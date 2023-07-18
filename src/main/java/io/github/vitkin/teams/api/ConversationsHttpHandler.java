package io.github.vitkin.teams.api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import io.github.vitkin.teams.api.csa.Conversation;
import io.github.vitkin.teams.api.csa.Teams;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import lombok.extern.log4j.Log4j2;
import io.github.vitkin.teams.api.csa.Teams.ConversationResponse;

/**
 *
 * @author victor
 */
@Log4j2
public class ConversationsHttpHandler implements HttpHandler {

  /**
   *
   * @param args
   */
  public static void main(String... args) throws IOException {

    HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
    server.createContext("/", new ConversationsHttpHandler());
    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
    server.setExecutor(threadPoolExecutor);
    server.start();
    log.info(" Server started on port 8001");
  }

  /**
   *
   * @param httpExchange
   * @throws IOException
   */
  @Override
  public void handle(HttpExchange httpExchange) throws IOException {

    String requestParamValue = null;

    /*
    if ("GET".equals(httpExchange.getRequestMethod())) {
      requestParamValue = handleGetRequest(httpExchange);
    } else if ("POST".equals(httpExchange.getRequestMethod())) {
      requestParamValue = handlePostRequest(httpExchange);
    }
     */
    handleResponse(httpExchange, requestParamValue);
  }

  /**
   *
   * @param httpExchange
   * @return
   */
  private String handleGetRequest(HttpExchange httpExchange) {

    return httpExchange.
      getRequestURI()
      .toString()
      .split("\\?")[1]
      .split("=")[1];
  }

  /**
   *
   * @param httpExchange
   * @param requestParamValue
   * @throws IOException
   */
  private void handleResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {

    try (OutputStream outputStream = httpExchange.getResponseBody()) {
      var uri = httpExchange.getRequestURI().getPath();

      if (uri.contains("/ams/")) {
        var u = uri.substring(0, uri.indexOf("/views"));
        var path = Paths.get("out/conversations" + u);
        var bytes = Files.readAllBytes(path);

        httpExchange.sendResponseHeaders(200, bytes.length);

        outputStream.write(bytes);
      } else if (uri.startsWith("/chats/") || uri.startsWith("/teams/")) {
        StringBuilder htmlBuilder = new StringBuilder();

        htmlBuilder
          .append("<!DOCTYPE html>"
            + "<html><head><meta charset=\"utf-8\" />"
            + "<style>"
            + "table, th, td { border: 1px solid black;border-collapse: collapse; }"
            + "th, td { padding: 10px; text-align: left; font-family: Arial, Helvetica, sans-serif; }"
            + "</style>"
            + "</head><body>");

        Jsonb jsonb = JsonbBuilder.create();

        Files.find(
          Paths.get("out/conversations" + uri),
          1,
          (p, basicFileAttributes) -> p.getFileName().toString().endsWith(".json")
        ).sorted(Comparator.reverseOrder()).forEach(path -> {
          log.debug("Path: {}", path);

          // if (path.endsWith("1.json"))
          try {
            // /chats/19:0a46c3a44757468491aa0ff5370f3c73@thread.v2/1.json
            // var path = Paths.get("out/conversations" + uri + "/1.json");
            var response = jsonb.fromJson(Files.readString(path), Conversation.Response.class);

            response.messages().forEach(message -> {
              log.debug("Message:\n{}:", message::content);

              htmlBuilder
                .append("<table style=\"width:800px\"><tr><th style=\"width:30%\">")
                .append(message.imdisplayname())
                .append("</th><th>")
                .append(message.composetime())
                .append("</th></tr><tr><td colspan=\"2\">");

              if ("RichText/Html".equals(message.messagetype())) {
                htmlBuilder
                  .append(message.content()
                    .replaceAll("https://us-api.asm.skype.com/v1/objects/", uri + "/ams/"));
              } else {
                htmlBuilder
                  .append(message.content().replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
              }

              var as = message.annotationsSummary();

              if (as != null && as.emotions() != null) {
                htmlBuilder.append("</td></tr><tr><td colspan=\"2\" style=\"text-align: right\">");

                as.emotions().entrySet().forEach(e -> {
                  var k = e.getKey();

                  switch (k) {
                    case "like" ->
                      k = "&#128077;";
                    case "laugh" ->
                      k = "&#129315;";
                    case "heart" ->
                      k = "&#128150;";
                    case "sad" ->
                      k = "&#128546;";
                  }

                  htmlBuilder
                    .append(k)
                    .append(":")
                    .append(e.getValue())
                    .append(" ");
                });
              }

              htmlBuilder.append("</td></tr></table><br/>");
            });
          } catch (IOException ex) {
            log.error(ex, ex);
          }
        });

        htmlBuilder.append("</body></html>");

        String htmlResponse = htmlBuilder.toString();

        var b = htmlResponse.getBytes(/*"UTF-8"*/);

        // this line is a must
        httpExchange.sendResponseHeaders(200, b.length);

        outputStream.write(b);
      } else {
        StringBuilder htmlBuilder = new StringBuilder();

        htmlBuilder
          .append("<!DOCTYPE html>"
            + "<html><head><meta charset=\"utf-8\" />"
            + "<style>"
            + "table, th, td { border: 1px solid black;border-collapse: collapse; }"
            + "th, td { padding: 10px; text-align: left; font-family: Arial, Helvetica, sans-serif; }"
            + "</style>"
            + "</head><body>");

        Jsonb jsonb = JsonbBuilder.create();

        var response = jsonb.fromJson(Files.readString(Paths.get("out/conversations.json")), ConversationResponse.class);

        response.chats().stream().forEach(chat -> {
          htmlBuilder.append("<table style=\"width:800px\">");

          if (chat.title() != null) {
            htmlBuilder
              .append("<tr><td>")
              .append(chat.title())
              .append("</td></tr>");
          }

          if (chat.lastMessage().content() != null) {
            htmlBuilder
              .append("<tr><td>")
              .append(chat.lastMessage().content()
                .replaceAll(
                  "https://us-api.asm.skype.com/v1/objects/",
                  "/chats/" + chat.id() + "/ams/"
                ))
              .append("</td></tr>")
              .append("<tr><td>Last message: ")
              .append(chat.lastMessage().composeTime())
              .append("</td></tr>");
          }

          htmlBuilder
            .append("<tr><td>Last join at: ")
            .append(chat.lastJoinAt())
            .append("</td></tr>")
            .append("<tr><td>")
            .append("<a href=\"")
            .append("/chats/")
            .append(chat.id())
            .append("\">")
            .append(chat.id())
            .append("</a>")
            .append("</td></tr>")
            .append("</table><br/>");
        });

        htmlBuilder.append("</body></html>");

        String htmlResponse = htmlBuilder.toString();

        var b = htmlResponse.getBytes(/*"UTF-8"*/);

        // this line is a must
        httpExchange.sendResponseHeaders(200, b.length);

        outputStream.write(b);
      }

      outputStream.flush();
    }

  }

  /**
   *
   * @param httpExchange
   * @return
   */
  private String handlePostRequest(HttpExchange httpExchange) {

    return handleGetRequest(httpExchange);
  }

}

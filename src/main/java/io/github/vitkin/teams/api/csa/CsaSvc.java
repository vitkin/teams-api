package io.github.vitkin.teams.api.csa;

import com.github.mizosoft.methanol.Methanol;
import io.github.vitkin.teams.api.SkypeToken;
import io.github.vitkin.teams.api.TeamsToken;
import io.github.vitkin.teams.api.csa.Channels.PinnedChannelsResponse;
import io.github.vitkin.teams.api.csa.Conversation.Message;
import io.github.vitkin.teams.api.csa.Conversation.Response;
import io.github.vitkin.teams.api.csa.Teams.Channel;
import io.github.vitkin.teams.api.csa.Teams.ConversationResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import lombok.extern.log4j.Log4j2;

/**
 *
 */
@Log4j2
public class CsaSvc {

  static final String CHAT_SVC_AGG = "https://teams.microsoft.com/api/csa/api/";
  static final String MESSAGES_HOST = "https://amer.ng.msg.teams.microsoft.com/";
  // static final String MESSAGES_HOST = "https://emea.ng.msg.teams.microsoft.com/";
  static final String SKYPE_ASM_API_URL = "https://us-api.asm.skype.com/v1/objects/";
  static final String ENDPOINT_CHAT_SVG_AGG = "chatsvcagg";
  static final String ENDPOINT_MESSAGES = "messages";

  TeamsToken token;
  URI csaSvcUrl = URI.create(CHAT_SVC_AGG);
  URI msgUrl = URI.create(MESSAGES_HOST);

  HttpClient client = Methanol.newBuilder()
    .version(HttpClient.Version.HTTP_1_1)
    .build();

  boolean debugSave;
  TeamsToken skypeToken;
  boolean debugDisallowUnknownFields;

  /**
   *
   * @param token
   * @param skypeToken
   */
  // Requires an aud:https://chatsvcagg.teams.microsoft.com token
  public CsaSvc(TeamsToken token, SkypeToken skypeToken) {

    // https://teams.microsoft.com/api/csa/api/v1/teams/users/me?isPrefetch=false&enableMembershipSummary=true
    this.token = token;
    this.skypeToken = skypeToken;
  }

  /**
   *
   * @param flag
   */
  public void debugSave(boolean flag) {
    debugSave = flag;
  }

  /**
   *
   * @param flag
   */
  public void debugDisallowUnknownFields(boolean flag) {
    debugDisallowUnknownFields = flag;
  }

  /**
   *
   * @param t
   * @param path
   * @return
   */
  URI getEndpoint(String t, String path) {

    if (path.startsWith("/")) {
      path = path.substring(1);
    }

    var url = csaSvcUrl;

    switch (t) {
      case ENDPOINT_CHAT_SVG_AGG ->
        url = csaSvcUrl;
      case ENDPOINT_MESSAGES ->
        url = msgUrl;

    }

    return url.resolve("v1/" + path);
  }

  /**
   *
   * @param method
   * @param url
   * @param body
   * @return
   */
  HttpRequest authenticatedRequest(String method, String url, HttpRequest.BodyPublisher body) {

    if (body == null) {
      body = HttpRequest.BodyPublishers.noBody();
    }

    var builder = HttpRequest.newBuilder()
      .uri(URI.create(url))
      .method(method, body);

    if (url.startsWith(CHAT_SVC_AGG)) {
      builder.headers("Authorization", TeamsToken.authString(token));
    } else if (url.startsWith(MESSAGES_HOST)) {
      builder.headers("Authentication", TeamsToken.authString(skypeToken));
    } else if (url.startsWith(SKYPE_ASM_API_URL)) {
      builder.headers("Authorization", TeamsToken.authString(skypeToken).replace("skypetoken=", "skype_token "));
    }

    return builder.build();
  }

  /**
   *
   * @throws IOException
   * @throws InterruptedException
   */
  public void getConversationsList() throws IOException, InterruptedException {

    var path = Paths.get("conversations.json");

    Jsonb jsonb = JsonbBuilder.create();

    // See https://skpy.t.allofti.me/background/protocol/chats.html
    var backwardLink = "users/ME/conversations";

    while (backwardLink != null) {
      var endpointUrl = backwardLink.startsWith("http") ? URI.create(backwardLink) : getEndpoint(ENDPOINT_MESSAGES, backwardLink);

      var req = authenticatedRequest("GET", endpointUrl.toString(), null);
      var resp = client.send(req, BodyHandlers.ofString());

      log.debug("Request:\n{}", req);
      log.debug("Status Code: {}", resp::statusCode);
      log.debug("Body:\n{}", resp::body);

      var body = resp.body();
      var response = jsonb.fromJson(body, Response.class);

      var metadata = response._metadata();

      // var messagesFile = Files.createFile(path.resolve(metadata.lastCompleteSegmentStartTime() + "-" + metadata.lastCompleteSegmentEndTime() + ".json"));
      var messagesFile = Files.createFile(path);
      // var messagesFile = Files.createFile(path.resolve("messages-" + i++ + ".json"));
      Files.writeString(messagesFile, body);

      backwardLink = metadata.backwardLink();
    }
  }

  /**
   * TODO: Consider void return.
   *
   * @param id
   * @param directory
   * @return
   * @throws UnsupportedEncodingException
   * @throws IOException
   * @throws InterruptedException
   */
  public List<Message> getConversationById(String id, String directory) throws UnsupportedEncodingException, IOException, InterruptedException {

    final String OUTPUT_PATH = "out/conversations/" + directory + "/" + id;
    log.info("Getting conversation {}", OUTPUT_PATH);
    var path = Paths.get(OUTPUT_PATH);
    Files.createDirectories(path);

    Jsonb jsonb = JsonbBuilder.create();

    // TODO: Consider having void return.
    ArrayList<Message> messages = new ArrayList<>();

    if (Files.exists(path.resolve("1.json")) || Files.exists(path.resolve("0.json"))) {
      log.warn("Conversation already downloaded: {}", id);

      return messages;
    }

    // See https://skpy.t.allofti.me/background/protocol/chats.html
    // var backwardLink = "users/ME/conversations";
    var backwardLink = "users/ME/conversations/" + URLEncoder.encode(id, "UTF-8") + "/messages"
      + "?view=" + URLEncoder.encode("msnp24Equivalent|CsupportsMessageProperties", "UTF-8")
      + "&pageSize=200"
      + "&startTime=1";

    // TODO: We shouldn't need an index for the AMS resources files names.
    // int i = 1;
    while (backwardLink != null) {
      var endpointUrl = backwardLink.startsWith("http") ? URI.create(backwardLink) : getEndpoint(ENDPOINT_MESSAGES, backwardLink);

      var req = authenticatedRequest("GET", endpointUrl.toString(), null);
      var resp = client.send(req, BodyHandlers.ofString());

      if (resp.statusCode() != 200) {
        log.error("Failed querying:\n{}", backwardLink);

        // System.exit(-1);
        break;
      }

      log.debug("Request:\n{}", req);
      log.debug("Status Code: {}", resp::statusCode);
      log.debug("Body:\n{}", resp::body);

      var body = resp.body();
      var response = jsonb.fromJson(body, Response.class);

      var metadata = response._metadata();

      // var messagesFile = Files.createFile(path.resolve(metadata.lastCompleteSegmentStartTime() + "-" + metadata.lastCompleteSegmentEndTime() + ".json"));
      var messagesFile = Files.createFile(path.resolve(metadata.lastCompleteSegmentStartTime() + ".json"));
      // var messagesFile = Files.createFile(path.resolve("messages-" + i++ + ".json"));
      Files.writeString(messagesFile, body);

      backwardLink = metadata.backwardLink();
      // backwardLink = backwardLink.replace("startTime=1", "startTime=" + metadata.lastCompleteSegmentStartTime());
      // backwardLink = backwardLink.replace("view=msnp24Equivalent&", "view=" + URLEncoder.encode("msnp24Equivalent|CsupportsMessageProperties", "UTF-8") + "&");

      var responseMessages = response.messages();

      // messages.addAll(responseMessages);
      var amsPath = Paths.get("out/conversations/" + directory + "/" + id + "/ams");
      Files.createDirectories(amsPath);

      responseMessages.forEach(message -> {

        var amsReferences = message.amsreferences();

        if (amsReferences != null) {
          log.debug("Message Content:\n{}", message::content);

          amsReferences.stream().forEach(ref -> {

            if (ref.isBlank()) {
              log.warn("Blank AMS reference for message {}", message::id);
            } else {
              // TODO: Figure out a better way to identify the AMS resource type.
              // var requestSuffix = message.content().contains(ref + "/views/img") ? "imgpsh_fullsize?v=1" : "original";
              if (!downloadAmsResource(amsPath, ref, "imgpsh_fullsize?v=1")) {
                if (!downloadAmsResource(amsPath, ref, "original")) {
                  if (!downloadAmsResource(amsPath, ref, "audio?v=1")) {
                    if (downloadAmsResource(amsPath, ref, "thumbnail?v=1")) {
                      // TODO: Parse content HTML.
                      log.warn("Video must be downloaded, see below:\n{}", message.content());
                    } else {
                      log.error("Cannot download {} for Conversation {}", ref, message.conversationid());

                      // System.exit(-2);
                    }
                  }
                }
              }
            }
          });
        }
      });
    }

    // TODO: Consider having void return.
    return messages;
  }

  /**
   *
   * @param amsPath
   * @param ref
   * @param requestSuffix
   * @return
   */
  private boolean downloadAmsResource(final Path amsPath, final String ref, final String requestSuffix) {

    final var request = authenticatedRequest("GET", SKYPE_ASM_API_URL + ref + "/views/" + requestSuffix, null);

    Path path = null;

    try {
      path = amsPath.resolve(ref);

      if (Files.exists(path)) {
        log.warn("File {} already exists!", path);

        return true;
      } else {
        Files.createFile(path);
      }

      final var r = client.send(request, BodyHandlers.ofFile(path));

      log.debug("Request:\n{}", request);
      log.debug("Status Code: {}", r::statusCode);
      log.debug("Body:\n{}", r::body);

      if (r.statusCode() == 200) {
        return true;
      }
    } catch (IOException | InterruptedException ex) {
      log.error(ex, ex);
    }

    if (path != null) {
      try {
        log.warn("Deleting file {}", path);
        Files.deleteIfExists(path);
      } catch (IOException ex) {
        log.error(ex, ex);
      }
    }

    return false;
  }

  /**
   *
   * @param channel
   * @return
   * @throws UnsupportedEncodingException
   * @throws IOException
   * @throws InterruptedException
   */
  public List<Message> getConversationByChannel(Channel channel) throws UnsupportedEncodingException, IOException, InterruptedException {

    var endpointUrl = getEndpoint(ENDPOINT_MESSAGES, "/users/ME/conversations/" + URLEncoder.encode(channel.id(), "UTF-8") + "/messages"
      + "?view=msnp24Equivalent|supportsMessageProperties"
      + "&pageSize=200"
      + "&startTime=1"
    );

    var req = authenticatedRequest("GET", endpointUrl.toString(), null);

    var resp = client.send(req, BodyHandlers.ofString());

    log.debug("Request:\n{}", req);
    log.debug("Status Code: {}", resp::statusCode);
    log.debug("Body:\n{}", resp::body);

    Jsonb jsonb = JsonbBuilder.create();

    var msgResponse = jsonb.fromJson(resp.body(), Response.class);

    return msgResponse.messages();
  }

  /**
   *
   * @return @throws IOException
   * @throws InterruptedException
   */
  public Teams.ConversationResponse getConversations() throws IOException, InterruptedException {

    var path = Paths.get("out/conversations.json");

    final String body;

    if (Files.exists(path)) {
      log.warn("File {} already exists!", path);
      body = Files.readString(path);
    } else {
      Files.createFile(path);

      var endpointUrl = getEndpoint(ENDPOINT_CHAT_SVG_AGG, "/teams/users/me"
        + "?isPrefetch=false"
        + "&enableMembershipSummary=true"
      );

      var req = authenticatedRequest("GET", endpointUrl.toString(), null);
      var resp = client.send(req, BodyHandlers.ofString());

      log.debug("Request:\n{}", req);
      log.debug("Status Code: {}", resp::statusCode);
      log.debug("Body:\n{}", resp::body);

      body = resp.body();
      Files.writeString(path, body);
    }

    Jsonb jsonb = JsonbBuilder.create();
    var msgResponse = jsonb.fromJson(body, ConversationResponse.class);

    return msgResponse;
  }

  /**
   *
   * @return @throws IOException
   * @throws InterruptedException
   */
  public List<String> getPinnedChannels() throws IOException, InterruptedException {

    var endpointUrl = getEndpoint(ENDPOINT_CHAT_SVG_AGG, "/teams/users/me/pinnedChannels");

    var req = authenticatedRequest("GET", endpointUrl.toString(), null);

    var resp = client.send(req, BodyHandlers.ofString());

    log.debug("Request:\n{}", req);
    log.debug("Status Code: {}", resp::statusCode);
    log.debug("Body:\n{}", resp::body);

    var jsonb = JsonbBuilder.create(new JsonbConfig().withFormatting(false));

    var msgResponse = jsonb.fromJson(resp.body(), PinnedChannelsResponse.class);

    return msgResponse.pinChannelOrder();
  }
}

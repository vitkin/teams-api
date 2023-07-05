package io.github.vitkin.teams.api.csa;

import io.github.vitkin.teams.api.SkypeToken;
import io.github.vitkin.teams.api.TeamsToken;
import io.github.vitkin.teams.api.csa.Channels.PinnedChannelsResponse;
import io.github.vitkin.teams.api.csa.Messages.ChatMessage;
import io.github.vitkin.teams.api.csa.Messages.MessagesResponse;
import io.github.vitkin.teams.api.csa.Teams.Channel;
import io.github.vitkin.teams.api.csa.Teams.ConversationResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

/**
 *
 */
public class CsaSvc {

  static final String CHAT_SVC_AGG = "https://teams.microsoft.com/api/csa/api/";
  static final String MESSAGES_HOST = "https://emea.ng.msg.teams.microsoft.com/";
  static final String ENDPOINT_CHAT_SVG_AGG = "chatsvcagg";
  static final String ENDPOINT_MESSAGES = "messages";

  TeamsToken token;
  URI csaSvcUrl = URI.create(CHAT_SVC_AGG);
  URI msgUrl = URI.create(MESSAGES_HOST);

  HttpClient client = HttpClient.newBuilder()
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
    }

    return builder.build();
  }

  /**
   *
   * @param channel
   * @return
   * @throws UnsupportedEncodingException
   * @throws IOException
   * @throws InterruptedException
   */
  public List<ChatMessage> getMessagesByChannel(Channel channel) throws UnsupportedEncodingException, IOException, InterruptedException {

    var endpointUrl = getEndpoint(ENDPOINT_MESSAGES, "/users/ME/conversations/" + URLEncoder.encode(channel.id(), "UTF-8") + "/messages"
      + "?view=msnp24Equivalent|supportsMessageProperties"
      + "&pageSize=200"
      + "&startTime=1"
    );

    var req = authenticatedRequest("GET", endpointUrl.toString(), null);

    var resp = client.send(req, BodyHandlers.ofString());

    System.out.println(resp.statusCode());
    System.out.println(resp.body());

    Jsonb jsonb = JsonbBuilder.create();

    var msgResponse = jsonb.fromJson(resp.body(), MessagesResponse.class);

    return msgResponse.messages();
  }

  /**
   *
   * @return @throws IOException
   * @throws InterruptedException
   */
  public Teams.ConversationResponse getConversations() throws IOException, InterruptedException {

    var endpointUrl = getEndpoint(ENDPOINT_CHAT_SVG_AGG, "/teams/users/me"
      + "?isPrefetch=false"
      + "&enableMembershipSummary=true"
    );

    var req = authenticatedRequest("GET", endpointUrl.toString(), null);

    var resp = client.send(req, BodyHandlers.ofString());

    System.out.println(resp.statusCode());
    System.out.println(resp.body());

    Jsonb jsonb = JsonbBuilder.create();

    var msgResponse = jsonb.fromJson(resp.body(), ConversationResponse.class);

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

    System.out.println(resp.statusCode());
    System.out.println(resp.body());

    var jsonb = JsonbBuilder.create(new JsonbConfig().withFormatting(false));

    var msgResponse = jsonb.fromJson(resp.body(), PinnedChannelsResponse.class);

    return msgResponse.pinChannelOrder();
  }
}

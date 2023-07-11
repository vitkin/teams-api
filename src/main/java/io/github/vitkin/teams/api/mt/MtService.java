package io.github.vitkin.teams.api.mt;

import com.github.mizosoft.methanol.Methanol;
import io.github.vitkin.teams.api.TeamsToken;
import io.github.vitkin.teams.api.models.Models;
import io.github.vitkin.teams.api.models.Models.Tenant;
import io.github.vitkin.teams.api.models.Models.UserResponse;
import io.github.vitkin.teams.api.mt.User.UsersResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.util.Base64;
import java.util.List;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import lombok.extern.log4j.Log4j2;

/**
 *
 */
@Log4j2
public class MtService {

  URI middleTierUrl = URI.create("https://teams.microsoft.com/api/mt/");
  TeamsToken token;
  String region;

  HttpClient client = Methanol.newBuilder()
    .version(HttpClient.Version.HTTP_1_1)
    .defaultHeader("Accept", "application/json")
    .build();

  boolean debugSave;
  boolean debugDisallowUnknownFields;

  /**
   *
   * @param region
   * @param token
   */
  public MtService(String region, TeamsToken token) {

    this.region = region;
    this.token = token;
  }

  /**
   *
   * @param flag
   */
  void debugSave(boolean flag) {
    debugSave = flag;
  }

  /**
   *
   * @param flag
   */
  void debugDisallowUnknownFields(boolean flag) {
    debugDisallowUnknownFields = flag;
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
      body = BodyPublishers.noBody();
    }

    return HttpRequest.newBuilder()
      .uri(URI.create(url))
      .headers("Authorization", TeamsToken.authString(token))
      .method(method, body)
      .build();
  }

  /**
   *
   * @param path
   * @return
   */
  URI getEndpoint(String path) {

    if (path.startsWith("/")) {
      path = path.substring(1);
    }

    return middleTierUrl.resolve(region + "/beta/" + path);
  }

  /**
   *
   * @return @throws ParseException
   * @throws IOException
   * @throws UnsupportedEncodingException
   * @throws InterruptedException
   */
  public Models.User getMe() throws ParseException, IOException, UnsupportedEncodingException, InterruptedException {

    // Retrieve email from token
    var email = User.getTokenEmail(token);

    return getUser(email);
  }

  /**
   *
   * @param mri
   * @return
   * @throws IOException
   * @throws InterruptedException
   */
  public List<Models.User> fetchShortProfile(String... mri) throws IOException, InterruptedException {
    var endpointUrl = getEndpoint("/users/fetchShortProfile"
      + "?isMailAddress=false"
      + "&enableGuest=true"
      + "&includeIBBarredUsers=false"
      + "&skypeTeamsInfo=true"
    );

    var jsonb = JsonbBuilder.create();
    var bodyBytes = jsonb.toJson(mri);

    var req = authenticatedRequest("POST", endpointUrl.toString(), BodyPublishers.ofString(bodyBytes));

    var resp = client.send(req, HttpResponse.BodyHandlers.ofString());

    log.info(resp::statusCode);
    log.info(resp::body);

    var msgResponse = jsonb.fromJson(resp.body(), UsersResponse.class);

    return msgResponse.value();

  }

  /**
   *
   * @return
   */
  public List<Tenant> getTenants() throws IOException, InterruptedException {

    var endpointUrl = getEndpoint("/users/tenants");

    var req = authenticatedRequest("GET", endpointUrl.toString(), null);

    var resp = client.send(req, HttpResponse.BodyHandlers.ofString());

    log.info(resp::statusCode);
    log.info(resp::body);

    Jsonb jsonb = JsonbBuilder.create();

    var msgResponse = jsonb.fromJson(resp.body(), List.class);

    return (List<Tenant>) msgResponse;
  }

  /**
   *
   * @param email
   * @return
   * @throws UnsupportedEncodingException
   * @throws IOException
   * @throws InterruptedException
   */
  private Models.User getUser(String email) throws UnsupportedEncodingException, IOException, InterruptedException {

    var endpointUrl = getEndpoint("/users/" + URLEncoder.encode(email, "UTF-8") + "/"
      + "?throwIfNotFound=false"
      + "&isMailAddress=true"
      + "&enableGuest=true"
      + "&includeIBBarredUsers=true"
      + "&skypeTeamsInfo=true"
    );

    var req = authenticatedRequest("GET", endpointUrl.toString(), null);

    var resp = client.send(req, HttpResponse.BodyHandlers.ofString());

    log.info(resp::statusCode);
    log.info(resp::body);

    Jsonb jsonb = JsonbBuilder.create();

    var msgResponse = jsonb.fromJson(resp.body(), UserResponse.class);

    return msgResponse.value();
  }

  /**
   *
   * @param email
   * @return
   * @throws UnsupportedEncodingException
   * @throws IOException
   * @throws InterruptedException
   */
  byte[] getProfilePicture(String email) throws UnsupportedEncodingException, IOException, InterruptedException {

    var endpointUrl = getEndpoint("/users/" + URLEncoder.encode(email, "UTF-8") + "/profilepicture?displayname=aaa");

    var req = authenticatedRequest("GET", endpointUrl.toString(), null);

    var resp = client.send(req, HttpResponse.BodyHandlers.ofString());

    return Base64.getDecoder().decode(resp.body());
  }
}

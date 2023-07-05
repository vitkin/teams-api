package io.github.vitkin.teams.api;

import static io.github.vitkin.teams.api.Const.TEAMS_API_ENDPOINT;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import com.nimbusds.jwt.JWTParser;

/**
 *
 */
public class SkypeToken extends TeamsToken {

  /**
   *
   * @param tokenType
   * @throws Exception
   */
  public SkypeToken() throws Exception {

    super("skype");

    var authClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .build();

    var rootToken = new RootSkypeToken();

    var request = HttpRequest.newBuilder()
            .uri(URI.create(TEAMS_API_ENDPOINT + "/authsvc/v1.0/authz"))
            .headers(
                    "Ms-Teams-Authz-Type", "TokenRefresh",
                    "Authorization", "Bearer " + rootToken.raw
            )
            .POST(BodyPublishers.noBody())
            .build();

    HttpResponse<String> response = authClient.send(request, BodyHandlers.ofString());

    System.out.println(response.statusCode());
    System.out.println(response.body());
    
    ObjectMapper mapper = new ObjectMapper();
    
    Map<String,Map> map = mapper.readValue(response.body(), Map.class);
    // System.out.println(map);

    Map<String,Object> tokens = map.get("tokens");
    
    this.raw = (String) tokens.get("skypeToken");
    this.inner = JWTParser.parse(this.raw);
    this.type = TOKEN_BEARER;
  }
}

package io.github.vitkin.teams.api.mt;

import io.github.vitkin.teams.api.TeamsToken;
import io.github.vitkin.teams.api.models.Models;
import java.text.ParseException;
import java.util.List;
import javax.json.bind.annotation.JsonbCreator;

/**
 *
 */
public class User {

  /**
   *
   * @param token
   * @return
   * @throws ParseException
   */
  static String getTokenEmail(TeamsToken token) throws ParseException {

    var claims = token.inner.getJWTClaimsSet();

    var mapClaims = claims.getClaims();

    var email = (String) mapClaims.get("email");

    if (email == null) {
      email = (String) mapClaims.get("upn");
    }

    return email;
  }

  /**
   *    */
  public static record UsersResponse(
    List<Models.User> value, //`json:"value"`
    String type //`json:"type"`
    ) {

    @JsonbCreator
    public UsersResponse  {
    }
  }
}

package io.github.vitkin.teams.api;

import com.nimbusds.jwt.JWT;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.nimbusds.jwt.JWTParser;

/**
 *
 */
public class TeamsToken {

  static final String TOKEN_SKYPE = "skypetoken";

  static final String TOKEN_BEARER = "Bearer";

  public JWT inner;
  
  public String raw;

  public String type;

  /**
   * 
   * @throws Exception 
   */
  TeamsToken() throws Exception {
    
    this("teams");
  }
  
  /**
   *
   * @param tokenType
   * @return
   * @throws Exception
   */
  TeamsToken(String tokenType) throws Exception {

    String tokenStr = System.getenv("MS_TEAMS_" + tokenType.toUpperCase() + "_TOKEN");

    if (tokenStr == null || "".equals(tokenStr)) {
      // Load from filesystem
      String homeDir = System.getProperty("user.home");

      if (homeDir == null) {
        throw new Exception("cannot retrieve user homedir");
      }

      Path jwtPath = Paths.get(homeDir, ".config/fossteams/token-" + tokenType + ".jwt");

      tokenStr = Files.readString(jwtPath);
    }

    this.raw = tokenStr;
    
    this.inner = JWTParser.parse(tokenStr);
    this.type = TOKEN_BEARER;
  }

  /**
   *
   * @param token
   * @return
   */
  public static String authString(TeamsToken token) {

    if (token == null) {
      return "";
    }

    switch (token.type) {
      case TOKEN_SKYPE:
        return "skypetoken=" + token.raw;
      case TOKEN_BEARER:
        return "Bearer " + token.raw;
    }
    return "";
  }
}

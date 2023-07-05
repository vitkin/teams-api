import com.skype.api.SkypeClient;
import com.skype.api.SkypeException;
import com.skype.api.Token;
import com.skype.api.TokenException;
import static com.skype.api.Token.Type.AUTHZ_TOKEN;
import static com.skype.api.Token.Type.REFRESH_TOKEN;
import static com.skype.api.Token.Type.SAML_TOKEN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestParseAuth {
    @org.junit.Test
    public void testParseAuth() throws SkypeException, TokenException {
        SkypeClient skype = new SkypeClient("https://login.skype.com");
        Token token = skype.getToken();
        String authzToken = token.getType() == AUTHZ_TOKEN? token.getId() : token.getSamlId();
        String authzTokenWithRefreshToken = authzToken.replaceAll("^refresh_token: ", "");
        String refreshToken = authzTokenWithRefreshToken.replaceAll("^.*: ", "");
        SkypeClient.Token.Type type = authzToken.indexOf(":") == -1? null : SkypeClient.Token.Type.valueOf(authzToken.split(":")[0]);
        assertTrue(type == REFRESH_TOKEN || type == SAML_TOKEN);
        String payload = authzTokenWithRefreshToken.split(":")[1];
        assertNotNull(refreshToken);
        assertNotNull(payload);
        String[] strings = payload.split(";");
        String[] ids = strings[0].split(",");
        assertEquals(3, ids.length);
        String[] tokens = strings[1].split(",");
        assertEquals(3, tokens.length);
        Token[] tokensWithRefreshToken = new Token[tokens.length + 1];
        for (int i = 0; i < tokens.length; i++) {
            tokensWithRefreshToken[i] = new Token(tokens[i], REFRESH_TOKEN.getValue());
        }
        tokensWithRefreshToken[tokens.length] = new Token(refreshToken, type.getValue());
        String[] samlIds = strings[2].split(",");
        assertEquals(2, samlIds.length);
        for (int i = 0; i < samlIds.length; i++) {
            if (i == 0) {
                assertEquals("CLIENT_ID", samlIds[i]);
            }
            else {
                assertEquals("SESSION_ID", samlIds[i]);
            }
        }
    }
}
package api;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParseAuthTest {
    @Test
    public void testParseAuth() {
        String response = "{\"tokens\":{\"skypeToken\":\"eyJhbGciOiJSUzI1NiIsImtpZCI6IjEwMiIsInR5cCI6IkpXVCJ9.aaa.eee\",\"expiresIn\":86397},\"region\":\"emea\",\"partition\":\"emea01\",\"regionGtms\":{\"ams\":\"https:\"";

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            AuthResponse authResponse = objectMapper.readValue(response, AuthResponse.class);
            assertEquals("eyJhbGciOiJSUzI1NiIsImtpZCI6IjEwMiIsInR5cCI6IkpXVCJ9.aaa.eee", authResponse.getTokens().getSkypeToken());
            assertEquals(86397, authResponse.getTokens().getExpiresIn());
            assertEquals("emea", authResponse.getRegion());
            assertEquals("emea01", authResponse.getPartition());
            assertEquals("https:", authResponse.getRegionGtms().getAms());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class AuthResponse {
    private Tokens tokens;
    private String region;
    private String partition;
    private RegionGtms regionGtms;

    public Tokens getTokens() {
        return tokens;
    }

    public void setTokens(Tokens tokens) {
        this.tokens = tokens;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPartition() {
        return partition;
    }

    public void setPartition(String partition) {
        this.partition = partition;
    }

    public RegionGtms getRegionGtms() {
        return regionGtms;
    }

    public void setRegionGtms(RegionGtms regionGtms) {
        this.regionGtms = regionGtms;
    }
}

class Tokens {
    private String skypeToken;
    private int expiresIn;

    public String getSkypeToken() {
        return skypeToken;
    }

    public void setSkypeToken(String skypeToken) {
        this.skypeToken = skypeToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}

class RegionGtms {
    private String ams;

    public String getAms() {
        return ams;
    }

    public void setAms(String ams) {
        this.ams = ams;
    }
}

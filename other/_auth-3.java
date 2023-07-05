import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jose.util.ResourceRetriever;
import com.nimbusds.jose.util.ResourceRetrieverException;

public class Test {
    private static class JWKSetResourceRetriever implements ResourceRetriever {
        private DefaultResourceRetriever defaultResourceRetriever;
        public JWKSetResourceRetriever(DefaultResourceRetriever defaultResourceRetriever) {
            this.defaultResourceRetriever = defaultResourceRetriever;
        }
        @Override
        public JWKSet retrieve(String url) throws ResourceRetrieverException {
            return defaultResourceRetriever.retrieve(url);
        }
    }
    private static JWKSet loadJWKSet() throws JOSEException, JWSException, IOException {
        JWKSet jwkSet = new JWKSet();
        JWK key = new RSAKey.Builder((RSAKey) JWK.load(Test.class.getResourceAsStream("/rsa_1.jwk"))).build();
        jwkSet.add(key);
        return jwkSet;
    }
    public static void main(String[] args) throws JOSEException, JWSException, IOException {
        ResourceRetriever resourceRetriever = new DefaultResourceRetriever();
        JWKSet jwkSet = loadJWKSet();
        JWKSetResourceRetriever jwkSetResourceRetriever = new JWKSetResourceRetriever(resourceRetriever);
        JWSSigner signer = new RSASSASigner(new RSAKey.Builder((
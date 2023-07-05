package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AuthzResponse {
    @JsonProperty("tokens")
    private Tokens tokens;
    @JsonProperty("region")
    private String region;
    @JsonProperty("partition")
    private String partition;
    @JsonProperty("regionGtms")
    private RegionGtms regionGtms;
    @JsonProperty("regionSettings")
    private RegionSettings regionSettings;
    @JsonProperty("licenseDetails")
    private LicenseDetails licenseDetails;

    // getters and setters

    static class Tokens {
        @JsonProperty("skypeToken")
        private String skypeToken;
        @JsonProperty("expiresIn")
        private int expiresIn;

        // getters and setters
    }

    static class RegionGtms {
        @JsonProperty("ams")
        private String ams;
        @JsonProperty("amsV2")
        private String amsV2;
        @JsonProperty("amsS2S")
        private String amsS2S;
        @JsonProperty("appsDataLayerService")
        private String appsDataLayerService;
        @JsonProperty("appsDataLayerServiceS2S")
        private String appsDataLayerServiceS2S;
        @JsonProperty("calling_callControllerServiceUrl")
        private String calling_callControllerServiceUrl;
        @JsonProperty("calling_callStoreUrl")
        private String calling_callStoreUrl;
        @JsonProperty("calling_conversationServiceUrl")
        private String calling_conversationServiceUrl;
        @JsonProperty("calling_keyDistributionUrl")
        private String calling_keyDistributionUrl;
        @JsonProperty("calling_potentialCallRequestUrl")
        private String calling_potentialCallRequestUrl;
        @JsonProperty("calling_sharedLineOptionsUrl")
        private String calling_sharedLineOptionsUrl;
        @JsonProperty("calling_udpTransportUrl")
        private String calling_udpTransportUrl;
        @JsonProperty("calling_uploadLogRequestUrl")
        private String calling_uploadLogRequestUrl;
        @JsonProperty("callingS2S_Broker")
        private String callingS2S_Broker;
        @JsonProperty("callingS2S_CallController")
        private String callingS2S_CallController;
        @JsonProperty("callingS2S_CallStore")
        private String callingS2S_CallStore;
        @JsonProperty("callingS2S_ContentSharing")
        private String callingS2S_ContentSharing;
        @JsonProperty("callingS2S_ConversationService")
        private String callingS2S_ConversationService;
        @JsonProperty("callingS2S_EnterpriseProxy")
        private String callingS2S_EnterpriseProxy;
        @JsonProperty("callingS2S_MediaController")
        private String callingS2S_MediaController;
        @JsonProperty("callingS2S_PlatformMediaAgent")
        private String callingS2S_PlatformMediaAgent;
        @JsonProperty("chatService")
        private String chatService;
        @JsonProperty("chatServiceS2S")
        private String chatServiceS2S;
        @JsonProperty("drad")
        private String drad;
        @JsonProperty("mailhookS2S")
        private String mailhookS2S;
        @JsonProperty("middleTier")
        private String middleTier;
        @JsonProperty("middleTierS2S")
        private String middleTierS2S;
        @JsonProperty("mtImageService")
        private String mtImageService;
        @JsonProperty("powerPointStateService")
        private String powerPointStateService;
        @JsonProperty("search")
        private String search;
        @JsonProperty("searchTelemetry")
        private String searchTelemetry;
        @JsonProperty("teamsAndChannelsService")
        private String teamsAndChannelsService;
        @JsonProperty("teamsAndChannelsProvisioningService")
        private String teamsAndChannelsProvisioningService;
        @JsonProperty("urlp")
        private String urlp;
        @JsonProperty("urlpV2")
        private String urlpV2;
        @JsonProperty("unifiedPresence")
        private String unifiedPresence;
        @JsonProperty("userEntitlementService")
        private String userEntitlementService;
        @JsonProperty("userIntelligenceService")
        private String userIntelligenceService;
        @JsonProperty("userProfileService")
        private String userProfileService;
        @JsonProperty("userProfileServiceS2S")
        private String userProfileServiceS2S;
        @JsonProperty("amdS2S")
        private String amdS2S;
        @JsonProperty("chatServiceAggregator")
        private String chatServiceAggregator;

        // getters and setters
    }

    static class RegionSettings {
        @JsonProperty("isUnifiedPresenceEnabled")
        private boolean isUnifiedPresenceEnabled;
        @JsonProperty("isOutOfOfficeIntegrationEnabled")
        private boolean isOutOfOfficeIntegrationEnabled;
        @JsonProperty("isContactMigrationEnabled")
        private boolean isContactMigrationEnabled;
        @JsonProperty("isAppsDiscoveryEnabled")
        private boolean isAppsDiscoveryEnabled;
        @JsonProperty("isFederationEnabled")
        private boolean isFederationEnabled;

        // getters and setters
    }

    static class LicenseDetails {
        @JsonProperty("isFreemium")
        private boolean isFreemium;
        @JsonProperty("isBasicLiveEventsEnabled")
        private boolean isBasicLiveEventsEnabled;
        @JsonProperty("isTrial")
        private boolean isTrial;
        @JsonProperty("isAdvComms")
        private boolean isAdvComms;

        // getters and setters
    }
}

class AuthzResponseTest {
    @Test
    void testParseAuth() {
        String response = "{\"tokens\":{\"skypeToken\":\"eyJhbGciOiJSUzI1NiIsImtpZCI6IjEwMiIsInR5cCI6IkpXVCJ9.aaa.eee\",\"expiresIn\":86397},\"region\":\"emea\",\"partition\":\"emea01\",\"regionGtms\":{\"ams\":\"https://eu-api.asm.skype.com\",\"amsV2\":\"https://eu-prod.asyncgw.teams.microsoft.com\",\"amsS2S\":\"https://eu-storage.asm.skype.com:444\",\"appsDataLayerService\":\"https://teams.microsoft.com/datalayer/emea\",\"appsDataLayerServiceS2S\":\"https://deletion-svc-emea.datalayer.teams.microsoft.com\",\"calling_callControllerServiceUrl\":\"https://api.cc.skype.com\",\"calling_callStoreUrl\":\"https://api.flightproxy.teams.microsoft.com/api/v2/ep/api.userstore.skype.com/\",\"calling_conversationServiceUrl\":\"https://api.flightproxy.teams.microsoft.com/api/v2/epconv\",\"calling_keyDistributionUrl\":\"https://api.flightproxy.teams.microsoft.com/api/v2/ep/api.cc.skype.com/kd\",\"calling_potentialCallRequestUrl\":\"https://api.flightproxy.teams.microsoft.com/api/v2/ep/api.cc.skype.com/cc/v1/potentialcall\",\"calling_sharedLineOptionsUrl\":\"https://api.flightproxy.teams.microsoft.com/api/v2/ep/api.cc.skype.com/cc/v1/sharedLineAppearance\",\"calling_udpTransportUrl\":\"udp://api.flightproxy.teams.microsoft.com:3478\",\"calling_uploadLogRequestUrl\":\"https://api.flightproxy.teams.microsoft.com/api/v2/ep/api.cc.skype.com/cc/v1/uploadlog/\",\"callingS2S_Broker\":\"https://api.broker.skype.com\",\"callingS2S_CallController\":\"https://api.cc.skype.com\",\"callingS2S_CallStore\":\"https://api.userstore.skype.com/\",\"callingS2S_ContentSharing\":\"https://api.css.skype.com/contentshare/\",\"callingS2S_ConversationService\":\"https://api.conv.skype.com/conv/\",\"callingS2S_EnterpriseProxy\":\"https://api.flightproxy.teams.microsoft.com\",\"callingS2S_MediaController\":\"https://api.mc.skype.com/media/v2/conversations\",\"callingS2S_PlatformMediaAgent\":\"https://pma.plat.skype.com:6448/platform/v1/incomingcall\",\"chatService\":\"https://emea.ng.msg.teams.microsoft.com\",\"chatServiceS2S\":\"https://emea.pg.msg.infra.teams.microsoft.com\",\"drad\":\"https://eu.msdrad.skype.com/\",\"mailhookS2S\":\"https://mailhook.teams.microsoft.com/emea\",\"middleTier\":\"https://teams.microsoft.com/api/mt/emea\",\"middleTierS2S\":\"https://teams.microsoft.com/api/mt/emea\",\"mtImageService\":\"https://teams.microsoft.com/api/mt/emea\",\"powerPointStateService\":\"https://emea.pptservicescast.officeapps.live.com\",\"search\":\"https://eu-prod.asyncgw.teams.microsoft.com/msgsearch\",\"searchTelemetry\":\"https://eu-prod.asyncgw.teams.microsoft.com/msgsearch\",\"teamsAndChannelsService\":\"https://teams.microsoft.com/api/mt/emea\",\"teamsAndChannelsProvisioningService\":\"https://teams.microsoft.com/fabric/emea/templates/api\",\"urlp\":\"https://urlp.asm.skype.com\",\"urlpV2\":\"https://eu-prod.asyncgw.teams.microsoft.com/urlp\",\"unifiedPresence\":\"https://presence.teams.microsoft.com\",\"userEntitlementService\":\"https://teams.microsoft.com/api/ues/emea\",\"userIntelligenceService\":\"https://teams.microsoft.com/api/nss/emea\",\"userProfileService\":\"https://teams.microsoft.com/api/userprofilesvc/emea\",\"userProfileServiceS2S\":\"https://userprofilesvc-emea.teams.microsoft.com\",\"amdS2S\":\"https://eu-distr.asm.skype.com:444\",\"chatServiceAggregator\":\"https://chatsvcagg.teams.microsoft.com\"},\"regionSettings\":{\"isUnifiedPresenceEnabled\":true,\"isOutOfOfficeIntegrationEnabled\":true,\"isContactMigrationEnabled\":true,\"isAppsDiscoveryEnabled\":true,\"isFederationEnabled\":true},\"licenseDetails\":{\"is
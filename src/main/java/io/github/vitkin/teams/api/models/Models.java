package io.github.vitkin.teams.api.models;

import java.util.List;

/**
 *
 */
public class Models {

  public static record UserResponse(
    User value, //`json:"value"`
    String type //`json:"type"`
    ) {

  }

  public static record SkypeTeamsInfo(
    boolean isSkypeTeamsUser) {

  }

  public static record FeatureSettings(
    boolean isPrivateChatEnabled,
    boolean enableShiftPresence,
    String coExistenceMode,
    boolean enableScheduleOwnerPermissions) {

  }

  public static record Phone(
    String type, //`json:"type"`
    String number //`json:"number"`
    ) {

  }

  public static record User(
    String alias, //`json:"alias"`
    boolean accountEnabled, //`json:"accountEnabled,omitempty"`
    String department, //`json:"department,omitempty"`
    String displayName, //`json:"displayName"`
    String email, //`json:"email"`
    FeatureSettings featureSettings, //`json:"featureSettings,omitempty"`
    String givenName, //`json:"givenName"`
    boolean isShortProfile, //`json:"isShortProfile"`
    boolean isSipDisabled, //`json:"isSipDisabled,omitempty"`
    String jobTitle, //`json:"jobTitle"`
    String mail, //`json:"mail,omitempty"`
    String mobile, //`json:"mobile,omitempty"`
    String mri, //`json:"mri,omitempty"`
    String objectId, //`json:"objectId"`
    String objectType, //`json:"objectType,omitempty"`
    List<Phone> phones, //`json:"phones,omitempty"`
    String physicalDeliveryOfficeName, //`json:"physicalDeliveryOfficeName,omitempty"`
    String preferredLanguage, //`json:"preferredLanguage,omitempty"`
    String responseSourceInformation, //`json:"responseSourceInformation,omitempty"`
    boolean showInAddressList, //`json:"showInAddressList,omitempty"`
    String sipProxyAddress, //`json:"sipProxyAddress,omitempty"`
    SkypeTeamsInfo skypeTeamsInfo, //`json:"skypeTeamsInfo,omitempty"`
    List<String> smtpAddresses, //`json:"smtpAddresses,omitempty"`
    String surname, //`json:"surname"`
    String tenantName, //`json:"tenantName"`
    String telephoneNumber, //`json:"telephoneNumber,omitempty"`
    String type, //`json:"type"`
    String userLocation, //`json:"userLocation"`
    String userPrincipalName, //`json:"userPrincipalName"`
    String userType //`json:"userType,omitempty"`
    ) {

  }

  public static record Tenant(
    String tenantID, //`json:"tenantId"`
    String tenantName, //`json:"tenantName"`
    String userId, //`json:"userId"`
    boolean isInvitationRedeemed, //`json:"isInvitationRedeemed"`
    String countryLetterCode, //`json:"countryLetterCode"`
    String userType, //`json:"userType"`
    String tenantType //`json:"tenantType"`
    ) {

  }
}

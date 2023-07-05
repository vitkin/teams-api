package io.github.vitkin.teams.api.csa;

//import java.time.Date;
import java.time.OffsetDateTime;
import java.util.List;

/**
 *
 */
public class Teams {

  public static record User() {

  }

  public static record FeedProperty(
    String isEmptyConversation,
    String consumptionHorizon,
    String consumptionHorizonBookmark) {

  }

  public static record PrivateFeed(
    String id,
    String type,
    long version,
    FeedProperty properties,
    Message lastMessage,
    String messages,
    String targetLink,
    String streamType) {

  }

  public static record ConversationMetadata(
    String syncToken,
    boolean isPartialData) {

  }

  public static record ChatMember(
    boolean isMuted,
    String mri,
    String role,
    String friendlyName,
    String tenantId,
    String objectId) {

  }

  public static record DateRange(
    OffsetDateTime startDate,
    OffsetDateTime endDate) {

  }

  public static record WeeklyRecurrence(
    long longerval,
    List<Long> daysOfTheWeek) {

  }

  public static record MonthlyRecurrence(
    long longerval,
    long weekOfTheMonthIndex,
    long dayOfTheWeek) {

  }

  public static record RecurrencePattern(
    long patternType,
    WeeklyRecurrence weekly,
    MonthlyRecurrence relativeMonthly) {

  }

  public static record MeetingInfo(
    String subject,
    String location,
    String startTime, // TODO: Switch to Date
    String endTime, // TODO: Switch to Date
    String exchangeId, // *String
    String iCalUid,
    boolean isCancelled,
    DateRange eventRecurrenceRange,
    RecurrencePattern eventRecurrencePattern,
    long appolongmentType,
    long meetingType,
    String scenario,
    String tenantId //`json:"tenantId"`
    ) {

  }

  public static record Chat(
    long chatSubType, //`json:"chatSubType"`
    String chatType, //`json:"chatType"`
    ConsumptionHorizon consumptionHorizon, //`json:"consumptionHorizon"`
    long conversationBlockedAt, //`json:"conversationBlockedAt"`
    String createdAt, //`json:"createdAt"`
    String creator, //`json:"creator"`
    boolean hasTranscript, //`json:"hasTranscript"`
    boolean hidden, //`json:"hidden"`
    String id, //`json:"id"`
    String longeropConversationStatus, //`json:"longeropConversationStatus"`
    long longeropType, //`json:"longeropType"`
    boolean isDisabled, //`json:"isDisabled"`
    boolean isGapDetectionEnabled, //`json:"isGapDetectionEnabled"`
    boolean isHighImportance, //`json:"isHighImportance"`
    boolean isLastMessageFromMe, //`json:"isLastMessageFromMe"`
    boolean isMessagingDisabled, //`json:"isMessagingDisabled"`
    boolean isOneOnOne, //`json:"isOneOnOne"`
    boolean isRead, //`json:"isRead"`
    boolean isSticky, //`json:"isSticky"`
    boolean isShared, //`json:"isShared"`
    OffsetDateTime lastJoinAt, //`json:"lastJoinAt"`
    OffsetDateTime lastLeaveAt, //`json:"lastLeaveAt"`
    Message lastMessage, //`json:"lastMessage"`
    MeetingInfo meetingInformation, //`json:"meetingInformation"`
    String meetingPolicy, //`json:"meetingPolicy"`
    List<ChatMember> members, //`json:"members"`
    String retentionHorizon, //`json:"retentionHorizon"`
    String retentionHorizonV2, //`json:"retentionHorizonV2"`
    String shareHistoryFromTime, //`json:"shareHistoryFromTime"`
    List<Tab> tabs, //`json:"tabs"`
    String tenantId, //`json:"tenantId"`
    long threadVersion, //`json:"threadVersion"`
    String threadSchemaVersion, //`json:"threadSchemaVersion,omitempty"`
    String title, //`json:"title"`
    ConsumptionHorizon userConsumptionHorizon, //`json:"userConsumptionHorizon"`
    long version//`json:"version"`
    ) {

  }

  public static record ConversationResponse(
    List<Chat> chats,
    ConversationMetadata metadata,
    List<PrivateFeed> privateFeeds,
    List<Team> teams,
    List<User> users) {

  }

  public static record ConsumptionHorizon(
    long originalArrivalTime,
    long timeStamp,
    String clientMessageId) {

  }

  public static record RetentionHorizon() {

  }

  public static record RetentionHorizonV2() {

  }

  public static record FileSettings(
    String filesRelativePath,
    String documentLibraryId,
    String sharepolongRootLibrary) {

  }

  public static record Tab(
    String id,
    String name,
    String definitionId,
    String directive,
    String tabType,
    float order,
    long replyChainId,
    Object settings //longerface{}
    ) {

  }

  public static record Message(
    String messageType,
    String content,
    String clientMessageId,
    String imDisplayName,
    String id,
    String type,
    OffsetDateTime composeTime, //api.RFC3339Time
    OffsetDateTime originalArrivalTime, //api.RFC3339Time
    String containerId,
    String parentMessageId,
    String from,
    long sequenceId,
    long version,
    String threadType, //*String
    boolean isEscalationToNewPerson) {

  }

  public static record ConnectorProfile(
    String avatarUrl,
    String displayName,
    String incomingUrl, // *String
    String connectorType,
    String id) {

  }

  public static record ChannelSettings(
    long channelPostPermissions,
    long channelReplyPermissions,
    long channelPinPostPermissions,
    long channelConnectorsPostPermissions,
    long channelBotsPostPermissions) {

  }

  public static record ActiveMeetup(
    String messageId,
    String conversationURL, //`json:"conversationUrl"`
    String conversationId, //`json:"conversationId"`
    String groupCallInitiator, //`json:"groupCallInitiator"`
    String wasInitiatorInLobby, //`json:"wasInitiatorInLobby"`
    OffsetDateTime expiration, //`json:"expiration"`
    String status, //`json:"status"`
    boolean isHostless, //`json:"isHostless"`
    String tenantId, //`json:"tenantId"`
    String organizerId, //`json:"organizerId"`
    long callMeetingType, //`json:"callMeetingType"`
    String conversationType //`json:"conversationType"`
    ) {

  }

  public static record Channel(
    String id,
    String displayName,
    String description,
    ConsumptionHorizon consumptionHorizon, //*ConsumptionHorizon
    RetentionHorizon retentionHorizon, //*RetentionHorizon
    RetentionHorizonV2 retentionHorizonV2, //*RetentionHorizonV2
    long version,
    long threadVersion,
    String parentTeamId,
    boolean isGeneral,
    boolean isFavorite,
    boolean isFollowed,
    boolean isMember,
    String creator,
    boolean isMessageRead,
    boolean isImportantMessageRead,
    boolean isGapDetectionEnabled,
    FileSettings defaultFileSettings,
    List<Tab> tabs,
    Message lastMessage,
    List<ConnectorProfile> connectorProfiles,
    boolean isDeleted,
    boolean isPinned,
    boolean isShared,
    OffsetDateTime lastImportantMessageTime,
    OffsetDateTime lastLeaveAt,
    OffsetDateTime lastJoinAt,
    long memberRole,
    boolean isMuted,
    long membershipExpiry,
    List<ActiveMeetup> activeMeetups,
    boolean isFavoriteByDefault,
    OffsetDateTime creationTime,
    boolean isArchived,
    long channelType,
    ChannelSettings channelSettings,
    long membershipVersion,
    MembershipSummary membershipSummary, //*MembershipSummary
    TeamSettings memberSettings, //`json:"memberSettings"`
    TeamSettings guestSettings, //`json:"guestSettings"`
    boolean isModerator,
    String groupId,
    boolean channelOnlyMember,
    boolean explicitlyAdded,
    String threadSchemaVersion, //`json:"threadSchemaVersion,omitempty"`
    ConsumptionHorizon userConsumptionHorizon,
    String tenantId //`json:"tenantId"`
    ) {

  }

  public static record TeamSettings(
    boolean addDisplayContent, // `json:"addDisplayContent"`
    boolean adminDeleteEnabled, // `json:"adminDeleteEnabled"`
    boolean channelMention, // `json:"channelMention"`
    boolean createlongegration, // `json:"createlongegration"`
    boolean createTab, // `json:"createTab"`
    boolean createTopic, // `json:"createTopic"`
    boolean customMemesEnabled, // `json:"customMemesEnabled"`
    boolean deleteEnabled, // `json:"deleteEnabled"`
    boolean deletelongegration, // `json:"deletelongegration"`
    boolean deleteTab, // `json:"deleteTab"`
    boolean deleteTopic, // `json:"deleteTopic"`
    boolean editEnabled, // `json:"editEnabled"`
    long generalChannelPosting,// `json:"generalChannelPosting"`
    boolean giphyEnabled, // `json:"giphyEnabled"`
    long giphyRating,// `json:"giphyRating"`
    boolean installApp, // `json:"installApp"`
    boolean isPrivateChannelCreationEnabled, // `json:"isPrivateChannelCreationEnabled"`
    boolean messageThreadingEnabled, // `json:"messageThreadingEnabled"`
    boolean removeDisplayContent, // `json:"removeDisplayContent"`
    boolean stickersEnabled, // `json:"stickersEnabled"`
    boolean teamMemesEnabled, // `json:"teamMemesEnabled"`
    boolean teamMention, // `json:"teamMention"`
    boolean uninstallApp, // `json:"uninstallApp"`
    boolean updatelongegration, // `json:"updatelongegration"`
    boolean updateTopic, // `json:"updateTopic"`
    boolean uploadCustomApp // `json:"uploadCustomApp"`
    ) {

  }

  public static record TeamStatus(
    long exchangeTeamCreationStatus,
    long sharePolongSiteCreationStatus,
    long teamNotebookCreationStatus,
    long exchangeTeamDeletionStatus) {

  }

  public static record MembershipSummary(
    long botCount, // `json:"botCount"`
    long mutedMembersCount, // `json:"mutedMembersCount"`
    long totalMemberCount,
    long adminRoleCount,
    long userRoleCount,
    long guestRoleCount) {

  }

  public static record TeamSiteInformation(
    String groupId,
    String sharepolongSiteUrl,
    String notebookId,
    boolean isOneNoteProvisioned) {

  }

  public static record ExtensionDefinition(
    OffsetDateTime updatedTime) {

  }

  public static record Team(
    long accessType,// `json:"accessType"`
    boolean channelOnlyMember,// `json:"channelOnlyMember"`
    List<Channels> channel,// `json:"channels"`
    String classification,// `json:"classification"`
    String conversationVersion,// `json:"conversationVersion"`
    String creator,// `json:"creator"`
    String description,// `json:"description"`
    String displayName,// `json:"displayName"`
    boolean dynamicMembership,// `json:"dynamicMembership"`
    String guestUsersCategory,// `json:"guestUsersCategory"`
    String id,// `json:"id"`
    boolean isArchived,// `json:"isArchived"`
    boolean isCollapsed,// `json:"isCollapsed"`
    boolean isCreator,// `json:"isCreator"`
    boolean isDeleted,// `json:"isDeleted"`
    boolean isFavorite,// `json:"isFavorite"`
    boolean isFollowed,// `json:"isFollowed"`
    boolean isTeamLocked,// `json:"isTeamLocked"`
    boolean isTenantWide,// `json:"isTenantWide"`
    boolean isUnlockMembershipSyncRequired,// `json:"isUnlockMembershipSyncRequired"`
    boolean isUserMuted,// `json:"isUserMuted"`
    String lastJoinAt,// `json:"lastJoinAt"`
    boolean maximumMemberLimitExceeded,// `json:"maximumMemberLimitExceeded"`
    long memberRole,// `json:"memberRole"`
    long membershipExpiry,// `json:"membershipExpiry"`
    MembershipSummary membershipSummary,// `json:"membershipSummary"` *MembershipSummary
    long membershipVersion,// `json:"membershipVersion"`
    String pictureETag,// `json:"pictureETag"`
    String smtpAddress,// `json:"smtpAddress"`
    TeamSettings teamGuestSettings,// `json:"teamGuestSettings"`
    TeamSettings teamSettings,// `json:"teamSettings"`
    TeamSiteInformation teamSiteInformation, // `json:"teamSiteInformation"`
    TeamStatus teamStatus,// `json:"teamStatus"`
    long teamType,// `json:"teamType"`
    ExtensionDefinition extensionDefinition, // `json:"extensionDefinition"`
    String tenantId,// `json:"tenantId"`
    String threadSchemaVersion,// `json:"threadSchemaVersion,omitempty"`
    String threadVersion// `json:"threadVersion"`
    ) {

  }

}

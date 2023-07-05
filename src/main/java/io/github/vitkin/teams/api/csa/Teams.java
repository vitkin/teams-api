package io.github.vitkin.teams.api.csa;

import java.time.LocalDate;
import java.util.List;
import javax.json.bind.annotation.JsonbCreator;

/**
 *
 */
public class Teams {

  public static record User() {

    @JsonbCreator
    public User {
    }
  }

  public static record FeedProperty(
    String isEmptyConversation,
    String consumptionHorizon,
    String consumptionHorizonBookmark) {

    @JsonbCreator
    public FeedProperty   {
    }
  }

  public static record PrivateFeed(
    String id,
    String type,
    int version,
    FeedProperty properties,
    Message lastMessage,
    String messages,
    String targetLink,
    String streamType) {

    @JsonbCreator
    public PrivateFeed        {
    }
  }

  public static record ConversationMetadata(
    String syncToken,
    boolean isPartialData) {

    @JsonbCreator
    public ConversationMetadata  {
    }
  }

  public static record ChatMember(
    boolean isMuted,
    String mri,
    String role,
    String friendlyName,
    String tenantId,
    String objectId) {

    @JsonbCreator
    public ChatMember      {
    }
  }

  public static record DateRange(
    LocalDate startDate,
    LocalDate endDate) {

    @JsonbCreator
    public DateRange  {
    }
  }

  public static record WeeklyRecurrence(
    int interval,
    List<Integer> daysOfTheWeek) {

    @JsonbCreator
    public WeeklyRecurrence  {
    }
  }

  public static record MonthlyRecurrence(
    int interval,
    int weekOfTheMonthIndex,
    int dayOfTheWeek) {

    @JsonbCreator
    public MonthlyRecurrence   {
    }
  }

  public static record RecurrencePattern(
    int patternType,
    WeeklyRecurrence weekly,
    MonthlyRecurrence relativeMonthly) {

    @JsonbCreator
    public RecurrencePattern   {
    }
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
    int appointmentType,
    int meetingType,
    String scenario,
    String tenantId //`json:"tenantId"`
    ) {

    @JsonbCreator
    public MeetingInfo             {
    }
  }

  public static record Chat(
    int chatSubType, //`json:"chatSubType"`
    String chatType, //`json:"chatType"`
    ConsumptionHorizon consumptionHorizon, //`json:"consumptionHorizon"`
    int conversationBlockedAt, //`json:"conversationBlockedAt"`
    String createdAt, //`json:"createdAt"`
    String creator, //`json:"creator"`
    boolean hasTranscript, //`json:"hasTranscript"`
    boolean hidden, //`json:"hidden"`
    String id, //`json:"id"`
    String interopConversationStatus, //`json:"interopConversationStatus"`
    int interopType, //`json:"interopType"`
    boolean isDisabled, //`json:"isDisabled"`
    boolean isGapDetectionEnabled, //`json:"isGapDetectionEnabled"`
    boolean isHighImportance, //`json:"isHighImportance"`
    boolean isLastMessageFromMe, //`json:"isLastMessageFromMe"`
    boolean isMessagingDisabled, //`json:"isMessagingDisabled"`
    boolean isOneOnOne, //`json:"isOneOnOne"`
    boolean isRead, //`json:"isRead"`
    boolean isSticky, //`json:"isSticky"`
    boolean isShared, //`json:"isShared"`
    LocalDate lastJoinAt, //`json:"lastJoinAt"`
    LocalDate lastLeaveAt, //`json:"lastLeaveAt"`
    Message lastMessage, //`json:"lastMessage"`
    MeetingInfo meetingInformation, //`json:"meetingInformation"`
    String meetingPolicy, //`json:"meetingPolicy"`
    List<ChatMember> members, //`json:"members"`
    String retentionHorizon, //`json:"retentionHorizon"`
    String retentionHorizonV2, //`json:"retentionHorizonV2"`
    String shareHistoryFromTime, //`json:"shareHistoryFromTime"`
    List<Tab> tabs, //`json:"tabs"`
    String tenantId, //`json:"tenantId"`
    int threadVersion, //`json:"threadVersion"`
    String threadSchemaVersion, //`json:"threadSchemaVersion,omitempty"`
    String title, //`json:"title"`
    ConsumptionHorizon userConsumptionHorizon, //`json:"userConsumptionHorizon"`
    int version//`json:"version"`
    ) {

    @JsonbCreator
    public Chat                                    {
    }
  }

  public static record ConversationResponse(
    List<Chat> chats,
    ConversationMetadata metadata,
    List<PrivateFeed> privateFeeds,
    List<Team> teams,
    List<User> users) {

    @JsonbCreator
    public ConversationResponse     {
    }
  }

  public static record ConsumptionHorizon(
    int originalArrivalTime,
    int timeStamp,
    String clientMessageId) {

    @JsonbCreator
    public ConsumptionHorizon   {
    }
  }

  public static record RetentionHorizon() {

    @JsonbCreator
    public RetentionHorizon {
    }
  }

  public static record RetentionHorizonV2() {

    @JsonbCreator
    public RetentionHorizonV2 {
    }
  }

  public static record FileSettings(
    String filesRelativePath,
    String documentLibraryId,
    String sharepointRootLibrary) {

    @JsonbCreator
    public FileSettings   {
    }
  }

  public static record Tab(
    String id,
    String name,
    String definitionId,
    String directive,
    String tabType,
    float order,
    int replyChainId,
    Object settings //interface{}
    ) {

    @JsonbCreator
    public Tab        {
    }
  }

  public static record Message(
    String messageType,
    String content,
    String clientMessageId,
    String imDisplayName,
    String id,
    String type,
    LocalDate composeTime, //api.RFC3339Time
    LocalDate originalArrivalTime, //api.RFC3339Time
    String containerId,
    String parentMessageId,
    String from,
    int sequenceId,
    int version,
    String threadType, //*String
    boolean isEscalationToNewPerson) {

    @JsonbCreator
    public Message               {
    }
  }

  public static record ConnectorProfile(
    String avatarUrl,
    String displayName,
    String incomingUrl, // *String
    String connectorType,
    String id) {

    @JsonbCreator
    public ConnectorProfile     {
    }
  }

  public static record ChannelSettings(
    int channelPostPermissions,
    int channelReplyPermissions,
    int channelPinPostPermissions,
    int channelConnectorsPostPermissions,
    int channelBotsPostPermissions) {

    @JsonbCreator
    public ChannelSettings     {
    }
  }

  public static record ActiveMeetup(
    String messageId,
    String conversationURL, //`json:"conversationUrl"`
    String conversationId, //`json:"conversationId"`
    String groupCallInitiator, //`json:"groupCallInitiator"`
    String wasInitiatorInLobby, //`json:"wasInitiatorInLobby"`
    LocalDate expiration, //`json:"expiration"`
    String status, //`json:"status"`
    boolean isHostless, //`json:"isHostless"`
    String tenantId, //`json:"tenantId"`
    String organizerId, //`json:"organizerId"`
    int callMeetingType, //`json:"callMeetingType"`
    String conversationType //`json:"conversationType"`
    ) {

    @JsonbCreator
    public ActiveMeetup            {
    }
  }

  public static record Channel(
    String id,
    String displayName,
    String description,
    ConsumptionHorizon consumptionHorizon, //*ConsumptionHorizon
    RetentionHorizon retentionHorizon, //*RetentionHorizon
    RetentionHorizonV2 retentionHorizonV2, //*RetentionHorizonV2
    int version,
    int threadVersion,
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
    LocalDate lastImportantMessageTime,
    LocalDate lastLeaveAt,
    LocalDate lastJoinAt,
    int memberRole,
    boolean isMuted,
    int membershipExpiry,
    List<ActiveMeetup> activeMeetups,
    boolean isFavoriteByDefault,
    LocalDate creationTime,
    boolean isArchived,
    int channelType,
    ChannelSettings channelSettings,
    int membershipVersion,
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

    @JsonbCreator
    public Channel                                               {
    }
  }

  public static record TeamSettings(
    boolean addDisplayContent, // `json:"addDisplayContent"`
    boolean adminDeleteEnabled, // `json:"adminDeleteEnabled"`
    boolean channelMention, // `json:"channelMention"`
    boolean createIntegration, // `json:"createIntegration"`
    boolean createTab, // `json:"createTab"`
    boolean createTopic, // `json:"createTopic"`
    boolean customMemesEnabled, // `json:"customMemesEnabled"`
    boolean deleteEnabled, // `json:"deleteEnabled"`
    boolean deleteIntegration, // `json:"deleteIntegration"`
    boolean deleteTab, // `json:"deleteTab"`
    boolean deleteTopic, // `json:"deleteTopic"`
    boolean editEnabled, // `json:"editEnabled"`
    int generalChannelPosting,// `json:"generalChannelPosting"`
    boolean giphyEnabled, // `json:"giphyEnabled"`
    int giphyRating,// `json:"giphyRating"`
    boolean installApp, // `json:"installApp"`
    boolean isPrivateChannelCreationEnabled, // `json:"isPrivateChannelCreationEnabled"`
    boolean messageThreadingEnabled, // `json:"messageThreadingEnabled"`
    boolean removeDisplayContent, // `json:"removeDisplayContent"`
    boolean stickersEnabled, // `json:"stickersEnabled"`
    boolean teamMemesEnabled, // `json:"teamMemesEnabled"`
    boolean teamMention, // `json:"teamMention"`
    boolean uninstallApp, // `json:"uninstallApp"`
    boolean updateIntegration, // `json:"updateIntegration"`
    boolean updateTopic, // `json:"updateTopic"`
    boolean uploadCustomApp // `json:"uploadCustomApp"`
    ) {

    @JsonbCreator
    public TeamSettings                          {
    }
  }

  public static record TeamStatus(
    int exchangeTeamCreationStatus,
    int sharePointSiteCreationStatus,
    int teamNotebookCreationStatus,
    int exchangeTeamDeletionStatus) {

    @JsonbCreator
    public TeamStatus    {
    }
  }

  public static record MembershipSummary(
    int botCount, // `json:"botCount"`
    int mutedMembersCount, // `json:"mutedMembersCount"`
    int totalMemberCount,
    int adminRoleCount,
    int userRoleCount,
    int guestRoleCount) {

    @JsonbCreator
    public MembershipSummary      {
    }
  }

  public static record TeamSiteInformation(
    String groupId,
    String sharepointSiteUrl,
    String notebookId,
    boolean isOneNoteProvisioned) {

    @JsonbCreator
    public TeamSiteInformation    {
    }
  }

  public static record ExtensionDefinition(
    LocalDate updatedTime) {

    @JsonbCreator
    public ExtensionDefinition {
    }
  }

  public static record Team(
    int accessType,// `json:"accessType"`
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
    int memberRole,// `json:"memberRole"`
    int membershipExpiry,// `json:"membershipExpiry"`
    MembershipSummary membershipSummary,// `json:"membershipSummary"` *MembershipSummary
    int membershipVersion,// `json:"membershipVersion"`
    String pictureETag,// `json:"pictureETag"`
    String smtpAddress,// `json:"smtpAddress"`
    TeamSettings teamGuestSettings,// `json:"teamGuestSettings"`
    TeamSettings teamSettings,// `json:"teamSettings"`
    TeamSiteInformation teamSiteInformation, // `json:"teamSiteInformation"`
    TeamStatus teamStatus,// `json:"teamStatus"`
    int teamType,// `json:"teamType"`
    ExtensionDefinition extensionDefinition, // `json:"extensionDefinition"`
    String tenantId,// `json:"tenantId"`
    String threadSchemaVersion,// `json:"threadSchemaVersion,omitempty"`
    String threadVersion// `json:"threadVersion"`
    ) {

    @JsonbCreator
    public Team                                      {
    }
  }

}

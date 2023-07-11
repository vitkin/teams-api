package io.github.vitkin.teams.api.csa;

//import java.time.Date;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

/**
 *
 */
public class Teams {

  public static record ConversationResponse(
    List<Team> teams,
    List<Chat> chats,
    List<User> users,
    List<PrivateFeed> privateFeeds,
    ConversationMetadata metadata) {

  }

  public static record Team(
    String displayName,
    String id,
    List<Channel> channels,
    String pictureETag,
    String description,
    boolean isFavorite,
    boolean isCollapsed,
    boolean isDeleted,
    boolean isTenantWide,
    String smtpAddress,
    String threadVersion,
    String threadSchemaVersion,
    String conversationVersion,
    String classification,
    long accessType,
    String guestUsersCategory,
    boolean dynamicMembership,
    boolean maximumMemberLimitExceeded,
    TeamSettings teamSettings,
    TeamSettings teamGuestSettings,
    TeamStatus teamStatus,
    TeamSiteInformation teamSiteInformation,
    boolean isCreator,
    String creator,
    long membershipVersion,
    MembershipSummary membershipSummary,
    boolean isUserMuted,
    String lastJoinAt,
    long membershipExpiry,
    long memberRole,
    boolean isFollowed,
    String tenantId,
    long teamType,
    GroupInformation groupInformation,
    boolean isArchived,
    boolean isTeamLocked,
    boolean isUnlockMembershipSyncRequired,
    boolean channelOnlyMember,
    long rosterVersion) {

  }

  public static record Chat(
    String id,
    ConsumptionHorizon consumptionHorizon,
    ConsumptionHorizon userConsumptionHorizon,
    String retentionHorizon,
    String retentionHorizonV2,
    List<ChatMember> members,
    String title,
    long version,
    long threadVersion,
    boolean isRead,
    boolean isHighImportance,
    boolean isOneOnOne,
    Message lastMessage,
    boolean isLastMessageFromMe,
    long chatSubType,
    MeetingInfo meetingInformation,
    OffsetDateTime lastJoinAt,
    OffsetDateTime lastLeaveAt,
    String createdAt,
    String creator,
    String tenantId,
    boolean hidden,
    boolean isGapDetectionEnabled,
    long interopType,
    ActiveMeetup activeMeetup,
    List<Tab> tabs,
    boolean isConversationDeleted,
    boolean isExternal,
    boolean isMessagingDisabled,
    String shareHistoryFromTime,
    boolean isDisabled,
    String importState,
    String chatType,
    String interopConversationStatus,
    long conversationBlockedAt,
    boolean hasTranscript,
    QuickReplyAugmentation quickReplyAugmentation,
    String templateType,
    boolean isSticky,
    String meetingPolicy,
    long draftVersion,
    long rosterVersion,
    boolean identityMaskEnabled) {

  }

  public static record User() {

  }

  public static record PrivateFeed(
    String id,
    String type,
    long version,
    FeedProperties properties,
    Message lastMessage,
    String messages,
    String targetLink,
    String streamType) {

  }

  public static record ConversationMetadata(
    String syncToken,
    String forwardSyncToken,
    boolean isPartialData,
    boolean hasMoreChats) {

  }

  public static record Channel(
    String id,
    String displayName,
    String description,
    ConsumptionHorizon consumptionHorizon,
    long version,
    long threadVersion,
    String threadSchemaVersion,
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
    List<ConnectorProfile> connectorProfiles,
    Message lastMessage,
    OffsetDateTime lastImportantMessageTime,
    boolean isDeleted,
    boolean isPinned,
    OffsetDateTime lastJoinAt,
    String threadSubType,
    OffsetDateTime lastLeaveAt,
    long memberRole,
    boolean isMuted,
    long membershipExpiry,
    boolean isFavoriteByDefault,
    OffsetDateTime creationTime,
    boolean isArchived,
    long channelType,
    TeamSettings memberSettings,
    TeamSettings guestSettings,
    ChannelSettings channelSettings,
    long membershipVersion,
    MembershipSummary membershipSummary,
    String sharepointSiteUrl,
    boolean isModerator,
    String groupId,
    boolean channelOnlyMember,
    boolean explicitlyAdded,
    boolean isShared,
    ExtensionDefinition extensionDefinition) {

  }

  public static record ConsumptionHorizon(
    long originalArrivalTime,
    long timeStamp,
    String clientMessageId) {

  }

  public static record FileSettings(
    String filesRelativePath,
    String documentLibraryId,
    String sharepointRootLibrary) {

  }

  public static record Tab(
    String id,
    String name,
    String definitionId,
    String directive,
    String tabType,
    float order,
    long replyChainId,
    Settings settings) {

  }

  public static record Settings(
    long wikiTabId,
    boolean wikiDefaultTab,
    boolean hasContent,
    String subtype, // wiki-tab, webpage, extemsion, calendar-tab, sharepointfiles, powerpointpin, wordpin or excelpin
    String objectId,
    String file,
    String name,
    String url,
    String removeUrl,
    String websiteUrl,
    String entityId,
    String siteUrl,
    String libraryServerRelativeUrl,
    String libraryId,
    String selectedDocumentLibraryTitle,
    String selectedSiteTitle,
    OffsetDateTime dateAdded,
    boolean isPrivateMeetingWiki,
    boolean meetingNotes,
    String scenarioName) {

  }

  public static record ConnectorProfile(
    String avatarUrl,
    String displayName,
    String incomingUrl,
    String connectorType,
    String id) {

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
    String threadType,
    boolean isEscalationToNewPerson) {

  }

  public static record TeamSettings(
    boolean createTopic,
    boolean updateTopic,
    boolean deleteTopic,
    boolean createTab,
    boolean deleteTab,
    boolean createIntegration,
    boolean updateIntegration,
    boolean deleteIntegration,
    boolean giphyEnabled,
    boolean stickersEnabled,
    long giphyRating,
    boolean customMemesEnabled,
    boolean teamMemesEnabled,
    boolean addDisplayContent,
    boolean removeDisplayContent,
    boolean teamMention,
    boolean channelMention,
    boolean adminDeleteEnabled,
    boolean deleteEnabled,
    boolean editEnabled,
    boolean messageThreadingEnabled,
    long generalChannelPosting,
    boolean installApp,
    boolean uninstallApp,
    boolean isPrivateChannelCreationEnabled,
    boolean uploadCustomApp) {

  }

  public static record ChannelSettings(
    long channelPostPermissions,
    long channelReplyPermissions,
    long channelPinPostPermissions,
    long channelBotsPostPermissions,
    long channelConnectorsPostPermissions) {

  }

  public static record MembershipSummary(
    long botCount,
    long mutedMembersCount,
    long totalMemberCount,
    long adminRoleCount,
    long userRoleCount,
    long guestRoleCount) {

  }

  public static record RetentionHorizon() {

  }

  public static record RetentionHorizonV2() {

  }

  public static record ExtensionDefinition(
    OffsetDateTime updatedTime,
    String tabsEtag
    ) {

  }

  public static record TeamStatus(
    long exchangeTeamCreationStatus,
    long sharePointSiteCreationStatus,
    long teamNotebookCreationStatus,
    long exchangeTeamDeletionStatus) {

  }

  public static record TeamSiteInformation(
    String groupId,
    String sharepointSiteUrl,
    String notebookId,
    boolean isOneNoteProvisioned) {

  }

  public static record GroupInformation(
    boolean isEligibleForRenewal,
    String expirationTime // OffsetDateTime
    ) {

  }

  public static record FeedProperties(
    String consumptionHorizon,
    String isEmptyConversation,
    String consumptionHorizonBookmark) {

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
    long interval,
    List<Long> daysOfTheWeek) {

  }

  public static record MonthlyRecurrence(
    long interval,
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
    long appointmentType,
    long meetingType,
    String scenario,
    String tenantId //`json:"tenantId"`
    ) {

  }

  public static record ActiveMeetup(
    String messageId,
    String conversationURL,
    String conversationId,
    String groupCallInitiator,
    boolean wasInitiatorInLobby,
    OffsetDateTime expiration,
    String status,
    boolean isHostless,
    String tenantId,
    String organizerId,
    long callMeetingType,
    MeetingData meetingData,
    String conversationType) {

  }

  public static record MeetingData(
    String meetingCode) {

  }

  public static record QuickReplyAugmentation(
    List<SuggestedActivity> suggestedActivities) {

  }

  public static record SuggestedActivity(
    String type,
    // List<SuggestedAction> suggestedActions,
    String id,
    OffsetDateTime timestamp,
    From from,
    Conversation conversation,
    String replyToId) {

  }

  public static record SuggestedAction(
    List<Action> actions
       ) {

  }

  public static record Action(
    String type,
    String title,
    String value,
    ChannelData channelData) {

  }

  public static record ChannelData(
    String type,
    String device,
    String id,
    OffsetDateTime utcTime,
    String targetMessageId) {

  }

  public static record From(
    String id,
    String name) {

  }

  public static record Conversation(
    boolean isGroup,
    String id) {

  }
}

package io.github.vitkin.teams.api.csa;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class Messages {

  static final String CHAT_MESSAGE_TYPE_MESSAGE = "Message";
  static final String EVENT_CALL = "Event/Call";
  static final String THREAD_ACTIVITY_ADD_MEMBER = "ThreadActivity/AddMember";

  public static record UserEmotion(
    String mri,
    long time, // TODO: Convert to time.time ?
    String value) {

  }

  public static record Emotion(
    String key,
    List<UserEmotion> users) {

  }

  public static record DeltaEmotion(
    String key,
    List<UserEmotion> users) {

  }

  public static record ChatMessageProperties(
    String subject,
    String title,
    String emailDetails,
    String meta,
    String files,
    List<Emotion> emotions,
    List<DeltaEmotion> deltaEmotions,
    long deleteTime, // TODO: Convert to time.Time ?
    boolean adminDelete,
    String s2SPartnerName,
    String mentions,
    String links,
    Object editTime,// Can be either String or long, wtf? TODO: Convert to time.Time ?
    long counterPartyMessageId,
    long originContextId,
    long parentMessageId,
    Object skipFanOutToBots,// Can be either String or boolean, wtf?
    String cards,
    String importance,
    String atp,
    String crossPostId,
    String meeting,
    String skypeGuid) {

  }

  public static record AnnotationsSummary(
    Map<String, Long> emotions) {

  }

  public static record ChatMessage(
    String id,
    long sequenceId,
    String skypeEditedId,
    long skypeEditOffset,
    String clientMessageId,
    String version,
    String conversationId,
    String conversationLink,
    String type,
    String messageType,
    String contentType,
    String content,
    List<String> amsReferences,
    String from,
    String imDisplayName,
    String s2SPartnerName,
    Date composeTime, //api.RFC3339Time
    Date originalArrivalTime, //api.RFC3339Time
    ChatMessageProperties properties,
    AnnotationsSummary annotationsSummary) {

  }

  public static record MessagesMetadata(
    String backwardLink,
    String syncState,
    long lastCompleteSegmentStartTime, // TODO: Parse as time.Time
    long lastCompleteSegmentEndTime // TODO: Parse as time.Time
    ) {

  }

  public static record MessagesResponse(
    List<ChatMessage> messages, //`json:"messages"`
    MessagesMetadata metadata //`json:"_metadata"`
    ) {

  }

}

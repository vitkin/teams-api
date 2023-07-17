package io.github.vitkin.teams.api.csa;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class Conversation {

  static final String CHAT_MESSAGE_TYPE_MESSAGE = "Message";
  static final String EVENT_CALL = "Event/Call";
  static final String THREAD_ACTIVITY_ADD_MEMBER = "ThreadActivity/AddMember";

  public static record Response(
    List<Message> messages,
    String tenantd,
    Metadata _metadata) {

  }

  public static record Message(
    long sequenceId,
    String conversationid,
    String conversationLink,
    String contenttype,
    String type,
    String s2spartnername,
    List<String> amsreferences,
    AnnotationsSummary annotationsSummary,
    String id,
    String clientmessageid,
    String version,
    String messagetype,
    String content,
    String from,
    String imdisplayname,
    Date composetime, //api.RFC3339Time
    Date originalarrivaltime, //api.RFC3339Time
    Properties properties,
    String skypeEditedId,
    long skypeEditOffset) {

  }

  public static record AnnotationsSummary(
    Map<String, Long> emotions // "like": 1
    ) {

  }

  public static record Properties(
    String subject,
    String title,
    String emailDetails,
    String meta,
    String files,
    List<Emotion> emotions,
    List<DeltaEmotion> deltaEmotions,
    long deleteTime, // TODO: Convert to time.Time ?
    boolean adminDelete,
    String s2spartnername,
    String mentions,
    Object edittime,// Can be either String or long, wtf? TODO: Convert to time.Time ?
    long counterPartyMessageId,
    long origincontextid,
    long parentMessageId,
    Object skipFanOutToBots,// Can be either String or boolean, wtf?
    String cards,
    String importance,
    String atp,
    String crossPostId,
    String meeting,
    String skypeGuid,
    String links,
    String languageStamp) {

  }

  public static record Emotion(
    String key,
    List<UserEmotion> users) {

  }

  public static record DeltaEmotion(
    String key,
    List<UserEmotion> users) {

  }

  public static record UserEmotion(
    String mri,
    long time, // TODO: Convert to time.time ?
    String value) {

  }

  public static record Metadata(
    long lastCompleteSegmentStartTime, // TODO: Parse as time.Time
    long lastCompleteSegmentEndTime, // TODO: Parse as time.Time
    String forwardLink,
    String backwardLink,
    String syncState) {

  }
}

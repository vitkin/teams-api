package io.github.vitkin.teams.api.csa;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.json.bind.annotation.JsonbCreator;

/**
 *
 */
public class Messages {

  static final String ChatMessageTypeMessage = "Message";
  static final String EvenCall = "Event/Call";
  static final String ThreadActivityAddMember = "ThreadActivity/AddMember";

  public static record UserEmotion(
    String mri,
    double time, // TODO: Convert to time.time ?
    String value) {

    @JsonbCreator
    public UserEmotion   {
    }
  }

  public static record Emotion(
    String key,
    List<UserEmotion> users) {

    @JsonbCreator
    public Emotion  {
    }
  }

  public static record DeltaEmotion(
    String key,
    List<UserEmotion> users) {

    @JsonbCreator
    public DeltaEmotion  {
    }
  }

  public static record ChatMessageProperties(
    String subject,
    String title,
    String emailDetails,
    String meta,
    String files,
    List<Emotion> emotions,
    List<DeltaEmotion> deltaEmotions,
    double deleteTime, // TODO: Convert to time.Time ?
    boolean adminDelete,
    String s2SPartnerName,
    String mentions,
    String links,
    Object editTime,// Can be either String or double, wtf? TODO: Convert to time.Time ?
    double counterPartyMessageId,
    double originContextId,
    double parentMessageId,
    Object skipFanOutToBots,// Can be either String or boolean, wtf?
    String cards,
    String importance,
    String atp,
    String crossPostId,
    String meeting,
    String skypeGuid) {

    @JsonbCreator
    public ChatMessageProperties                       {
    }
  }

  public static record AnnotationsSummary(
    Map<String, Double> emotions) {

    @JsonbCreator
    public AnnotationsSummary {
    }
  }

  public static record ChatMessage(
    String id,
    double sequenceId,
    String skypeEditedId,
    int skypeEditOffset,
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

    @JsonbCreator
    public ChatMessage                    {
    }
  }

  public static record MessagesMetadata(
    String backwardLink,
    String syncState,
    double lastCompleteSegmentStartTime, // TODO: Parse as time.Time
    double lastCompleteSegmentEndTime // TODO: Parse as time.Time
    ) {

    @JsonbCreator
    public MessagesMetadata    {
    }
  }

  public static record MessagesResponse(
    List<ChatMessage> messages, //`json:"messages"`
    MessagesMetadata metadata //`json:"_metadata"`
    ) {

    @JsonbCreator
    public MessagesResponse  {
    }
  }

}

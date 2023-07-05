package sandbox;

import io.github.vitkin.teams.api.csa.Channels;
import java.time.LocalDate;
import java.util.List;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import lombok.extern.log4j.Log4j2;

/**
 *
 */
@Log4j2
public class Main {

  public static void main(String... args) {
    var person = new Person(
      "John",
      "Doe",
      "USA",
      LocalDate.of(1990, 11, 11),
      List.of("Speaker")
    );

    var expected = """
      {
          "achievements": [
              "Speaker"
          ],
          "address": "USA",
          "birthday": "1990-11-11",
          "firstName": "John",
          "lastName": "Doe"
      }
      """;

    var jsonb = JsonbBuilder.create(new JsonbConfig().withFormatting(true));

    var serialized = jsonb.toJson(person);

    log.info(serialized);

    var json = """
      {
          "achievements": [
              "Speaker"
          ],
          "address": "USA",
          "birthday": "1990-11-11",
          "firstName": "John",
          "lastName": "Doe"
      }
    """;

    var deserialized = jsonb.fromJson(json, Person.class);

    log.info(deserialized::firstName);

    var j = """
            {"orderVersion":1659616855316,"pinChannelOrder":["19:5d1c8fba9fcb49fcae742c92bfeca00b@thread.tacv2","19:1c6015899783435d91667e0231b40617@thread.tacv2","19:ef8b3d7f7f964b80b936883d0d53a7da@thread.tacv2"]}
            """;

    var d = jsonb.fromJson(j, Channels.PinnedChannelsResponse.class);

    log.info(d::orderVersion);
  }
}

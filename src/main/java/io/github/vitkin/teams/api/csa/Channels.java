package io.github.vitkin.teams.api.csa;

import java.util.List;
import javax.json.bind.annotation.JsonbCreator;

/**
 *
 */
public class Channels {

  public static record PinnedChannelsResponse(
    long orderVersion,
    List<String> pinChannelOrder) {

    @JsonbCreator
    public PinnedChannelsResponse  {
    }
  }
}

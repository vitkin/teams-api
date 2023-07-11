package io.github.vitkin.teams.api.csa;

import java.util.List;

/**
 *
 */
public class Channels {

  public static record PinnedChannelsResponse(
    long orderVersion,
    List<String> pinChannelOrder) {
  }
}

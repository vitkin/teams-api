package io.github.vitkin.teams.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;

import io.github.vitkin.teams.api.csa.CsaSvc;
import io.github.vitkin.teams.api.csa.Messages.ChatMessage;
import io.github.vitkin.teams.api.csa.Teams.Channel;
import io.github.vitkin.teams.api.csa.Teams.ConversationResponse;
import io.github.vitkin.teams.api.models.Models.Tenant;
import io.github.vitkin.teams.api.models.Models.User;
import io.github.vitkin.teams.api.mt.MtService;
import lombok.extern.log4j.Log4j2;

/**
 *
 */
@Log4j2
public class TeamsClient {

  /**
   *
   * @param args
   * @throws Exception
   */
  public static void main(String... args) throws Exception {
    
    // System.setProperty("https.proxyHost", "localhost");
    // System.setProperty("https.proxyPort", "8000");

    var client = new TeamsClient();
    
    // var me = client.getMe();

    var pinnedChannels = client.getPinnedChannels();
    System.out.println(pinnedChannels);
    
    var conversations = client.getConversations();
    System.out.println(conversations);
    
    // var conversation = client.getMessages(pinnedChannels.get(0));
    // System.out.println(conversation);
  }

  /**
   *    */
  CsaSvc chatSvc;

  /**
   *    */
  MtService mtSvc;

  /**
   *
   * @throws Exception
   */
  public TeamsClient() throws Exception {

    // Get Skype Spaces Token
    var skypeSpaces = new SkypeSpacesToken();
    var chatSvcToken = new ChatSvcAggToken();
    var skypeToken = new SkypeToken();

    // Initialize Chat Service
    chatSvc = new CsaSvc(chatSvcToken, skypeToken);

    // Initialize MT Service
    mtSvc = new MtService("emea", skypeSpaces);
  }

  /**
   *
   * @param debugFlag
   */
  public void debug(boolean debugFlag) {
    chatSvc.debugSave(debugFlag);
  }

  /**
   *
   * @return
   */
  public CsaSvc chatSvc() {
    return chatSvc;
  }

  /**
   *
   * @return @throws IOException
   * @throws InterruptedException
   */
  ConversationResponse getConversations() throws IOException, InterruptedException {

    return this.chatSvc.getConversations();
  }

  /**
   *
   * @param channel
   * @return
   * @throws IOException
   * @throws InterruptedException
   */
  List<ChatMessage> getMessages(String id) throws IOException, InterruptedException {

    return this.chatSvc.getMessagesById(id);
  }
  
  /**
   *
   * @param channel
   * @return
   * @throws IOException
   * @throws InterruptedException
   */
  List<ChatMessage> getMessages(Channel channel) throws IOException, InterruptedException {

    return this.chatSvc.getMessagesByChannel(channel);
  }

  /**
   *
   * @return @throws ParseException
   * @throws IOException
   * @throws UnsupportedEncodingException
   * @throws InterruptedException
   */
  User getMe() throws ParseException, IOException, UnsupportedEncodingException, InterruptedException {

    return this.mtSvc.getMe();
  }

  /**
   *
   * @param mris
   * @return
   * @throws IOException
   * @throws InterruptedException
   */
  List<User> fetchShortProfile(String... mris) throws IOException, InterruptedException {

    return this.mtSvc.fetchShortProfile(mris);
  }

  /**
   *
   * @return
   */
  List<Tenant> getTenants() throws IOException, InterruptedException {

    return this.mtSvc.getTenants();
  }

  /**
   *
   * @return @throws IOException
   * @throws InterruptedException
   */
  List<String> getPinnedChannels() throws IOException, InterruptedException {

    return chatSvc.getPinnedChannels();
  }
}

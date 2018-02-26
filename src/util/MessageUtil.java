package util;

import java.util.ArrayList;

import org.json.JSONObject;

import socket.ChatConnectHandler;
import topic.TopicUtil;

public class MessageUtil {
	public static void handleTopicMessages(JSONObject messageJson){
		try{
			ArrayList<String> usersRelatedToTopic = TopicUtil.getAllRelatedUsersToTopic(messageJson.getString("topicid"));
			ChatConnectHandler.sendMessageToUserList(usersRelatedToTopic,messageJson.toString());
		}catch(Exception e){
			
		}
	}
}

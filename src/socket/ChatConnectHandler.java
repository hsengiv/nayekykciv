package socket;

import java.util.ArrayList;
import java.util.HashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import user.User;
import util.MessageUtil;
import util.UserUtil;

@ServerEndpoint(value = "/chat/connect/{id}")
public class ChatConnectHandler {
	private Session session;
	private String id;
	private String userName;
	private String name;
	private static HashMap<String,ArrayList<ChatConnectHandler>> connections = new HashMap<String,ArrayList<ChatConnectHandler>>();

	@OnOpen
	public void open(@PathParam("id") String id, Session session) {
		this.id = id;
		this.session = session;
		User user = UserUtil.getUserObjectUID(id);
		this.name = user.getName();
		this.userName = user.getUname();
		addSession(this);
	}

	@OnClose
	public void close() {
		removeSession(this);
	}

	@OnMessage
	public void message(String message) {
		try {

			JSONObject messageJson = new JSONObject(message);
			messageJson.put("sendername", this.name);
			messageJson.put("senderusername", this.userName);
			messageJson.put("senderid", this.id);
			if(messageJson.has("type")){
				String type =  messageJson.getString("type");
				if(type.equals("topicmessage")){
					MessageUtil.handleTopicMessages(messageJson);
				}
			}
		} catch (Exception e) {

		}
	}

	@OnError
	public void onError(Throwable t) throws Throwable {
		removeSession(this);
	}

	
	public static void sendMessageToUserList(ArrayList<String> userIds,String message){
		try{
			for(String userId : userIds){
				if(connections.containsKey(userId)){
					ArrayList<ChatConnectHandler> sessionList = connections.get(userId);
					for(ChatConnectHandler obj : sessionList){
						obj.session.getBasicRemote().sendText(message);
					}
				}
			}
		}catch(Exception e){
			
		}
	}
	public static void addSession(ChatConnectHandler obj){
		try{
			if(obj.connections.containsKey(obj.id)){
				obj.connections.get(obj.id).add(obj);;
			}else{
				ArrayList<ChatConnectHandler> connectionsList = new ArrayList<ChatConnectHandler>();
				connectionsList.add(obj);
				connections.put(obj.id, connectionsList);
			}
		}catch(Exception e){
			
		}
	}
	public static void removeSession(ChatConnectHandler currObj){
		try{
			ArrayList<ChatConnectHandler> list = currObj.connections.get(currObj.id);
			for(ChatConnectHandler obj : list){
				if(currObj.session.equals(obj.session)){
					list.remove(obj);
				}
			}
		}catch(Exception e){
			
		}
		
	}
}
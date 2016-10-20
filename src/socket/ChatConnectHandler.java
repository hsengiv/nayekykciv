package socket;

import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat/connect/{name}")
public class ChatConnectHandler{
	private Session session;
    private String name;
    private static ArrayList<ChatConnectHandler> connections = new ArrayList<ChatConnectHandler>();
	@OnOpen
    public void open(@PathParam("name") String name,Session session) {
        this.name = name;
    	this.session = session;
        connections.add(this);
        this.sendToAll(name + " joined room");
    }
    @OnClose
    public void close() {
        this.sendToAll(this.name + " left the room");
    }
    @OnMessage
    public void message(String message) {
        try{

    	this.sendToAll(this.name+" : "+message);
    }catch(Exception e){

    }
    }
    @OnError
    public void onError(Throwable t) throws Throwable {
        this.sendToAll(this.name + " left the room");
    }
    public static void sendToAll(String message){
        for(ChatConnectHandler obj : connections){
            try{
             obj.session.getBasicRemote().sendText(message);
         }catch(Exception e){

     }
        }
    }
}
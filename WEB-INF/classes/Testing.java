import javax.websocket.*;
import java.io.IOException;
import javax.websocket.server.ServerEndpoint;
import java.util.*;

import javax.websocket.server.*;
@ServerEndpoint(value = "/testing/chat/{name}")
public class Testing{
	private Session session;
    private String name;
    private static ArrayList<Testing> connections = new ArrayList<Testing>();
	@OnOpen
    public void start(@PathParam("name") String name,Session session) {
        this.name = name;
    	this.session = session;
        connections.add(this);
        this.sendToAll(name + " joined room");
    }
    @OnClose
    public void end() {
        this.sendToAll(this.name + " left the room");
    }
    @OnMessage
    public void incoming(String message) {
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
        for(Testing obj : connections){
            try{
             obj.session.getBasicRemote().sendText(message);
         }catch(Exception e){

     }
        }
    }
}
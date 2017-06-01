package cn.izhiju.service;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket/chat") 
public class MessageDeal {
	
	private static final String GUEST_PREFIX = "Guest";
    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    private static final Set<MessageDeal> connections =
            new CopyOnWriteArraySet<>();

    private final String nickname;
    private Session session;

    public MessageDeal() {
        nickname = GUEST_PREFIX + connectionIds.getAndIncrement();
    }

    @OnOpen
    public void start(Session session) {
        this.session = session;
        connections.add(this);
        //String message = String.format("* %s %s", nickname, "has joined.");
        String message="mqtt session start";
        broadcast(message);
    }

    @OnClose
    public void end() {
        connections.remove(this);
       // String message = String.format("* %s %s", nickname, "has disconnected.");
        String message="mqtt session end";
        broadcast(message);
    }


    @OnError
    public void onError(Throwable t) throws Throwable {
    	broadcast("mqtt Error: " + t.toString());
    }
	
	  /** 
     * 消息发送触发方法 
     * @param message 
     */  
    @OnMessage  
    public void incoming(String message) {  
        broadcast(message);  
    } 
    
    private static void broadcast(String msg) {
        for (MessageDeal client : connections) {
            try {
                synchronized (client) {
                    client.session.getBasicRemote().sendText(msg);
                }
            } catch (IOException e) {
            	System.out.println("Chat Error: Failed to send message to client");
                connections.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                    // Ignore
                }
                String message = String.format("* %s %s",
                        client.nickname, "has been disconnected.");
                broadcast(message);
            }
        }
    }

}

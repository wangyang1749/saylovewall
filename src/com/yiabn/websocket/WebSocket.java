package com.yiabn.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.PongMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/websocket")
public class WebSocket {
	Map<Integer, HttpSession> maps = new HashMap<Integer,HttpSession>();
    
	 @OnOpen  
	    public void onOpen(Session session) {  
	       System.out.println("dd");
	    } 
	@OnMessage
    public void echoTextMessage(Session session, String msg, boolean last) {
        try {
            if (session.isOpen()) {
                session.getBasicRemote().sendText("444444"+msg, last);
            }
        } catch (IOException e) {
            try {
                session.close();
            } catch (IOException e1) {
                // Ignore
            }
        }
    }

    @OnMessage
    public void echoBinaryMessage(Session session, ByteBuffer bb,
            boolean last) {
        try {
            if (session.isOpen()) {
                session.getBasicRemote().sendBinary(bb, last);
            }
        } catch (IOException e) {
            try {
                session.close();
            } catch (IOException e1) {
                // Ignore
            }
        }
    }

    /**
     * Process a received pong. This is a NO-OP.
     *
     * @param pm    Ignored.
     */
    @OnMessage
    public void echoPongMessage(PongMessage pm) {
        // NO-OP
    }
}

package cn.yt4j.message.server.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gyv12345@163.com
 */
@Slf4j
@ServerEndpoint(value = "/endpoint", port = "90")
public class Endpoint {

    public static ConcurrentHashMap<String, Endpoint> concurrentHashMap = new ConcurrentHashMap<>();

    private Session session;

    private String userKey="";


    @BeforeHandshake
    public void handshake(Session session, HttpHeaders headers, @RequestParam String userKey) {
        session.setSubprotocols("stomp");
    }

    @OnOpen
    public void onOpen(Session session, HttpHeaders headers, @RequestParam String userKey) {
        this.session = session;
        if (!StringUtils.isEmpty(userKey) && concurrentHashMap.containsKey(userKey)) {
            concurrentHashMap.remove(userKey);
            concurrentHashMap.put(userKey,this);
        }else {
            concurrentHashMap.put(userKey,this);
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        session.sendText("关闭");
        concurrentHashMap.remove(this.userKey);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        session.sendText("{\"message\":1}");
    }

    @OnBinary
    public void onBinary(Session session, byte[] bytes) {
        for (byte b : bytes) {
            System.out.println(b);
        }
        session.sendBinary(bytes);
    }

    @OnEvent
    public void onEvent(Session session, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    System.out.println("read idle");
                    break;
                case WRITER_IDLE:
                    System.out.println("write idle");
                    break;
                case ALL_IDLE:
                    System.out.println("all idle");
                    break;
                default:
                    break;
            }
        }
    }


    public static void send(Object message, String userKey){
        ObjectMapper mapper=new ObjectMapper();
        if (concurrentHashMap.containsKey(userKey)){
            try {
                concurrentHashMap.get(userKey).session.sendText(mapper.writeValueAsString(message));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}

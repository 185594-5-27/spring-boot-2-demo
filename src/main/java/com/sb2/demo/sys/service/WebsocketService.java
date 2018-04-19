package com.sb2.demo.sys.service;


import com.sb2.demo.common.config.websocket.OutMessage;
import com.sb2.demo.common.config.websocket.SocketSessionRegistry;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/*
* 类描述：
* @auther linzf
* @create 2017/11/23 0023 
*/
@Service("websocketService")
@Transactional(rollbackFor={IllegalArgumentException.class})
public class WebsocketService {

    /**session操作类*/
    @Autowired
    private SocketSessionRegistry webAgentSessionRegistry;
    /**消息发送工具*/
    @Autowired
    private SimpMessagingTemplate template;

    /**
     * 功能描述：推送打印订单的消息到页面进行打印
     * @param orderId
     */
    public void printOrder(String orderId,String orderDetailId){
        Set<String> keys = webAgentSessionRegistry.getSessionIds("hyll");
        JSONObject jobj = new JSONObject();
        jobj.put("orderId",orderId);
        jobj.put("orderDetailId",orderDetailId);
        jobj.put("type","1");
        keys.forEach(x->{
            template.convertAndSendToUser(x,"/topic/greetings",new OutMessage(jobj.toString()),createHeaders(x));
        });
    }


    /**
     * 功能描述：组装JSON数据的头部数据
     * @param sessionId
     * @return
     */
    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }

}

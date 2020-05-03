package cn.yt4j.message.core.client;

import cn.yt4j.message.core.model.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能描述：客户端，可以是的feign也可以是dubbo
 *
 * @author: gyv12345@163.com
 * @date: 2020/5/3 16:08
 */
@FeignClient(name = "yt4j-message")
public interface MessageClient {
    /**
     * 没啥说的，发送消息
     * @param message
     */
    @RequestMapping("message")
    void send(Message message);
}

package cn.yt4j.message.server.service;

import cn.yt4j.message.server.dao.MessageDao;
import cn.yt4j.message.core.client.MessageClient;
import cn.yt4j.message.core.model.Message;
import cn.yt4j.message.server.endpoint.Endpoint;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 功能描述：
 *
 * @author: gyv12345@163.com
 * @date: 2020/5/3 16:29
 */
@AllArgsConstructor
@Service
public class MessageServiceImpl implements MessageClient {

    private final MessageDao messageDao;

    @Async
    @Override
    public void send(Message message) {
        this.messageDao.add(message);
        Endpoint.send(message, message.getUserKey());
    }

}

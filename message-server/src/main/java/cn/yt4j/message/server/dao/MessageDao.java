package cn.yt4j.message.server.dao;

import cn.yt4j.message.core.model.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 功能描述：
 *
 * @author: gyv12345@163.com
 * @date: 2020/5/3 16:42
 */
@Mapper
public interface MessageDao {
    /**
     * 查询方法
     * @param message
     * @return
     */
    List<Message> list(Message message);

    /**
     * 添加方法
     * @param message
     * @return
     */
    int add(Message message);

    /**
     * 批量修改，修改时间槽
     * @return
     */
    int taskUpdate();

    /**
     * 这个估计用不到
     * @return
     */
    int update();
}

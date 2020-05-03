package cn.yt4j.message.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author gyv12345@163.com
 * @date 2020年5月3日
 */
@Getter
@Setter
@EqualsAndHashCode()
@ToString(callSuper = true)
public class Message implements Serializable {

    private static final long serialVersionUID = -941913841103579113L;

    private Long id;

    private String userKey;

    private String message;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}

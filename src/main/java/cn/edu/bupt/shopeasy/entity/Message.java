package cn.edu.bupt.shopeasy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Message {
    @TableId(type= IdType.AUTO)
    private Long id; // 消息ID，自增主键
    private String fromUsername; // 发送者用户名
    private String toUsername; // 接收者用户名
    private String content; // 消息详情
    private String state; // 消息状态
}

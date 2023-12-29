package cn.edu.bupt.shopeasy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    @TableId(type= IdType.AUTO)
    private Long id; // 用户ID，自增主键
    private String username; // 用户名
    private String password; // 用户密码
    private String number; // 用户手机号
    private String email; // 用户邮箱
    private String avatar; // 用户头像，存储图片的URL
}

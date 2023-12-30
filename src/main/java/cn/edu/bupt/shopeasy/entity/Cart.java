package cn.edu.bupt.shopeasy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Cart {
    @TableId(type= IdType.AUTO)
    private Long id; // 商品ID，自增主键
    private String name; // 商品名字
    private double price; // 商品价格
    private String type; // 商品种类
    private String image; // 商品图片
    private int count; // 商品数量
    private String username; // 用户名
}

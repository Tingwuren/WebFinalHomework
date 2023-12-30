package cn.edu.bupt.shopeasy.controller;

import cn.edu.bupt.shopeasy.entity.Cart;
import cn.edu.bupt.shopeasy.entity.Product;
import cn.edu.bupt.shopeasy.entity.User;
import cn.edu.bupt.shopeasy.mapper.CartMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Resource
    CartMapper cartMapper;
    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    String add(@RequestBody Product product, HttpSession session) {
        if(session.getAttribute("now_user") == null) {
            return "请先登录！";
        }
        User user = (User)session.getAttribute("now_user");
        // 查询购物车中是否已有相同的商品和用户名
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername())
                .and(i -> i.eq("name", product.getName()));
        Cart cart = cartMapper.selectOne(queryWrapper);
        if(cart == null) {
            // 如果没有，就创建一个新的购物车项
            cart = new Cart();
            cart.setName(product.getName());
            cart.setPrice(product.getPrice());
            cart.setType(product.getType());
            cart.setImage(product.getImage());
            cart.setCount(1);
            cart.setUsername(user.getUsername());
            cartMapper.insert(cart);
        } else {
            // 如果有，就增加数量
            cart.setCount(cart.getCount() + 1);
            // 创建一个更新条件
            UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<>();
            // 设置更新条件为用户名和商品名相同的项
            updateWrapper.eq("username", user.getUsername())
                    .and(i -> i.eq("name", product.getName()));
            // 调用更新方法
            cartMapper.update(cart,updateWrapper);
        }
        return "添加成功！";
    }
    @GetMapping(path = "/getCart", produces = "application/json")
    List<Cart> load(HttpSession session) {
        if(session.getAttribute("now_user") == null) {
            return Collections.emptyList();
        }
        User user = (User)session.getAttribute("now_user");
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        System.out.println("加载"+user.getUsername()+"的购物车");
        return cartMapper.selectList(queryWrapper);
    }
    @PutMapping(path = "/plus", consumes = "application/json", produces = "application/json")
    String plus(@RequestBody Cart cart) {
        cart.setCount(cart.getCount() + 1);
        // 创建一个更新条件
        UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<>();
        // 设置更新条件为用户名和商品名相同的项
        updateWrapper.eq("username", cart.getUsername())
                .and(i -> i.eq("name", cart.getName()));
        // 调用更新方法
        cartMapper.update(cart,updateWrapper);
        return "增加成功";
    }
    @PutMapping(path = "/minus", consumes = "application/json", produces = "application/json")
    String minus(@RequestBody Cart cart) {
        int count = cart.getCount() - 1;
        if(count == 0) {
            QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", cart.getUsername())
                    .and(i -> i.eq("name", cart.getName()));
            cartMapper.delete(queryWrapper);
            return "减少成功";
        }
        cart.setCount(cart.getCount() - 1);
        // 创建一个更新条件
        UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<>();
        // 设置更新条件为用户名和商品名相同的项
        updateWrapper.eq("username", cart.getUsername())
                .and(i -> i.eq("name", cart.getName()));
        // 调用更新方法
        cartMapper.update(cart,updateWrapper);
        return "减少成功";
    }
    @DeleteMapping(path = "/clear")
    String clear(HttpSession session) {
        if(session.getAttribute("now_user") == null) {
            return "请先登录";
        }
        User user = (User)session.getAttribute("now_user");
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        String username = user.getUsername();
        queryWrapper.eq("username", username);
        cartMapper.delete(queryWrapper);
        return "购物车清空成功";
    }
}

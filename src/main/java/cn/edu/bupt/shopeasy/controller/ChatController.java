package cn.edu.bupt.shopeasy.controller;

import cn.edu.bupt.shopeasy.entity.Cart;
import cn.edu.bupt.shopeasy.entity.Message;
import cn.edu.bupt.shopeasy.entity.User;
import cn.edu.bupt.shopeasy.mapper.MessageMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Resource
    MessageMapper messageMapper;
    @GetMapping(path = "/getReceiveMessage", produces = "application/json")
    List<Message> loadReceiveMessage(HttpSession session) {
        if(session.getAttribute("now_user") == null) {
            return Collections.emptyList();
        }
        User user = (User)session.getAttribute("now_user");
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("to_username", user.getUsername());
        System.out.println("加载"+user.getUsername()+"接收的消息");
        System.out.println("加载消息:"+messageMapper.selectList(queryWrapper));
        return messageMapper.selectList(queryWrapper);
    }
    @GetMapping(path = "/getSendMessage", produces = "application/json")
    List<Message> loadSendMessage(HttpSession session) {
        if(session.getAttribute("now_user") == null) {
            return Collections.emptyList();
        }
        User user = (User)session.getAttribute("now_user");
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("from_username", user.getUsername());
        System.out.println("加载"+user.getUsername()+"发送的消息");
        System.out.println("加载消息:"+messageMapper.selectList(queryWrapper));
        return messageMapper.selectList(queryWrapper);
    }
    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    String send(@RequestBody Message message, HttpSession session) {
        if(session.getAttribute("now_user") == null) {
            return "请先登录！";
        }
        User user = (User)session.getAttribute("now_user");
        Message send = new Message();
        send.setFromUsername(user.getUsername());
        send.setToUsername(message.getToUsername());
        send.setContent(message.getContent());
        send.setState("未读");
        messageMapper.insert(send);
        return "发送成功";
    }
    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    void read(@PathVariable Long id, @RequestBody Message message) {
        System.out.println("阅读消息：" + message);
        message.setId(id);
        messageMapper.updateById(message);
    }
    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable Long id) {
        System.out.println("清除消息：" + id);
        messageMapper.deleteById(id);
    }
}

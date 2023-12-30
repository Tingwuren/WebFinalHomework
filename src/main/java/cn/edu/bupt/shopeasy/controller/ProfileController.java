package cn.edu.bupt.shopeasy.controller;

import cn.edu.bupt.shopeasy.entity.User;
import cn.edu.bupt.shopeasy.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private List<String> sessionIds = new ArrayList<>();

    @Resource
    UserMapper userMapper;
    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    String register(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        //打印用户信息
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        //查询用户名是否存在
        User isUser = userMapper.selectOne(queryWrapper);
        System.out.println("User: " + isUser);
        if (isUser != null) {
            //用户名已存在，返回提示
            return "用户已存在";
        } else {
            //用户名不存在，执行插入操作
            userMapper.insert(user);
            return "注册成功";
        }
    }

    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    String login(@RequestBody User user, Model model,HttpSession session) {
        if(session.getAttribute("now_user") == null) {
            String username = user.getUsername();
            String password = user.getPassword();
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", username)
                    .and(i -> i.eq("password", password));
            User loginUser = userMapper.selectOne(queryWrapper);
            if (loginUser != null) {
                session.setAttribute("now_user", loginUser);
                return "登录成功";
            } else {
                return "用户名或密码错误";
            }
        } else {
            return "已登录";
        }
    }
    @GetMapping(path = "/getProfile", produces = "application/json")
    User load(HttpSession session) {
        System.out.println("加载用户信息");
        User user = (User)session.getAttribute("now_user");


        if(user != null) {
            System.out.println("用户id："+ user.getId());
            User loadUser = userMapper.selectById(user.getId());
            System.out.println("当前登录用户："+ loadUser);
            return loadUser;
        }
        return null;
    }
    @GetMapping("/logout")
    public void deleteSession(HttpSession session) {
        session.invalidate();
    }
    @PutMapping(path="/update/{id}",consumes = "application/json", produces = "application/json")
    public void update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        System.out.println("更新用户，userid:"+user.getId());
        userMapper.updateById(user);
    }
}

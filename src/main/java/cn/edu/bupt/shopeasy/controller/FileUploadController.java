package cn.edu.bupt.shopeasy.controller;

import cn.edu.bupt.shopeasy.entity.User;
import cn.edu.bupt.shopeasy.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/profile/upload")
public class FileUploadController {
    @Resource
    UserMapper userMapper;
    @PostMapping
    public String handleFormUpload(@RequestParam("file") MultipartFile file,
                                   Model model, HttpSession session) throws IOException {
        if (!file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            String targetFilename = UUID.randomUUID() +
                    originalFilename.substring(originalFilename.lastIndexOf("."));
            if(session.getAttribute("now_user") == null) {
                return "profile";
            }
            User user = (User)session.getAttribute("now_user");
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", user.getUsername());
            User loginUser = userMapper.selectOne(queryWrapper);
            String avatar = "/"+user.getUsername()+".jpg";
            loginUser.setAvatar(avatar);
            userMapper.update(loginUser,queryWrapper);
            file.transferTo(Paths.get("./upload/"+avatar));
            String message = String.format("上传成功：%s；文件大小：%d字节",
                    file.getOriginalFilename(),
                    file.getSize());
            model.addAttribute("message",message);
            model.addAttribute("avatarFilename", avatar);
        }
        return "profile";
    }

    @GetMapping
    public String setUpUploadForm() {
        return "profile";
    }
}

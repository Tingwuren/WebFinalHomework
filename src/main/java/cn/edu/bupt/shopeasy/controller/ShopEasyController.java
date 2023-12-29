package cn.edu.bupt.shopeasy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("")
public class ShopEasyController {
    @GetMapping("")
    ModelAndView getIndexView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index"); // 返回首页的视图名称，需要在视图解析器中配置
        // 可以在 mv 中添加一些数据，用于在视图中展示，例如：
        // mv.addObject("username", "张三");
        return mv;
    }
    @GetMapping("/category")
    ModelAndView getCategoryView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("category"); // 返回分类页的视图名称，需要在视图解析器中配置
        // 可以在 mv 中添加一些数据，用于在视图中展示，例如：
        // mv.addObject("categories", categoryService.listAllCategories());
        return mv;
    }
    @GetMapping("/cart")
    ModelAndView getCartView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cart"); // 返回购物车页的视图名称，需要在视图解析器中配置
        // 可以在 mv 中添加一些数据，用于在视图中展示，例如：
        // mv.addObject("items", cartService.listCartItemsByUserId(userId));
        return mv;
    }
    @GetMapping("/profile")
    ModelAndView getProfileView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("profile"); // 返回个人中心页的视图名称，需要在视图解析器中配置
        // 可以在 mv 中添加一些数据，用于在视图中展示，例如：
        // mv.addObject("user", userService.getUserById(userId));
        return mv;
    }
    @GetMapping("/chat")
    ModelAndView getChatView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("chat"); // 返回聊天页的视图名称，需要在视图解析器中配置
        // 可以在 mv 中添加一些数据，用于在视图中展示，例如：
        // mv.addObject("messages", messageService.listMessagesByUserId(userId));
        return mv;
    }
}

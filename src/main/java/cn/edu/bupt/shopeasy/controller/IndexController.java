package cn.edu.bupt.shopeasy.controller;

import cn.edu.bupt.shopeasy.entity.Product;
import cn.edu.bupt.shopeasy.mapper.ProductMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/index")
public class IndexController {
    @Resource
    ProductMapper productMapper;
    @GetMapping(path = "/getIndex", produces = "application/json")
    List<Product> load() {
        return productMapper.selectList(null);
    }
    @GetMapping(path = "/{query}", produces = "application/json")
    List<Product> search(@PathVariable String query) {
        // 如果请求为空，直接返回未筛选的列表
        System.out.println("搜索："+query);
        if(query == "") {
            return productMapper.selectList(null);
        }
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", query)
                .or()
                .eq("type", query);
        List<Product> results = productMapper.selectList(queryWrapper);
        return results;
    }
}

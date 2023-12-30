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
@RequestMapping("/category")
public class CategoryController {
    @Resource
    ProductMapper productMapper;
    @GetMapping(path = "/getAll", produces = "application/json")
    List<Product> load() {
        return productMapper.selectList(null);
    }
    @GetMapping(path = "/getDigital", produces = "application/json")
    List<Product> getDigital() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", "数码");
        List<Product> results = productMapper.selectList(queryWrapper);
        return results;
    }
    @GetMapping(path = "/getComputer", produces = "application/json")
    List<Product> getComputer() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", "电脑");
        List<Product> results = productMapper.selectList(queryWrapper);
        return results;
    }
    @GetMapping(path = "/getHome", produces = "application/json")
    List<Product> getHome() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", "家电");
        List<Product> results = productMapper.selectList(queryWrapper);
        return results;
    }
    @GetMapping(path = "/getClothing", produces = "application/json")
    List<Product> getClothing() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", "服饰");
        List<Product> results = productMapper.selectList(queryWrapper);
        return results;
    }
    @GetMapping(path = "/getBeauty", produces = "application/json")
    List<Product> getBeauty() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", "美妆");
        List<Product> results = productMapper.selectList(queryWrapper);
        return results;
    }
}

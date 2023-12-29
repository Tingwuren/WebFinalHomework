package cn.edu.bupt.shopeasy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("cn.edu.bupt.shopeasy.mapper")
@SpringBootApplication
public class ShopEasyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopEasyApplication.class, args);
    }

}

package cn.edu.bupt.shopeasy.mapper;

import cn.edu.bupt.shopeasy.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

public interface UserMapper extends BaseMapper<User> {
}

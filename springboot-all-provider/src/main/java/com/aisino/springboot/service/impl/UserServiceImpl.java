package com.aisino.springboot.service.impl;

import com.aisino.springboot.mapper.UserMapper;
import com.aisino.springboot.model.User;
import com.aisino.springboot.service.UserService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component//spring的注解
@Service//dubbo注解
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 分页查询
     *
     * @param paramMap 参数map
     * @return
     */
    @Override
    public List<User> getUserByPage(Map<String, Object> paramMap) {
        return userMapper.selectUserByPage(paramMap);
    }

    /**
     * 查询总数
     *
     * @return
     */
    @Override
    public int getUserByTotal() {
        return userMapper.selectUserByTotal();
    }
}

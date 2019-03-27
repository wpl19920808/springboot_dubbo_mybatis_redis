package com.aisino.springboot.service;

import com.aisino.springboot.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 分页查询
     * @param paramMap 参数map
     * @return
     */
    List<User> getUserByPage(Map<String, Object> paramMap);


    /**
     * 查询总数
     * @return
     */
    int getUserByTotal();


}

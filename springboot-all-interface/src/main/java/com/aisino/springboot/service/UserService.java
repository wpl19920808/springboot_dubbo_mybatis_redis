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


    /**添加用户
     * @param user
     */
    int addUser(User user);


    /**删除用户
     * @param id
     * @return
     */
    int deleteUser(int id);


    /**修改用户
     * @param user
     * @return
     */
    int updateUser(User user);


    /**查询用户
     * @param id
     */
     User getUserByID(Integer id);
}

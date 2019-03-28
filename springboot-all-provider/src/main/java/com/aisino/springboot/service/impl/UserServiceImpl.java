package com.aisino.springboot.service.impl;

import com.aisino.springboot.mapper.UserMapper;
import com.aisino.springboot.model.User;
import com.aisino.springboot.service.UserService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component//spring的注解
@Service//dubbo注解
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
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
        // 设置key的序列化方式，采用字符串方式，有较好的可读性
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        // 先从redis取总行数
        Integer totalRows = (Integer) redisTemplate.opsForValue().get("totalRows");
        if(null == totalRows)
        {
            synchronized (this){
                totalRows = (Integer) redisTemplate.opsForValue().get("totalRows");
                if(null == totalRows){
                    totalRows = userMapper.selectUserByTotal();
                    redisTemplate.opsForValue().set("totalRows", totalRows);
                }
            }
        }
        return totalRows;
    }

    /**
     * 添加用户
     *
     * @param user
     */
    @Override
    public int addUser(User user) {
        int result = userMapper.insert(user);
        if(result > 0){
            //添加成功，需要更新redis缓存总数
            int totalRows = userMapper.selectUserByTotal();

            // 设置key的序列化方式，采用字符串方式，有较好的可读性
            redisTemplate.setKeySerializer(new StringRedisSerializer());

            redisTemplate.opsForValue().set("totalRows", totalRows);
        }
        return result;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @Override
    public int deleteUser(int id) {
        int result = userMapper.deleteByPrimaryKey(id);
        if(result > 0){
            // 删除成功，需要更新redis缓存
            int totalRows = userMapper.selectUserByTotal();

            // 设置key的序列化方式，采用字符串方式，有较好的可读性
            redisTemplate.setKeySerializer(new StringRedisSerializer());

            redisTemplate.opsForValue().set("totalRows", totalRows);
        }
        return result;
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 查询用户
     *
     * @param id
     */
    @Override
    public User getUserByID(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}

package com.aisino.springboot.controller;

import com.aisino.springboot.model.User;
import com.aisino.springboot.service.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Controller
public class UserController {

    @Reference//要引用alibaba的包
    private UserService userService;

    @RequestMapping("/index")
    public String index(Model model,
            @RequestParam(value = "curPage", required = false) Integer curPage){
        int pageSize = 5;
        int totalRows = userService.getUserByTotal();

        if(null == curPage){
            curPage = 1;//默认第一页
        }

        //计算分页
        int totalPages = totalRows / pageSize;
        //有可能又余数
        int left = totalRows % pageSize;
        if(left > 0){
            ++totalPages;
        }

        // 当前页不能大于总页数
        curPage = curPage > totalPages ? totalPages : curPage;
        // 当前页码不能小于1
        curPage = curPage < 1 ? 1 : curPage;

        //计算查询开始行
        int startRow = (curPage - 1) * pageSize;
        Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
        paramMap.put("startRow", startRow);
        paramMap.put("pageSize", pageSize);
        List<User> userList = userService.getUserByPage(paramMap);

        model.addAttribute("userlist", userList);
        model.addAttribute("curPage", curPage);
        model.addAttribute("totalPages", totalPages);
        //thymeleaf，跳到模板页面
        return "index";
    }


    /**
     * 添加用户页面
     * @return
     */
    @RequestMapping("/user/toAddUser")
    public String AddToUser(){
        return "addUser";
    }


    /**
     * 添加/修改用户
     * spring MVC就提供了，表单提交，可以直接映射成一个类的对象来提交
     * 前提是这个表单各个属性的name必须和类的属性名字是一样的
     * @param user
     * @return
     */
    @RequestMapping("/user/addUser")
    public String AddUser(User user){
        Integer id = user.getId();
        if(null == id){
            // 添加用户
            userService.addUser(user);
        }else {
            // 修改
            userService.updateUser(user);
        }

        return "redirect:/index";// 重定向回首页
    }


    /**去修改
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/user/toUpdate")
    public String ToUpdate(Model model, @RequestParam("id") Integer id){
        User user = userService.getUserByID(id);
        model.addAttribute("user", user);

        return "addUser";
    }

    /**删除用户
     * @param id
     * @return
     */
    @RequestMapping("/user/delete")
    public String delete(int id){
        userService.deleteUser(id);
        return "redirect:/index";// 重定向回首页
    }
}

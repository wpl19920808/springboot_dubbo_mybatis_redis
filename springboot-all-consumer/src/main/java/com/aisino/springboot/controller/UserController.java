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
}

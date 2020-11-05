package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.User;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Configuration
    public class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/avatorImages/**").addResourceLocations("file:D:\\IdeaProjects\\otters-server\\avatorImages\\");
        }
    }

//    添加用户
    @ResponseBody
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public Object addUser(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String user_id = req.getParameter("userId").trim();
        String user_name = req.getParameter("userName").trim();
        String user_password = req.getParameter("userPassword").trim();
        String user_gender = req.getParameter("userGender").trim();
        String user_phone = req.getParameter("userPhone").trim();
        String user_email = req.getParameter("userEmail").trim();
        String user_status = req.getParameter("userStatus").trim();
        String user_location = req.getParameter("userLocation").trim();
        String user_idcard = req.getParameter("userIdcard").trim();
        String user_introduction = req.getParameter("userIntroduction").trim();
        String user_avatorPic = req.getParameter("userAvatorPic").trim();

        if (user_id.equals("") || user_id == null){
            jsonObject.put("code", 0);
            jsonObject.put("msg", "账号错误");
            return jsonObject;
        }
        if (user_name.equals("") || user_name == null){
            jsonObject.put("code", 0);
            jsonObject.put("msg", "昵称错误");
            return jsonObject;
        }
        if (user_password.equals("") || user_password == null){
            jsonObject.put("code", 0);
            jsonObject.put("msg", "密码错误");
            return jsonObject;
        }
        User user = new User();
        user.setUserId(user_id);
        user.setUserName(user_name);
        user.setUserPassword(user_password);
        user.setUserGender(new Byte(user_gender));
        if (user_phone == ""){
            user.setUserPhone(null);
        } else{
            user.setUserPhone(user_phone);
        }
        if (user_email == ""){
            user.setUserEmail(null);
        } else{
            user.setUserEmail(user_email);
        }
        user.setUserStatus(new Byte(user_status));
        user.setUserLocation(user_location);
        user.setUserIdcard(user_idcard);
        user.setUserIntroduction(user_introduction);
        user.setUserAvatorPic(user_avatorPic);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        boolean res = userService.addUser(user);
        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "注册成功");
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "注册失败");
            return jsonObject;
        }
    }

//    判断是否登录成功
    @ResponseBody
    @RequestMapping(value = "/user/login/status", method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest req, HttpSession session){

        JSONObject jsonObject = new JSONObject();
        String user_id = req.getParameter("userId");
//        String user_name = req.getParameter("username");
        String user_password = req.getParameter("userPassword");
        System.out.println(user_id+"  "+user_password);
        boolean res = userService.veritypasswd(user_id, user_password);

        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "登录成功");
            jsonObject.put("userMsg", userService.loginStatus(user_id));
            session.setAttribute("userId", user_id);
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "账号或密码错误");
            return jsonObject;
        }

    }

//    返回所有用户
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Object allUser(){
        return userService.allUser();
    }

//    返回指定ID的用户
    @RequestMapping(value = "/user/detail", method = RequestMethod.GET)
    public Object userOfId(HttpServletRequest req){
        String user_id = req.getParameter("userId");
        return userService.userOfId(user_id);
    }

//    删除用户
    @RequestMapping(value = "/user/delete", method = RequestMethod.GET)
    public Object deleteUser(HttpServletRequest req){
        String user_id = req.getParameter("userId");
        return userService.deleteUser(user_id);
    }

//    更新用户信息
    @ResponseBody
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public Object updateUserMsg(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String user_id = req.getParameter("userId").trim();
        String user_name = req.getParameter("userName").trim();
        String user_password = req.getParameter("userPassword").trim();
        String user_gender = req.getParameter("userGender").trim();
        String user_phone = req.getParameter("userPhone").trim();
        String user_email = req.getParameter("userEmail").trim();
        String user_status = req.getParameter("userStatus").trim();
        String user_location = req.getParameter("userLocation").trim();
        String user_idcard = req.getParameter("userIdcard").trim();
        String user_introduction = req.getParameter("userIntroduction").trim();
//        String user_avatorPic = req.getParameter("userAvatorPic").trim();
//        String avator = req.getParameter("avator").trim();
//        System.out.println(username+"  "+password+"  "+sex+"   "+phone_num+"     "+email+"      "+birth+"       "+introduction+"      "+location);

        if (user_id.equals("") || user_id == null){
            jsonObject.put("code", 0);
            jsonObject.put("msg", "账号错误");
            return jsonObject;
        }
        if (user_name.equals("") || user_name == null){
            jsonObject.put("code", 0);
            jsonObject.put("msg", "昵称错误");
            return jsonObject;
        }
        if (user_password.equals("") || user_password == null){
            jsonObject.put("code", 0);
            jsonObject.put("msg", "密码错误");
            return jsonObject;
        }
        User user = new User();
        user.setUserId(user_id);
        user.setUserName(user_name);
        user.setUserPassword(user_password);
        user.setUserGender(new Byte(user_gender));
        user.setUserPhone(user_phone);
        user.setUserEmail(user_email);
        user.setUserStatus(new Byte(user_status));
        user.setUserLocation(user_location);
        user.setUserIdcard(user_idcard);
        user.setUserIntroduction(user_introduction);
//        user.setUserAvatorPic(user_avatorPic);
//        user.setAvator(avator);
        user.setUpdateTime(new Date());

        boolean res = userService.updateUserMsg(user);
        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "修改成功");
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "修改失败");
            return jsonObject;
        }
    }

//    更新用户头像
    @ResponseBody
    @RequestMapping(value = "/user/avatar/update", method = RequestMethod.POST)
    public Object updateUserPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("userId")String user_id){
        JSONObject jsonObject = new JSONObject();

        if (avatorFile.isEmpty()) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "文件上传失败！");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "avatorImages" ;
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeAvatorPath = "/avatorImages/"+fileName;
        try {
            avatorFile.transferTo(dest);
            User user = new User();
            user.setUserId(user_id);
            user.setUserAvatorPic(storeAvatorPath);
            boolean res = userService.updateUserAvator(user);
            if (res){
                jsonObject.put("code", 1);
                jsonObject.put("avator", storeAvatorPath);
                jsonObject.put("msg", "上传成功");
                return jsonObject;
            }else {
                jsonObject.put("code", 0);
                jsonObject.put("msg", "上传失败");
                return jsonObject;
            }
        }catch (IOException e){
            jsonObject.put("code", 0);
            jsonObject.put("msg", "上传失败"+e.getMessage());
            return jsonObject;
        }finally {
            return jsonObject;
        }
    }
}

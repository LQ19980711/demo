package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Index {

    @Resource
    private UserService userService;

    @RequestMapping(value="/list")
    public String getListUser(HttpServletRequest request, HttpServletResponse response){
        String userName = request.getParameter("userName");
        System.out.println(userName);
        Map params=new HashMap();
        List<User> userList=userService.findListUser(params);
        String json=null;
        if (userList.size()>0){
            json=JSON.toJSONString(userList);
        }else {
            json="{\"ers\":\"no\",\"mesage\":\"加载失败\"}";
        }
        return json;
    }

    @RequestMapping(value="/insertUser")
    public String insertUser(HttpSession session, User user){
        int size=userService.insertUser(user);
        String json=null;
        if (size>0){
            json="{\"ers\":\"yes\",\"message\":\"新增成功\"}";
        }else {
            json="{\"ers\":\"no\",\"message\":\"新增失败\"}";
        }
        return json;
    }

    @RequestMapping(value="/getIp")
    public String getIp(HttpServletRequest request){
        String ip=getIpAddress(request);
        return ip;
    }


    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     *
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     *
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     *
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}

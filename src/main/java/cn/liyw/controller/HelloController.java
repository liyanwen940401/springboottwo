package cn.liyw.controller;

import cn.liyw.domin.User;
import cn.liyw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class HelloController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/add")
    public int addUser(User user) {
        return userService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/selectAll")
    public List<User> selectAll(){
        return userService.queryUserList();
    }

    @RequestMapping(method = RequestMethod.GET,value = "/selectUserById")
    public String selectUserById(ModelMap model){
        System.out.println("ok");
//        User user = userService.queryUserById(1L);
        User user = new User();
        user.setAge(12);
        user.setName("zhangsan");
        user.setId(1L);
        model.put("user",user);
        return "demo";
    }
}

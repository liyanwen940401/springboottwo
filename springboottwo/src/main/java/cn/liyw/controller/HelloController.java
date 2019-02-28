package cn.liyw.controller;

import cn.liyw.domin.User;
import cn.liyw.service.UserService;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        System.out.println("23415432");
        System.out.println("23415432");
        System.out.println("23415432");
        User user = userService.queryUserById(1L);
        model.put("user",user);
        return "demo";
    }
}

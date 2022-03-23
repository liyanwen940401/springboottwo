package cn.liyw.dao;

import cn.liyw.Application;
import cn.liyw.domin.User;
import cn.liyw.service.DuplicateOrderException;
import cn.liyw.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserDaoTest {
    @Autowired
    private UserService userService;

    public int add(){
        User user = new User();
        user.setAge(12);
        user.setName("zhangsan1231211111111111111111zhangsan1231211111111111111111zhangsan1231211111111111111111zhangsan1231211111111111111111zhangsan1231211111111111111111");
        user.setId(1L);

        Integer result =0;
        try {
            result = userService.addUser(user);
        } catch (DuplicateKeyException e) {
            throw new DuplicateOrderException("duplicate recharge fast pass exception");
        }
        return result;
    }
    @Test
    public void T_add(){
        Map<String, String> extparam = new HashMap<String, String>();

            extparam.put("uuid", "1");



    }


}

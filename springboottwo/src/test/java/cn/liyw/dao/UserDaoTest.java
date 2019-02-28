package cn.liyw.dao;

import cn.liyw.Application;
import cn.liyw.domin.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    @Test
    public void T_add(){
        User user = new User();
        user.setAge(12);
        user.setName("zhangsan");
        user.setId(1L);
        userDao.addUser(user);
    }

}
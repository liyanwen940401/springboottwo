package cn.liyw.page;

import cn.liyw.Application;
import cn.liyw.domin.Read;
import cn.liyw.domin.Read2;
import cn.liyw.domin.RiskRule;
import cn.liyw.domin.User;
import cn.liyw.service.GsonUtil;
import cn.liyw.service.UserService;
import com.xxl.job.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class test3 {
    @Autowired
    private UserService userService;

    @Test
    public void T_add() throws Exception {

        Integer count = 1000;
        for (int i = 0; i < count; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
        Thread.sleep(100000);

    }

    public class MyRunnable implements Runnable {
        @Override
        public void run() {
            update(12l, "aaaa", 11);
            System.out.println(Thread.currentThread().getName() + "---" + System.currentTimeMillis());
        }

        private void update(Long id, String name, int gearId) {
            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setGearId(gearId);
            try {
                User user1 = userService.queryUserById(user.getId());
                if (Objects.isNull(user1)) {
                    user.setAge(1);
                    userService.addUser(user);
                } else {
                    userService.updateCount(user1.getId());
                }
            } catch (DuplicateKeyException e) {
                update(id, name, gearId);
                System.out.println("DuplicateKeyException  "+System.currentTimeMillis());
            } catch (Exception e) {
                System.out.println("Exception  "+System.currentTimeMillis());
            }
        }


    }
}
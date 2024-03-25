package cn.liyw.page;

import cn.liyw.Application;
import cn.liyw.domin.CallbackActivityConf;
import cn.liyw.domin.RNP2;
import cn.liyw.domin.User;
import cn.liyw.risk.RiskModule;
import cn.liyw.risk.RiskModuleFactory;
import cn.liyw.service.GsonUtil;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class test1 {

    @Autowired
    private RiskModuleFactory riskModuleFactory;

    static Set<String> operator = new HashSet<String>() {{
        add("&");
        add("!");
        add("|");
    }};

    public Boolean compute(LinkedList<String> s1, User user) {
        LinkedList<String> stack = new LinkedList<String>();
        while (s1.size() != 0) {
            boolean ret = false;
            String tmp = s1.pop();
            if (operator.contains(tmp)) {
                String d2 = stack.pop();
                if (tmp.equals("&")) {
                    String d1 = stack.pop();
                    ret = process1(d1, d2, user);
                } else if (tmp.equals("|")) {
                    String d1 = stack.pop();
                    ret = process2(d1, d2, user);
                } else if (tmp.equals("!")) {
                    ret = process3(d2, user);
                } else {

                }
                if (ret) {
                    stack.push("Y");
                } else {
                    stack.push("N");
                }
            } else {
                stack.push(tmp);
            }
        }
        String ret = stack.pop();
        if (ret.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean createHandler(String opType, User user) {
        if (opType.equals("Y")) {
            return true;
        } else if (opType.equals("N")) {
            return false;
        }
        // 根据配置创建风控模块对象
        RiskModule riskModule = null;
        try {
            riskModule = riskModuleFactory.create(opType);
        } catch (Exception ex) {
            System.out.println("error in riskModuleFactory.create");
        }
        return riskModule.invokeAsync(user);
    }

    public boolean process1(String d1, String d2, User user) {
        boolean ret = createHandler(d1, user);
        if (!ret) {
            return false;
        }
        return createHandler(d2, user);
    }

    public boolean process2(String d1, String d2, User user) {
        boolean ret = createHandler(d1, user);
        if (!ret) {
            return createHandler(d2, user);
        } else {
            return true;
        }

    }

    public boolean process3(String d1, User user) {
        boolean ret = createHandler(d1, user);
        return !ret;

    }

    @Test
    public void T_add() throws Exception {
        User user = new User();
        user.setName("zhang");
        user.setGearId(1);
        user.setAge(2);
        user.setId(3l);
        RNP2 rnp2 = new RNP2();
        LinkedList<String> list = rnp2.RPN("(a&(e|f))|d|c|(g&h)");
        System.out.println(GsonUtil.toJson(user));
        Boolean ret = compute(list, user);
        System.out.println(ret);
    }

}
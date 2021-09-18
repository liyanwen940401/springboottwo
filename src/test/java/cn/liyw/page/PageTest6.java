package cn.liyw.page;

import cn.liyw.Application;
import cn.liyw.domin.User;
import cn.liyw.service.RedisUtils;
import cn.liyw.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PageTest6 {

    @Autowired
    private UserService userService;

    public int myAtoi(String s) {
        s = s.trim();
        if(s.equals("")){
            return 0;
        }
        char[] res = s.toCharArray();
        List<Character> list = Arrays.asList('1','2','0','3','4','5','6','7','8','9');
        char[] result = new char[res.length];
        int  b = 0;
        int i=0;
        if(res[0]=='-'){
            i++;
            b=-1;
        }else if(res[0]=='+'){
            i++;
            b=1;
        }
        int temp = 0;
        for(;i<res.length;i++){
            if(b != 0 && temp==0 && res[i]=='0'){
                return 0;
            }
            if(list.contains(res[i])){
                result[temp]=res[i];
                temp++;
            }
        }
        if(temp==0){
            return 0;
        }
        String s1 = String.copyValueOf(result,0,temp);
        try {
            int number = Integer.valueOf(s1);
            return b*number;
        }catch (Exception e){
            if(b<0){
                return -2147483648;
            }else{
                return 2147483647;
            }
        }
    }

    @Test
    public void T_add(){
        String str = RedisUtils.get("mars:ums:user:admin",0);
        System.out.println(str+"**************");

    }


}
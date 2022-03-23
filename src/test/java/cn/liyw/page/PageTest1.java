package cn.liyw.page;

import cn.liyw.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PageTest1 {
    public int lengthOfLongestSubstring(String s) {
        if(s==null ||s.equals("")){
            return 0;
        }
        if(s.length() == 1){
            return 1;
        }
        char[] cs = s.toCharArray();
        int length = 0;
        Set<Character> set = new HashSet();
        for(int i = 0;i<cs.length-1;i++){
            set.clear();
            set.add(cs[i]);
            for(int j = i+1;j<cs.length;j++){
                if(set.contains(cs[j]) ){
                    break;
                }else{
                    set.add(cs[j]);
                }
            }
            if(set.size()>length) {
                length = set.size();
            }
        }
        return length;
    }

    @Test
    public void T_add(){


    }

}
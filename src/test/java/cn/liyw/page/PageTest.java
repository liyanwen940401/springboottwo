package cn.liyw.page;

import cn.liyw.Application;
import cn.liyw.dao.UserDao;
import cn.liyw.domin.ActivityItemType;
import cn.liyw.domin.User;
import cn.liyw.domin.k_v;
import net.minidev.json.JSONArray;
import netscape.javascript.JSObject;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PageTest {

     public class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode result =new ListNode();
        ListNode temp =result;
        int t = 0;
        while(l1 !=null || l2!=null || t!=0){
            int res = (l1==null?0:l1.val)+(l2==null?0:l2.val)+t;
            if(res>=10){
                t=1;
                res=res-10;
            }else{
                t=0;
            }
            result.next =new ListNode(res);
            result = result.next;
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
        }
        return temp.next;
    }


    class Person{
         private int id;
         private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    @Test
    public void T_add() throws ParseException, JSONException {
        Calendar calendar = Calendar.getInstance();
        Integer w = calendar.get(Calendar.DAY_OF_WEEK);

        System.out.println(w);
    }

}
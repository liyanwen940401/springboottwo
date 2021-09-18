package cn.liyw.page;

import cn.liyw.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PageTest5 {

    public int reverse(int x) {
        if(x>-10&&x<10){
            return x;
        }
        boolean b = x>0?true:false;
        x=b?x:-x;
        char[] str = (x+"").toCharArray();
        char[] result = new char[str.length];
        int j=0;
        for(int i=str.length-1;i>=0;i--){
            result[j]=str[i];
            j++;
        }
        String s = String.copyValueOf(result);
        int r=0;
        try{
            r = Integer.parseInt(s);
        }catch (Exception e){
            return 0;
        }
        return b?r:-r;
    }

    @Test
    public void T_add(){
        int r = this.reverse(1534236469);
        System.out.println(r);
    }

}
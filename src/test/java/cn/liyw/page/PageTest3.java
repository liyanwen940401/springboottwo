package cn.liyw.page;

import cn.liyw.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PageTest3 {

    public String longestPalindrome(String s) {
        if(s.length()<=1){
            return s;
        }
        char[] res = s.toCharArray();
        String result =String.valueOf(res[0]);
        //奇数回文
        for(int i=1;i<res.length-1;i++){
            for(int j=i-1;j>=0&&2*i-j<res.length;j--){
                if(!Objects.equals(res[j],res[2*i-j])){
                    if(result.length()<2*i-2*j-1) {
                        result = s.substring(j + 1, 2 * i - j);
                    }
                    break;
                }
                if((j==0 || 2*i-j==res.length-1)&& result.length()<2*i-j-j){
                    result = s.substring(j,2*i-j+1);
                }
            }
        }
        for(int i=0;i<res.length-1;i++){
            if(!Objects.equals(res[i],res[i+1])){
                continue;
            }
            if(result.length()<2){
                result = s.substring(i, i+2);
            }
            for(int j=i-1;j>=0&&i+1+i-j<res.length;j--){
                if(!Objects.equals(res[j],res[i+1+i-j])){
                    if(result.length()<(i-j)*2) {
                        result = s.substring(j + 1, 2*i-j+1);
                    }
                    break;
                }
                if((j==0|| i+1+i-j==res.length-1)&& result.length()<2*(i-j+1)){
                    result = s.substring(j,i+1+i-j+1);
                }
            }
        }
        return result;
    }

    @Test
    public void T_add(){
        this.longestPalindrome("babaddtattarrattatddetartrateedredividerb");
    }

}
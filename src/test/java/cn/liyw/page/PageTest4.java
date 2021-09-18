package cn.liyw.page;

import cn.liyw.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Objects;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PageTest4 {

    public String convert(String s, int numRows) {
        if(s.length()<=numRows || numRows==1){
            return s;
        }
        int num = s.length()%(2*numRows-2)==0?s.length()/(2*numRows-2):s.length()/(2*numRows-2)+1;
        char[] chars = s.toCharArray();
        char[] newChars = new char[chars.length];
        int temp = 0;
        for(int i=0;i<num;i++){
            newChars[temp] = chars[i*(2*numRows-2)];
            temp++;
        }
        for(int j=1;j<=numRows-2;j++) {
            for (int i = 0; i < num && i * (2 * numRows - 2) + j<s.length(); i++) {
                newChars[temp] = chars[i * (2 * numRows - 2) + j];
                temp++;
                if((i + 1) * (2 * numRows - 2) - j<s.length()) {
                    newChars[temp] = chars[(i + 1) * (2 * numRows - 2) - j];
                    temp++;
                }
            }
        }
        for(int i=temp;i<s.length();i++){
            newChars[i] = chars[(i-temp)*(2*numRows-2)+numRows-1];
        }
        return String.valueOf(newChars);
    }

    @Test
    public void T_add(){
        this.convert("ABCDE",4);
    }

}
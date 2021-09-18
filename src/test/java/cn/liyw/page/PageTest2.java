package cn.liyw.page;

import cn.liyw.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PageTest2 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        boolean b = (nums1.length+nums2.length)%2==0;
        int num = b? (nums1.length+nums2.length)/2:(nums1.length+nums2.length)/2+1;
        int [] result = new int[num+1];
        int num1=0;
        int num2=0;
        for(int i=0;i<=num;i++){
            if(num1== nums1.length && num2== nums2.length){
                break;
            }
            if(num1== nums1.length){
                result[i]=nums2[num2];
                num2++;
            }else if(num2== nums2.length){
                result[i]=nums1[num1];
                num1++;
            }else {
                if (nums1[num1] >= nums2[num2]) {
                    result[i] = nums2[num2];
                    num2++;
                } else {
                    result[i] = nums1[num1];
                    num1++;
                }
            }
        }
        if(b){
            return (result[num]+result[num-1])/2.0;
        }else{
            return result[num-1]/1.0;
        }
    }

    @Test
    public void T_add(){
        this.findMedianSortedArrays(new int[]{},new int[]{2});
    }

}
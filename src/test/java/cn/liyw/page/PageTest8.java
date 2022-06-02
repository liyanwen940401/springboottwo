package cn.liyw.page;


import cn.liyw.domin.BillingInfo;
import cn.liyw.domin.CMQMessage;
import cn.liyw.domin.CMQRetryMessage;
import cn.liyw.domin.MessageItem;
import cn.liyw.service.GsonUtil;
import org.junit.Test;

import java.io.*;
import java.util.HashSet;

public class PageTest8 {
    @Test
    public void T_add() {
        try {

            String encoding = "GBK";
            File file = new File("/Users/liyanwen/Desktop/fail.log");
            HashSet<Integer> apptype = new HashSet<Integer>();
            String dstPath = "/Users/liyanwen/Desktop/lyw.log";
            int i=0;
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                BufferedWriter bw = new BufferedWriter(new FileWriter(dstPath));

                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {

                    String queueName = lineTxt.substring(lineTxt.indexOf("failed|")+7 , lineTxt.indexOf(
                            "|5|") );

                    String gift = lineTxt.substring(lineTxt.indexOf("|5|") + 3, lineTxt.lastIndexOf(
                            "}") + 1);
                    CMQMessage cmqMsg = GsonUtil.fromJson(gift, CMQMessage.class);
                    CMQRetryMessage message = new CMQRetryMessage(queueName,GsonUtil.toJson(cmqMsg));
                    System.out.println(GsonUtil.toJson(message));
                    i++;
                    bw.write(GsonUtil.toJson(message));
                    bw.newLine();//换行
                    bw.flush();//强制输出缓冲区的内容，避免数据缓存，造成文件写入不完整的情况。
                }
                bufferedReader.close();
                read.close();
                System.out.println(GsonUtil.toJson(i));
            } else {
                System.out.println("CheckMonthInfoService|check|error file not");
            }
        } catch (
                Exception e) {
            System.out.println("CheckMonthInfoService|check|error file error:" + e.getMessage());
        }
    }
}

package cn.liyw.page;


import cn.liyw.domin.BillingInfo;
import cn.liyw.domin.MessageItem;
import cn.liyw.service.GsonUtil;
import org.apache.poi.hssf.usermodel.*;
import org.junit.Test;

import java.io.*;
import java.util.HashSet;

public class ExportLogTest {
    @Test
    public void T_add() {
        try {

            String encoding = "GBK";
            File file = new File("/Users/liyanwen/Desktop/13-15-02.log");
            HashSet<Integer> apptype = new HashSet<Integer>();
            apptype.add(16);
            apptype.add(20);
            apptype.add(33);
            apptype.add(34);
            apptype.add(38);
            apptype.add(42);
            apptype.add(44);
            apptype.add(45);
            String dstPath = "/Users/liyanwen/Desktop/lyw02.log";
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                BufferedWriter bw = new BufferedWriter(new FileWriter(dstPath));
                ;
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    if (lineTxt.contains("doSaveReportConsumeLog Exception")) {

                        String data = lineTxt.substring(lineTxt.indexOf("|{") + 1, lineTxt.indexOf("}org") + 1);

                        BillingInfo cmqMsg = GsonUtil.fromJson(data, BillingInfo.class);
                        long amount = 0;
                        for (MessageItem messageItem : cmqMsg.getItemList()) {
                            amount = amount + messageItem.getAmount();
                        }
                        int type = 3;

                        if (apptype.contains(cmqMsg.getAppType())) {
                            type = 2;
                        }
                        //写文件
                        bw.write(type+","+cmqMsg.getAppId()+","+cmqMsg.getAreaId()+","+cmqMsg.getUserGuid()+","+amount);
                        bw.newLine();//换行
                        bw.flush();//强制输出缓冲区的内容，避免数据缓存，造成文件写入不完整的情况。

                        System.out.println(type + "," + cmqMsg.getAppId() + "," + cmqMsg.getAreaId() + "," + cmqMsg.getUserGuid() + "," + amount);
                    }

                }
                bw.close();
                bufferedReader.close();
                read.close();
            } else {
                System.out.println("CheckMonthInfoService|check|error file not");
            }
        } catch (
                Exception e) {
            System.out.println("CheckMonthInfoService|check|error file error:" + e.getMessage());
        }
        System.out.print("end");
    }
}

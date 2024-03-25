package cn.liyw.page;


import cn.liyw.service.GsonUtil;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class ExportTest {
    @Test
    public void T_add() {
        Map<Integer, Integer> map = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        try {

            String encoding = "GBK";
            File file = new File("/Users/liyanwen/Desktop/log.log");
            HashSet<Integer> apptype = new HashSet<Integer>();
            String dstPath = "/Users/liyanwen/Desktop/lyw02.log";
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                BufferedWriter bw = new BufferedWriter(new FileWriter(dstPath));

                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    String[] data = lineTxt.split(" ");
                    if (Objects.isNull(map.get(Integer.parseInt(data[0])))) {
                        map.put(Integer.parseInt(data[0]), 1);
                    } else {
                        map.put(Integer.parseInt(data[0]), map.get(Integer.parseInt(data[0])) + 1);
                    }
                }
                for (Integer key : map.keySet()) {
                    //写文件
                    bw.write(key + ":" + map.get(key));
                    bw.newLine();//换行
                    bw.flush();//强制输出缓冲区的内容，避免数据缓存，造成文件写入不完整的情况。
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
        System.out.print(GsonUtil.toJson(map));
    }
}

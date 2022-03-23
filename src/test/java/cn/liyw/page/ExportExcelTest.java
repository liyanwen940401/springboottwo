package cn.liyw.page;


import org.apache.poi.hssf.usermodel.*;
import org.junit.Test;

import java.io.*;
import java.util.HashSet;

public class ExportExcelTest {
    @Test
    public void T_add() {
        try {

            String encoding = "GBK";
            File file = new File("/Users/liyanwen/Desktop/lyw02-01.log");
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int i = 1;
                //  创建一个工作簿
                HSSFWorkbook wb = new HSSFWorkbook();
                //  创建一个工作表
                HSSFSheet sheet = wb.createSheet();
                //  创建字体
                HSSFFont font1 = wb.createFont();
                HSSFFont font2 = wb.createFont();
                font1.setFontHeightInPoints((short) 14);
                font2.setFontHeightInPoints((short) 12);
                //  创建单元格样式
                HSSFCellStyle css1 = wb.createCellStyle();
                HSSFCellStyle css2 = wb.createCellStyle();
                HSSFDataFormat df = wb.createDataFormat();
                //  设置单元格字体及格式
                css1.setFont(font1);
                css1.setDataFormat(df.getFormat("#,##0.0"));
                css2.setFont(font2);
                css2.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
                while ((lineTxt = bufferedReader.readLine()) != null) {

                    String[] data = lineTxt.split(",");
                    if(data[3].equals("4314757592")){
                        continue;
                    }
                    HSSFRow row = sheet.createRow(i);
                    HSSFCell cell = row.createCell(0);
                    cell.setCellValue(data[1]);
                    cell = row.createCell(1);
                    cell.setCellValue(data[2]);
                    cell = row.createCell(2);
                    cell.setCellValue(data[3]);
                    cell = row.createCell(3);
                    if(data[0].equals("2")) {
                        cell.setCellValue("30003");
                    }else{
                        cell.setCellValue("30010");
                    }
                    cell = row.createCell(4);
                    cell.setCellValue(data[4]);
                    cell = row.createCell(5);
                    cell.setCellValue("订阅累积消费满足金额发放月票");
                    cell.setCellStyle(css2);
                    i++;
                }


                //  写文件
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream("/Users/liyanwen/Desktop/wb.xls");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    wb.write(fos);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                bw.close();
//                bufferedReader.close();
//                read.close();
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

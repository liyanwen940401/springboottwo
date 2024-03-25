package cn.liyw.page;

import cn.liyw.Application;
import cn.liyw.dao.UserDao;
import cn.liyw.domin.User;
import cn.liyw.service.GsonUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class excelPutCos {
    @Autowired
    private UserDao userDao;

    public COSClient getCOSClient() {
        try {
            // 1 初始化用户身份信息（secretId, secretKey）。
            COSCredentials cred = new BasicCOSCredentials("xxxxx", "xxxxx");
            // 2 设置 bucket 的地域
            Region region = new Region("na-toronto");
            ClientConfig clientConfig = new ClientConfig(region);
            clientConfig.setHttpProtocol(HttpProtocol.https);
            // 3 生成 cos 客户端。
            return new COSClient(cred, clientConfig);
        } catch (Exception e) {
        }
        return null;
    }

    @Test
    public void T_add() throws Exception {
        List<User> activities = userDao.queryUserList();
        //创建Excel文件，并把activityList写入到excel中
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建第一页
        HSSFSheet sheet = workbook.createSheet("activities");
        //创建第一行
        HSSFRow row = sheet.createRow(0);
        //创建第一列
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("ID");
        //追加列第二列
        cell = row.createCell(1);
        cell.setCellValue("所有者");
        cell = row.createCell(2);
        cell.setCellValue("名称");
        cell = row.createCell(3);
        cell.setCellValue("开始日期");
        cell = row.createCell(4);
        cell.setCellValue("结束日期");
        cell = row.createCell(5);
        cell.setCellValue("成本");
        cell = row.createCell(6);
        cell.setCellValue("描述");
        cell = row.createCell(7);
        cell.setCellValue("创建时间");
        cell = row.createCell(8);
        cell.setCellValue("创建者");
        cell = row.createCell(9);
        cell.setCellValue("修改时间");
        cell = row.createCell(10);
        cell.setCellValue("修改者");

        if (activities != null && activities.size() > 0) {
            User activity = null;

            for (int i = 0; i < activities.size(); i++) {
                activity = activities.get(i);
                //追加第二行
                row = sheet.createRow(i + 1);
                //追加第二行第一列
                cell = row.createCell(0);
                cell.setCellValue(activity.getId());
                //追加第二行第二列
                cell = row.createCell(1);
                cell.setCellValue(activity.getId());
                cell = row.createCell(2);
                cell.setCellValue(activity.getName());
                cell = row.createCell(3);
                cell.setCellValue(activity.getName());
                cell = row.createCell(4);
                cell.setCellValue(activity.getName());
                cell = row.createCell(5);
                cell.setCellValue(activity.getName());
                cell = row.createCell(6);
                cell.setCellValue(activity.getName());
                cell = row.createCell(7);
                cell.setCellValue(activity.getName());
                cell = row.createCell(8);
                cell.setCellValue(activity.getName());
                cell = row.createCell(9);
                cell.setCellValue(activity.getName());
                cell = row.createCell(10);
                cell.setCellValue(activity.getName());
            }
        }
        String key = "数据20230803.xlsx";
        File f = new File(key);
        OutputStream os = new FileOutputStream(f);
        workbook.write(os);
        COSClient cosClient = getCOSClient();
        PutObjectRequest putObjectRequest = new PutObjectRequest("pay-static-1253177085", key, f);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        System.out.println(("putObjectResult end:{}" + GsonUtil.toJson(putObjectResult)));

    }

}
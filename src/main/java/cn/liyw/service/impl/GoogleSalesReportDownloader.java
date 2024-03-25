package cn.liyw.service.impl;

import cn.liyw.service.DateUtils;
import cn.liyw.service.GsonUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.storage.Storage;
import com.google.api.services.storage.StorageScopes;
import com.google.api.services.storage.model.StorageObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.LinkedHashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class GoogleSalesReportDownloader {
    public static void main(String[] args) throws IOException, GeneralSecurityException {

        // 根据您的实际情况更改这些变量
        String jsonKeyFile = "GooglePlayAndroidDeveloper.json";
        String cloudStorageBucket = "pubsite_prod_rev_1515xxxx";
        String reportToDownload = "sales/salesreport_202403.zip";

        // Configure the Google API Client
        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();

        // Load the JSON key file
        GoogleCredential credential = GoogleCredential.fromStream(Files.newInputStream(Paths.get(jsonKeyFile)))
                .createScoped(StorageScopes.all());


        // Build the Storage service
        Storage storage = new Storage.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("Google Sales Report Downloader")
                .build();

        // Perform the Storage Objects Get request
        Storage.Objects.Get getRequest = storage.objects().get(cloudStorageBucket, reportToDownload);
        StorageObject storageObject = getRequest.execute();

        MediaHttpDownloader downloader = getRequest.getMediaHttpDownloader();
        GenericUrl url = new GenericUrl(storageObject.getMediaLink());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        downloader.download(url, outputStream);
        byte[] bytes = outputStream.toByteArray();
        String zipFileName = "/Users/liyanwen/task/springboottwo/src/main/resources/file/1.zip";
        FileOutputStream fileOut = new FileOutputStream(zipFileName);
        // 将字节数组写入文件
        fileOut.write(bytes);
        // 关闭流
        fileOut.close();
        outputStream.close();
        // 解压缩文件
        String outputFolder = "/Users/liyanwen/task/springboottwo/src/main/resources/file";
        String fileName = null;
        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(Paths.get(zipFileName)))) {
            fileName = unzipFiles(zipInputStream, outputFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 读取csv文件
        ExcelReaderBuilder readerBuilder = EasyExcel.read(outputFolder+"/"+fileName, new MyEventListener());
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        readerBuilder.build().read(readSheet);
        System.out.println("2222");
    }

    public static class MyEventListener extends AnalysisEventListener<LinkedHashMap<Integer, String>> {
        @Override
        public void invoke(LinkedHashMap<Integer, String> rowData, AnalysisContext context) {
            // Handle each row of data here
            if ("Google fee".equals(rowData.getOrDefault(4, ""))) {
                return;
            }
            if ("Google fee refund".equals(rowData.getOrDefault(4, ""))) {
                return;
            }
            if ("Tax".equals(rowData.getOrDefault(4, ""))) {
                return;
            }
            if ("Tax refund".equals(rowData.getOrDefault(4, ""))) {
                return;
            }

            ReconciliationOrder dao = getReconciliationOrder(rowData);
            if (dao == null) {
                return;
            }
            if(dao.getPurchaseDate().getTime() < DateUtils.getBeforeDays(1)||dao.getPurchaseDate().getTime() > DateUtils.getBeforeDays(0)){
                return;
            }
            System.out.println(GsonUtil.toJson(dao));
//            int insert = reconciliationOrderService.insert(dao);
//            LogUtil.info("reconciliationOrderDAO insert",insert,dao.getTxnId());
//            num.getAndIncrement();
        }

        private ReconciliationOrder getReconciliationOrder(LinkedHashMap<Integer, String> rowData) {

            ReconciliationOrder dao = new ReconciliationOrder();
            try {
                dao.setTxnId(rowData.getOrDefault(0, ""));
                dao.setChannelCode("google");
                String productId = rowData.getOrDefault(6, "");
                dao.setProductId(productId);
                String itemId = rowData.getOrDefault(8, "");
                if (!"com.qidian.Int.reader".equals(productId) && !"com.chereads.reader".equals(productId) &&
                        !"com.bestnovel.reader".equals(productId)) {
                    return null;
                }
                if ("com.qidian.Int.reader".equals(productId)) {
                    dao.setBizId("QDHY");
                    if (itemId.toLowerCase().contains("coinplan")) {
                        dao.setBizId("QDHYCP");
                    }
                } else if ("com.chereads.reader".equals(productId)) {
                    dao.setBizId("QDCR");
                    if (itemId.toLowerCase().contains("coinplan")) {
                        dao.setBizId("QDCRCP");
                    }
                } else if ("com.bestnovel.reader".equals(productId)) {
                    dao.setBizId("QDBN");
                    if (itemId.toLowerCase().contains("coinplan")) {
                        dao.setBizId("QDBNCP");
                    }
                }
                dao.setItemId(itemId);
                String transactionType = rowData.getOrDefault(3, "");
                dao.setTransactionType(transactionType);
                if ("Charge".equals(transactionType) || "Charged".equals(transactionType)) {
                    dao.setTransactionStatus(TransactionStatus.Charged.getValue());
                } else if ("Charge Refund".equals(transactionType) || "Refund".equals(transactionType)) {
                    dao.setTransactionStatus(TransactionStatus.Refund.getValue());
                }
                dao.setAmount(Float.parseFloat(rowData.getOrDefault(12, "0")));
                dao.setCurrency(rowData.getOrDefault(9, ""));
                long dateTime = Long.parseLong(rowData.getOrDefault(2, ""))*1000;
                dao.setPurchaseDate(DateUtils.dateStr2Date(DateUtils.date2DateStr(dateTime)));
                dao.setDataSource("salesreport");
                return dao;
            } catch (Exception e) {
            }
            return null;
        }

        // 读取完Excel文件所有数据后都会调用该方法
        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {

        }
    }


    private static String unzipFiles(ZipInputStream zipInputStream, String outputFolder) throws IOException {
        byte[] buffer = new byte[1024];
        ZipEntry entry;
        String fileName = null;
        while ((entry = zipInputStream.getNextEntry()) != null) {
            fileName = entry.getName();
            File outputFile = new File(outputFolder + "/" + fileName);

            // 创建文件夹
            if (entry.isDirectory()) {
                outputFile.mkdirs();
            } else {
                // 创建文件并写入内容
                new File(outputFile.getParent()).mkdirs();
                try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
                    int bytesRead;
                    while ((bytesRead = zipInputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }
                }
            }

            zipInputStream.closeEntry();
        }
        return fileName;
    }
}

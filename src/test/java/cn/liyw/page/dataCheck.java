package cn.liyw.page;

import cn.liyw.Application;
import cn.liyw.dao.UserDao;
import cn.liyw.domin.User;
import cn.liyw.domin.UserBaseRiskCheckResult;
import cn.liyw.service.DateUtils;
import cn.liyw.service.GsonUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.tencentcloudapi.cls.v20201016.ClsClient;
import com.tencentcloudapi.cls.v20201016.models.LogInfo;
import com.tencentcloudapi.cls.v20201016.models.SearchLogRequest;
import com.tencentcloudapi.cls.v20201016.models.SearchLogResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class dataCheck {

    public ClsClient getClsClient() {
        try {
            Credential cred = new Credential("xxxxxx", "xxxxx");
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cls.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            return new ClsClient(cred, "na-toronto", clientProfile);
        } catch (Exception e) {
        }
        return null;
    }

    public List<String> SearchLog(SearchLogRequest req) {
        List<String> logInfoList = new ArrayList<>();
        ClsClient client = getClsClient();
        try {
            // 实例化一个请求对象,每个接口都会对应一个request对象
            int i = 0;
            int sum = 0;
            req.setSort("asc");
            long timeCurr = req.getFrom();
            List<String> logs = new ArrayList<>();
            for (; ; ) {
                // 返回的resp是一个SearchLogResponse的实例，与请求对象对应
                SearchLogResponse resp = client.SearchLog(req);
                if (resp.getResults() == null || resp.getResults().length == 0) {
                    break;
                }
                sum += resp.getResults().length;
                for (LogInfo log : resp.getResults()) {
                    //得到本次最新时间
                    if (log.getTime() > timeCurr) {
                        timeCurr = log.getTime();
                    }
                }
                for (LogInfo log : resp.getResults()) {
                    if (log.getTime() == timeCurr) {
                        logs.add(log.getLogJson());
                    } else {
                        logInfoList.add(log.getLogJson());
                    }
                }
                i++;
                if (sum % 10000 == 0) {
                    sum = 0;
                    req.setFrom(timeCurr);
                    req.setContext("");
                } else {
                    logInfoList.addAll(logs);
                    if (StringUtils.isEmpty(resp.getContext())) {
                        break;
                    }
                    req.setContext(resp.getContext());
                }
                logs = new ArrayList<>();
            }
        } catch (Exception e) {
        }
        return logInfoList;
    }

    @Test
    public void T_add() throws Exception {
        SearchLogRequest request = new SearchLogRequest();
        //查询前一天命中风控的日志
        Long startTime = DateUtils.getBeforeDays(1);
        Long endTime = DateUtils.getBeforeDays(0);
        request.setTopicId("8a46f9e5-2713-4e2e-a30f-4d4bfef47f8e");
        request.setFrom(startTime);
        request.setTo(endTime);
        request.setQuery("method:\"riskCheckResult\" AND result:\"0\" AND NOT appId:\"900\"");
        request.setLimit(1000l);
        List<String> logs = SearchLog(request);
        System.out.println("RiskResultFixJobHandler userbase size:" + logs.size());
        try {
            logs.forEach(e -> {
                UserBaseRiskCheckResult riskCheckResult = GsonUtil.fromJson(e, UserBaseRiskCheckResult.class);
                request.setTopicId("4ebed017-dd25-46ae-8d90-530426b8dc24");
                request.setQuery("checkEnv AND " + riskCheckResult.getImei());
                List<String> dataLogs = SearchLog(request);
                if (dataLogs.size() > 0) {
                    System.out.println("knobs imei:" + riskCheckResult.getImei());
                }else{
                    System.out.println("no knobs imei:" + riskCheckResult.getImei());
                }
            });
        } catch (Exception e) {
        }

    }

}
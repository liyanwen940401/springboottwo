package cn.liyw.page;

import cn.liyw.Application;
import cn.liyw.domin.Inparam;
import cn.liyw.domin.RiskRule;
import cn.liyw.domin.User;
import cn.liyw.risk.RiskModule;
import cn.liyw.risk.RiskModuleFactory;
import cn.liyw.service.GsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class test2 {

    @Test
    public void T_add() throws Exception {
        String str ="{\"result\":\"true\",\"inparam\":{\"deviceType\":\"\",\"appVersion\":\"\",\"isSlave\":1," +
                "\"clinetType\":0,\"extParams\":\"{\\\"report\\\":\\\"ip\\\",\\\"source\\\":\\\"checkIn\\\"}\"," +
                "\"riskVersion\":0,\"userAgent\":\"\",\"deviceName\":\"\",\"ywguid\":4322973567,\"areaId\":1,\"cbId\":0,\"osVersion\":\"\",\"riskType\":\"checkIn\",\"cAuthorId\":0,\"ccId\":0,\"appId\":900,\"clientIp\":\"93.42.32.132\",\"imei\":\"\"},\"method\":\"planRet\",\"nodePath\":\"[\\\"pvLogQueryModule\\\",\\\"checkEnvRiskModule\\\"]\",\"planId\":\"11002\",\"riskId\":\"11001\"}";

        Inparam param = GsonUtil.fromJson(str, Inparam.class);
        System.out.println("2222");

    }

}
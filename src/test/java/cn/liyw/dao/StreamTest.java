package cn.liyw.dao;

import cn.liyw.Application;
import cn.liyw.domin.AccountInfo;
import cn.liyw.service.DateUtils;
import cn.liyw.service.GsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class StreamTest {

    @Test
    public void T_add() throws Exception {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setAccount("123");
        accountInfo.setAccountType(1);
        accountInfo.setRegeisterTime("2019-07-24 12:00:11");
        AccountInfo accountInfo1 = new AccountInfo();
        accountInfo1.setAccount("123");
        accountInfo1.setAccountType(2);
        accountInfo1.setRegeisterTime("2018-07-24 12:00:11");

        List<AccountInfo> list = new java.util.ArrayList<>();
        list.add(accountInfo);
        list.add(accountInfo1);
        AccountInfo targetAccount = list.get(0);
        for (AccountInfo account : list) {
            if (DateUtils.getTimeInMilSecond(account.getRegeisterTime()) < DateUtils.getTimeInMilSecond(targetAccount.getRegeisterTime())) {
                targetAccount = account;
            }
        }
        System.out.println(GsonUtil.toJson(targetAccount));
    }
}
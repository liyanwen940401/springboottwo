package cn.liyw.risk.impl;

import cn.liyw.domin.User;
import cn.liyw.future.PromissionFuture;
import cn.liyw.risk.RiskModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 账号风控类型命中模块
 * riskid（多选），banid（多选），time（最近多少时间分钟），count（次数，比如0-3起止次数）
 * 如果time不选，则不对时间做过滤
 *
 * @author wj
 */
@Component("a")
public class AccountHitRiskModule implements RiskModule {

    private PromissionFuture future;

    @Autowired
    public AccountHitRiskModule(PromissionFuture future) {
        this.future = future;
    }

    @Override
    public Boolean invokeAsync(User entity) {

        System.out.println("a.invokeAsync===false");
        return false;
    }

}
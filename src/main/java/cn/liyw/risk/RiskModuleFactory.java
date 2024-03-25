package cn.liyw.risk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * YuewenRiskControl
 * @author zhangzaiyuan
 * @date 2019/8/2
 */
@Component("riskModuleFactory")
public class RiskModuleFactory {

    private final Map<String, RiskModule> riskModuleMap;

    @Autowired
    public RiskModuleFactory(Map<String, RiskModule> riskModuleMap) {
        this.riskModuleMap = riskModuleMap;
    }

    /**
     * 根据模块名获取模块对象
     * @param moduleName 风控模块名字
     * @return 风控模块
     */
    public RiskModule create(String moduleName) {
        return riskModuleMap.get(moduleName);
    }

}

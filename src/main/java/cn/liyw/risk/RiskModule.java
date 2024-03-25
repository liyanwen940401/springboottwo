package cn.liyw.risk;


import cn.liyw.domin.User;

/**
 * YuewenRiskControl
 *
 * @author zhangzaiyuan
 * @date 2019/8/2
 */
public interface RiskModule {
    /**
     * 模块执行异步方法
     *
     * @param entity 风控模块入参
     * @return
     */
    Boolean invokeAsync(User entity);
}

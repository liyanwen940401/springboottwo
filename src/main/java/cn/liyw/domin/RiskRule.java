package cn.liyw.domin;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * created on 2019/7/24 14:37
 * 风控规则对象
 *
 * @author zhangalong
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiskRule implements Comparable<RiskRule> {
    /**
     * 规则ID自增，主键Id
     */
    @Expose()
    private int ruleId;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 应用编号
     */
    private int appId;

     /**
     * 应用编号
     */
    private int areaId;

    /**
     * 风控类型
     */
    @Expose()
    private int riskId;

    /**
     * 风控策略
     */
    @Expose()
    private int banId;

    /**
     * 添加人
     */
    private String addUser;

    /**
     * 添加时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 状态，0正常，1关闭
     */
    private int status;

    /**
     * 备注
     */
    private String memo;

    /**
     * 风控验证过期时间(分钟)
     */
    private int expireTime;

    /**
     * 风控验证使用次数
     */
    private int checkUsageTimes;
    /**
     * banId的优先级
     */
    @Expose()
    private int sortId;

    public int compareTo(RiskRule o) {
        return this.banId - o.banId;
    }
}

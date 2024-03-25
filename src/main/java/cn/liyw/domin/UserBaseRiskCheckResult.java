package cn.liyw.domin;

import lombok.Data;

@Data
public class UserBaseRiskCheckResult {
    private String result;
    private String reason;
    private String areaId;
    private String method;
    private String accountType;
    private String appId;
    private String imei;
    private String email;
    private String riskId;
}

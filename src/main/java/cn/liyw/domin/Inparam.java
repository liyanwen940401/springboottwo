package cn.liyw.domin;

import lombok.Data;

@Data
public class Inparam {
    private CommonInparam inparam;
    private String result;
    private String riskId;
    private String planId;
    @Data
    public static class CommonInparam {
        public String userAgent = "";
        public String osVersion = "";
        public String deviceType = "";
        public String deviceName = "";
        public int clinetType = 0;
        public String imei = "";
        public int appId = 0;
        public int areaId = 0;
        public String appVersion = "";
        public String clientIp = "";
        public long ywguid = 0L;
        public String riskType = "";
        public int isSlave = 0;
        public int riskVersion = 0;
        public long cbId = 0L;
        public long ccId = 0L;
        public long cAuthorId = 0L;
        public String extParams = null;

    }
}

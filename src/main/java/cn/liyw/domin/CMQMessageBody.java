package cn.liyw.domin;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * cmq消息体
 *
 * @author liujianqin
 */
public class CMQMessageBody {

    private Integer appId;
    private Integer areaId;
    private Integer channelId;
    private Long userGuid;
    private Integer userType;
    private String userIp;
    private String source;
    private String version;
    private String memo;
    private String imei;
    private String country;
    private Long activityId;
    private Integer activityType;
    private String client;
    private String clientOrderId;
    private String remoteIp;
    private String sessionyw;
    private Integer bizzType;

    public List<CMQMessageGiftBody> giftBodys = Lists.newArrayList();

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Long getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(Long userGuid) {
        this.userGuid = userGuid;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public String getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(String clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public List<CMQMessageGiftBody> getGiftBodys() {
        return giftBodys;
    }

    public void addGiftBody(CMQMessageGiftBody giftBody) {
        this.giftBodys.add(giftBody);
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public String getSessionyw() {
        return sessionyw;
    }

    public void setSessionyw(String sessionyw) {
        this.sessionyw = sessionyw;
    }

    public Integer getBizzType() {
        return bizzType;
    }

    public void setBizzType(Integer bizzType) {
        this.bizzType = bizzType;
    }

}

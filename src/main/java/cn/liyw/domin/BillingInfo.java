package cn.liyw.domin;


import java.util.List;


public class BillingInfo {

	private String orderId;
	/**
	 * 用户id
	 */
	private Long userGuid;

	private Integer appId;

	private Integer areaId;

	/**
	 * 消费类型
	 */
	private Integer appType;

	/**
	 * 书本号
	 */
	private Long cbId;

	/**
	 * 道具id
	 */
	private Long itemId;

	private String userIp ;

	private String remoteIp;

	/**
	 * 计费国家
	 */
	private String country;

	/**
	 * 1 免费 2 有价 3 免费+有价
	 */
	private Integer billingType;

	/**
	 * 时间
	 */
	private Long createTime;

	private String imei;

	/**
	 * 扣费详情
	 */
	private List<MessageItem> itemList;

	private String memo;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getUserGuid() {
		return userGuid;
	}

	public void setUserGuid(Long userGuid) {
		this.userGuid = userGuid;
	}

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

	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public Long getCbId() {
		return cbId;
	}

	public void setCbId(Long cbId) {
		this.cbId = cbId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getBillingType() {
		return billingType;
	}

	public void setBillingType(Integer billingType) {
		this.billingType = billingType;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public List<MessageItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<MessageItem> itemList) {
		this.itemList = itemList;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	

}


package cn.liyw.domin;

/**
 * 道具类型
 *
 */
public enum ActivityItemType {
	NO_CURRENCY("非货币", 0), CURRENCY("货币", 1), COUPON("限免券", 4),DISCOUNTCOUPON("折扣券", 5),POINTS("积分", 6),FASTPASS("读书券", 7),MONTHVIP("会员", 8),BADGE("会员", 9),FRAME("挂件",10);

	private String description;
	private Integer value;

	ActivityItemType(String description, Integer value) {
		this.description = description;
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}

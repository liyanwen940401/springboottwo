package cn.liyw.domin;

public class MessageItem {
	/**
	 * 货币类型 4 免费币 5 有价币
	 */
	private int curyId;

	/**
	 * 消耗金额
	 */
	private int amount;

	/**
	 * 余额
	 */
	private long balance;

	public int getCuryId() {
		return curyId;
	}

	public void setCuryId(int curyId) {
		this.curyId = curyId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}
	
	

}

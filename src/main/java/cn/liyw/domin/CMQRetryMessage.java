package cn.liyw.domin;

/**
 * 礼包重发机制
 *
 * @author liujianqin
 */
public class CMQRetryMessage {

    // 重试次数
    private int tryTimes;
    // 重发礼包
    private String giftMsg;

    // 消息队列名称
    private String queueName;

    public CMQRetryMessage(String queueName, String giftMsg) {
        this.queueName = queueName;
        this.giftMsg = giftMsg;
        this.tryTimes = 1;

    }

    public int getTryTimes() {
        return tryTimes;
    }

    public void incrementTimes() {
        this.tryTimes++;
    }

    public String getGiftMsg() {
        return giftMsg;
    }

    public String getQueueName() {
        return queueName;
    }

}

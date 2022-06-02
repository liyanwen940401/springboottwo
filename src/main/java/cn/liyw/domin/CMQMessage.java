package cn.liyw.domin;

/**
 * CMQ消息
 *
 * @author liujianqin
 */
public class CMQMessage {

    // 消息ID
    private Long msgId;

    // 消息体
    private CMQMessageBody messageBody;

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public CMQMessageBody getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(CMQMessageBody messageBody) {
        this.messageBody = messageBody;
    }

}

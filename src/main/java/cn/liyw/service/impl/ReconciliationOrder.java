package cn.liyw.service.impl;

import lombok.Data;

import java.util.Date;

/**
 * 对账订单
 *
 * @author liyuping
 */
@Data
public class ReconciliationOrder {

    private String txnId;
    private String productId;
    private String channelCode;
    private String itemId;
    private float amount;
    private String currency;
    private String bizId;
    private String transactionType;
    private Date purchaseDate;
    private Date createTime;
    private String memo=" ";
    private int transactionStatus;
    private String dataSource;


}

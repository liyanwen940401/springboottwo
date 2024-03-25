package cn.liyw.service.impl;

public enum TransactionStatus {

    UNKNOWN(0),
    Charged(1),
    Refund(2);

    private int value;

    TransactionStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

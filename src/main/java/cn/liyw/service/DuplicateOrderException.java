package cn.liyw.service;

/**
 * 重复订单异常
 * 
 * @author zhanghaipeng
 * @date 2019/8/23
 * @version 1.0
 *
 **/
public class DuplicateOrderException extends RuntimeException {

    public DuplicateOrderException(String msg) {
        super(msg);
    }
}

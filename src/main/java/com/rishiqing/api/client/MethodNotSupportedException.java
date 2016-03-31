package com.rishiqing.api.client;

/**
 * 方法不受支持的异常
 * User: user 毛文强
 * Date: 2016/3/31
 * Time: 15:02
 * To change this template use File | Settings | File Templates.
 */
public class MethodNotSupportedException extends RuntimeException {

    public MethodNotSupportedException(String msg){
        super(msg);
    }
}

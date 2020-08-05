package com.boollan.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author Boollan
 */
public interface IMemberPaymentSystem {

    /**
     * 创建支付订单
     * @param request  请求头
     * @param response  响应体
     * @param price  支付的价格
     * @param type 支付类型  1：支付宝 2：QQ钱包 3：微信
     * @param payId 支付人的唯一标识
     * @param param 自定义一些参数 支付后返回
     */
    void paymentCreatOrder(HttpServletRequest request, HttpServletResponse response, String price, String type, String payId, String param) throws ServletException, IOException;


    /**
     * 支付回调验证
     * @param requestParams 回调的信息
     * @return 返回订单回调是否正确
     */
    boolean paymentOrderCall(Map requestParams) throws NoSuchAlgorithmException;

}

package com.boollan.service.impl;

import com.boollan.service.IMemberPaymentSystem;
import com.boollan.util.module.HttpSteamInfo;
import com.boollan.util.module.LoginValidation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author Boollan
 */
public class MemberPaymentSystem implements IMemberPaymentSystem {

    private String url;
    private String callUrl;
    private String returnUrl;
    private String token;
    private String codePayId;
    private String key;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCallUrl(String callUrl) {
        this.callUrl = callUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCodePayId(String codePayId) {
        this.codePayId = codePayId;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void paymentCreatOrder(HttpServletRequest request, HttpServletResponse response, String price, String type, String payId, String param) throws ServletException, IOException {
        response.sendRedirect(url + "?id=" + codePayId + "&pay_id=" + payId + "&price=" + price + "&type=" + type + "&token=" + token + "&param=" + param + "&notify_url=" + callUrl + "&return_url=" + returnUrl);
    }

    @Override
    public boolean paymentOrderCall(Map requestParams) throws NoSuchAlgorithmException {

        if (requestParams != null) {
            LoginValidation visitor = new LoginValidation();
            //验证订单回调是否正确
            boolean isCall = visitor.paymentCallVerifier(requestParams, key);
            if (isCall) {
                return true;
            }
        }
        return false;
    }

}

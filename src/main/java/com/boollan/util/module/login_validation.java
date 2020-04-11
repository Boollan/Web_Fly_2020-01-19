package com.boollan.util.module;



import com.dingxianginc.ctu.client.CaptchaClient;
import com.dingxianginc.ctu.client.model.CaptchaResponse;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Properties;


public class login_validation {

    //继承接口 Ilogin_validation

    //登录格式验证
    public boolean loginandRegvalidation(String UserName_Validation, String Password_Validation) {
        if (UserName_Validation.trim() != null && Password_Validation.trim() != null) {

            if (UserName_Validation.trim().length() < 16 && UserName_Validation.trim().length() > 1) {

                if (Password_Validation.trim().length() < 24 && Password_Validation.trim().length() > 7) {
                    return true;
                }

            }

        }
        return false;
    }

    //用户注册格式验证
    public boolean reg_validation(String Email, String UserName, String Password) {
        return true;
    }

    //人机验证
    public boolean ImgeVerification(String token) {

        try {
            /**构造入参为appId和appSecret
             * appId和前端验证码的appId保持一致，appId可公开
             * appSecret为秘钥，请勿公开
             * token在前端完成验证后可以获取到，随业务请求发送到后台，token有效期为两分钟
             * ip 可选，提交业务参数的客户端ip
             **/

            Properties properties = new Properties();
            // 使用ClassLoader加载properties配置文件生成对应的输入流
            InputStream in = login_validation.class.getClassLoader().getResourceAsStream("JavaConfig/JavaConfig.properties");
            // 使用properties对象加载输入流
            properties.load(in);
            //获取key对应的value值
            String appId = properties.getProperty("appId");
            String appSecret = properties.getProperty("appSecret");

            CaptchaClient captchaClient = new CaptchaClient(appId, appSecret);
            //captchaClient.setCaptchaUrl(captchaUrl);
            //特殊情况需要额外指定服务器,可以在这个指定，默认情况下不需要设置
            CaptchaResponse response = captchaClient.verifyToken(token);
            //CaptchaResponse response = captchaClient.verifyToken(token, ip);
            //针对一些token冒用的情况，业务方可以采集客户端ip随token一起提交到验证码服务，验证码服务除了判断token的合法性还会校验提交业务参数的客户端ip和验证码颁发token的客户端ip是否一致
//            System.out.println(response.getCaptchaStatus());
            //确保验证状态是SERVER_SUCCESS，SDK中有容错机制，在网络出现异常的情况会返回通过
            //System.out.println(response.getIp());
            //验证码服务采集到的客户端ip
            if (response.getResult()) {
                /**token验证通过，继续其他流程**/
                return true;
            } else {
                return false;
                /**token验证失败，业务系统可以直接阻断该次请求或者继续弹验证码**/
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}



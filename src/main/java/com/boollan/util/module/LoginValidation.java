package com.boollan.util.module;

import com.dingxianginc.ctu.client.CaptchaClient;
import com.dingxianginc.ctu.client.model.CaptchaResponse;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author Boollan
 */
public class LoginValidation {


    /**
     * 构造入参为appId和appSecret
     * appId和前端验证码的appId保持一致，appId可公开
     * appSecret为秘钥，请勿公开
     * token在前端完成验证后可以获取到，随业务请求发送到后台，token有效期为两分钟
     * ip 可选，提交业务参数的客户端ip
     * @param token 安全码
     * @return 是否正确
     */
    public boolean imageVerification(String token) {

        try {
            Properties properties = new Properties();
            // 使用ClassLoader加载properties配置文件生成对应的输入流
            InputStream in = LoginValidation.class.getClassLoader().getResourceAsStream("db.properties");
            // 使用properties对象加载输入流
            assert in != null;
            properties.load(in);
            //获取key对应的value值
            String appId = properties.getProperty("appId");
            String appSecret = properties.getProperty("appSecret");

            CaptchaClient captchaClient = new CaptchaClient(appId, appSecret);
            //特殊情况需要额外指定服务器,可以在这个指定，默认情况下不需要设置
            CaptchaResponse response = captchaClient.verifyToken(token);
            //验证码服务采集到的客户端ip
            if(response.getResult()) {
                /*token验证通过，继续其他流程**/
                return true;
            } else {
                return false;
                /*token验证失败，业务系统可以直接阻断该次请求或者继续弹验证码**/
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean paymentCallVerifier(Map requestParams , String key) throws NoSuchAlgorithmException {
        /*
         *验证通知 处理自己的业务
         */
        //申明hashMap变量储存接收到的参数名用于排序
        Map<String,String> params = new HashMap<>();
        //申明字符变量 保存接收到的变量
        String valueStr = "";
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            valueStr = values[0];
            //增加到params保存
            params.put(name, valueStr);
        }
        //转为数组
        List<String> keys = new ArrayList<String>(params.keySet());
        //重新排序
        Collections.sort(keys);
        String prestr = "";
        //获取接收到的sign 参数
        String sign= params.get("sign");
        //遍历拼接url 拼接成a=1&b=2 进行MD5签名
        for (int i = 0; i < keys.size(); i++) {
            String key_name = keys.get(i);
            String value = params.get(key_name);
            //跳过这些 不签名
            if(value== null || value.equals("") ||key_name.equals("sign")){
                continue;
            }
            if (prestr.equals("")){
                prestr =  key_name + "=" + value;
            }else{
                prestr =  prestr +"&" + key_name + "=" + value;
            }
        }
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update((prestr+key).getBytes());
        String  mySign = new BigInteger(1, md.digest()).toString(16).toLowerCase();
        if(mySign.length()!=32) {mySign="0"+mySign;}
        if(mySign.equals(sign)){
            return true;
        }else{
            //参数不合法
            return false;
        }
    }


}



package com.boollan.controller;

import java.net.URLDecoder;

import com.alibaba.fastjson.JSONObject;
import com.boollan.dao.*;
import com.boollan.domain.CommodityList;
import com.boollan.domain.L4d2OpList;
import com.boollan.domain.L4d2VipList;
import com.boollan.service.IMemberPaymentSystem;
import com.boollan.util.module.HttpSteamInfo;
import com.boollan.util.module.LoginValidation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;


/**
 * @author Boollan
 */
@RequestMapping(path = "/Payment")
public class Payment {

    private IMemberPaymentSystem payment;

    private ICommodityListDao commodity;

    private IL4d2VipListDao il4d2VipListDao;

    private IL4d2OpListDao il4d2OpListDao;

    public void setPayment(IMemberPaymentSystem payment) {
        this.payment = payment;
    }

    public void setCommodity(ICommodityListDao commodity) {
        this.commodity = commodity;
    }

    public void setIl4d2OpListDao(IL4d2OpListDao il4d2OpListDao) {
        this.il4d2OpListDao = il4d2OpListDao;
    }

    public void setIl4d2VipListDao(IL4d2VipListDao il4d2VipListDao) {
        this.il4d2VipListDao = il4d2VipListDao;
    }

    /**
     * 创建支付订单
     */
    @RequestMapping(path = "/CratePaymentOrder", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void getCratePaymentOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        JSONObject result = new JSONObject();

        //支付渠道
        String type = request.getParameter("type");
        //充值的用户
        String payId = request.getParameter("payId");
        //商品唯一ID
        result.put("productId", request.getParameter("productId"));
        //商品类型
        result.put("productType", request.getParameter("productType"));
        //获取商品的价格
        CommodityList productByList = commodity.findProductByList(request.getParameter("productId"));
        //价格
        String price = productByList.getProductMoney();

        payment.paymentCreatOrder(request, response, price, type, payId, result.toString());


    }


    /**
     * 订单支付完成后回调
     */
    @RequestMapping(path = "/CratePaymentCall", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getCratePaymentCall(HttpServletRequest request) throws NoSuchAlgorithmException, IOException {
        //验证回调信息的安全性
        if (request.getParameterMap() != null && payment.paymentOrderCall(request.getParameterMap())) {

            //用户唯一标识
            String pay_id = request.getParameter("pay_id");
            //付款金额
            String money = request.getParameter("money");
            //提交的金额
            String price = request.getParameter("price");
            //自定义信息结果集
            JSONObject jsonObject = JSONObject.parseObject(request.getParameter("param"));
            //商品唯一ID
            String productId = jsonObject.getString("productId");
            //商品类型
            String productType = jsonObject.getString("productType");

            CommodityList productByList = commodity.findProductByList(productId);
            //取回的商品金额
            String productMoney = productByList.getProductMoney();
            //判断金额是否正确
            if (Double.valueOf(money).equals(Double.valueOf(productMoney))) {

                //判断是 1 （VIP） 2（OP）
                if ("1".equals(productType)) {
                    //查询当前用户
                    L4d2VipList vipBySteamId32 = il4d2VipListDao.findVipBySteamId32(pay_id.trim());
                    //获取卡密中的时间
                    long datetime = Integer.parseInt(productByList.getProductTime());
                    //当前时间（秒）
                    long time = System.currentTimeMillis() / 1000;
                    //通过Http接口获取数据
                    HttpSteamInfo steamInfo = new HttpSteamInfo();
                    //判断数据库是否有该用户
                    if (vipBySteamId32 != null) {
                        //玩家当前时间
                        long datelined = Integer.parseInt(vipBySteamId32.getDatetimeov());
                        //判断当前VIP用户是否已经过期
                        if (datelined > time) {

                            //当前时间 + CDK固定值 （秒）
                            String dataResult = Long.toString((datelined + datetime));

                            //给已经存在VIP叠加时间
                            vipBySteamId32.setDatetimeov(dataResult);
                            vipBySteamId32.setGamename(steamInfo.getHttpSteamIdToName(pay_id.trim()));
                            il4d2VipListDao.upDateL4d2VipListInfo(vipBySteamId32);
                            return "success";

                        } else {

                            //当前时间 + CDK固定值 （秒）
                            String dataResult = Long.toString((time + datetime));

                            //玩家VIP已过期直接覆盖不需要叠加
                            vipBySteamId32.setDatetimeov(dataResult);
                            vipBySteamId32.setGamename(steamInfo.getHttpSteamIdToName(pay_id.trim()));
                            il4d2VipListDao.upDateL4d2VipListInfo(vipBySteamId32);
                            return "success";

                        }
                    } else {


                        //没有改VIP玩家 创建一个Vip玩家 并且给与时间
                        L4d2VipList newVipUser = new L4d2VipList();
                        newVipUser.setSteamid32(pay_id.trim());
                        newVipUser.setGamename(steamInfo.getHttpSteamIdToName(pay_id.trim()));
                        newVipUser.setDatetimeov(Long.toString((time + datetime)));
                        il4d2VipListDao.insertVipInfo(newVipUser);
                        return "success";

                    }

                }

                if ("2".equals(productType)) {

                    //查询当前用户
                    L4d2OpList opBySteamId32 = il4d2OpListDao.findOpBySteamId32(pay_id.trim());
                    //获取卡密中的时间
                    long datetime = Integer.parseInt(productByList.getProductTime());
                    //当前时间（秒）
                    long time = System.currentTimeMillis() / 1000;
                    //通过Http接口获取数据
                    HttpSteamInfo steamInfo = new HttpSteamInfo();
                    //判断数据库是否有该用户
                    if (opBySteamId32 != null) {
                        //玩家当前时间
                        long datelined = Integer.parseInt(opBySteamId32.getDatetimeov());
                        //判断当前OP用户是否已经过期
                        if (datelined > time) {
                            //当前时间 + CDK固定值 （秒）
                            String dataResult = Long.toString((datelined + datetime));
                            //给已经存在OP叠加时间
                            opBySteamId32.setDatetimeov(dataResult);
                            opBySteamId32.setGamename(steamInfo.getHttpSteamIdToName(pay_id.trim()));
                            il4d2OpListDao.upDateL4d2OpListInfo(opBySteamId32);

                            return "success";
                        } else {

                            //当前时间 + CDK固定值 （秒）
                            String dataResult = Long.toString((time + datetime));

                            //玩家OP已过期直接覆盖不需要叠加
                            opBySteamId32.setDatetimeov(dataResult);
                            opBySteamId32.setGamename(steamInfo.getHttpSteamIdToName(pay_id.trim()));
                            il4d2OpListDao.upDateL4d2OpListInfo(opBySteamId32);

                            return "success";
                        }
                    } else {


                        //没有改OP玩家 创建一个OP玩家 并且给与时间
                        L4d2OpList newOpUser = new L4d2OpList();
                        newOpUser.setSteamid32(pay_id.trim());

                        newOpUser.setGamename(steamInfo.getHttpSteamIdToName(pay_id.trim()));
                        newOpUser.setDatetimeov(Long.toString((time + datetime)));
                        il4d2OpListDao.insertOpInfo(newOpUser);

                        return "success";

                    }
                }

            }
        }
        return "No";
    }


    /**
     * 支付状态页面
     *
     * @return true
     */
    @RequestMapping(value = "/CratePaymentSusses")
    public ModelAndView getCratePaymentSusses(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //支付状态
        String status = request.getParameter("status");
        //用户唯一标识
        String pay_id = request.getParameter("pay_id");
        //付款金额
        String money = request.getParameter("money");
        //支付方式
        String type = request.getParameter("type");
        //流水号
        String pay_no = request.getParameter("pay_no");
        //自定义参数
        String param = request.getParameter("param");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("VuePanel/userPanel/module/Membersbuy/paymentstatus");
        //账号
        modelAndView.addObject("steamId64",pay_id);
        //金额
        modelAndView.addObject("money",money);
        //流水号
        modelAndView.addObject("pay_no",pay_no);



        // 0：成功 -1:失败 -2:参数有误
        if ("0".equals(status)){
            modelAndView.addObject("status","支付成功,会员已到账.");
        }else {
            modelAndView.addObject("status","支付成功,steamid输入错误或其他错误.");
        }

        //支付方式 1：支付宝 2：QQ钱包 3：微信支付。默认值：1
        if ("1".equals(type)){
            modelAndView.addObject("payType","支付宝");
        }else if("2".equals(type)) {
            modelAndView.addObject("payType","QQ钱包");
        }else if ("3".equals(type)){
            modelAndView.addObject("payType","微信支付");
        }else {
            modelAndView.addObject("payType","未知");
        }

        //获取自定义参数
        JSONObject jsonObject = JSONObject.parseObject(URLDecoder.decode(param,"utf-8"));

        //商品唯一ID
        String productId = jsonObject.getString("productId");

        //获取商品列表
        CommodityList productByList = commodity.findProductByList(productId);

        //steam信息
        HttpSteamInfo steamInfo  = new HttpSteamInfo();

        //游戏里玩家名称
        modelAndView.addObject("gameName",steamInfo.getHttpSteamIdToName(pay_id));

        //商品名称
        modelAndView.addObject("productName",productByList.getProductName());

        return modelAndView;
    }

}

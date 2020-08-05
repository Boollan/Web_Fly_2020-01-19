package com.test;

import com.boollan.dao.IL4d2OpCdkDao;
import com.boollan.domain.L4d2OpCdk;
import com.boollan.service.IL4d2GameInfo;
import com.boollan.service.IMemberSystem;
import com.boollan.service.IMemberSystemAdmin;
import com.boollan.service.IUserInformationSystem;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class test {
    @Test
    public void test() throws IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("Spring-config.xml","Spring-service-config.xml");
        IMemberSystem IMemberSystem = (IMemberSystem) ac.getBean("memberSystem");
        IMemberSystem.memberOpExchange("STEAM_1:0:123810748","BNYUQ7pS4x1dheBH");
    }

    @Test
    public void test1() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("Spring-config.xml","Spring-service-config.xml");
        IMemberSystemAdmin iMemberSystemAdmin = (IMemberSystemAdmin) ac.getBean("memberSystemAdmin");
        iMemberSystemAdmin.memberSystemAdminCdkGenerateOp(1,"86400","xiaowei123++.1.");

    }
    @Test
    public void test2() throws IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("Spring-config.xml","Spring-service-config.xml");
        IMemberSystem IMemberSystem = (IMemberSystem) ac.getBean("memberSystem");
        IMemberSystem.memberVipExchange("STEAM_1:0:123810748","BNYUQ7pS4x1dheBH");

    }

    @Test
    public void test3() throws IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("Spring-config.xml","Spring-service-config.xml");
        IL4d2GameInfo IL4d2GameInfo = (IL4d2GameInfo) ac.getBean("l4d2GameInfo");
        List<Map<String, String>> l4d2GameCcrList = IL4d2GameInfo.getL4d2GameCcrList();

        for (Map<String, String> map : l4d2GameCcrList){
            String res = "地图:"+map.get("mapName")+"|玩家:" + map.get("playName") + "|通关记录"+map.get("time");
            System.out.println(res);
        }

        Map<String, String> c1m1_hotel = IL4d2GameInfo.getL4d2GameMapNameToCcr("c1m1_hotel");
        System.out.println(c1m1_hotel.toString());


    }

    @Test
    public void test4() throws IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("Spring-config.xml","Spring-service-config.xml");
        IUserInformationSystem iUserInformationSystem = (IUserInformationSystem) ac.getBean("informationSystem");
        boolean boollan1 = iUserInformationSystem.userReg("Boollan", "xiaowei123++..", "wyzaoz@163.com");
        System.out.println(boollan1);
//        boolean boollan2 = iUserInformationSystem.userLogin("Boollan", "xiaowei123++..");
//        System.out.println(boollan2);


    }
}

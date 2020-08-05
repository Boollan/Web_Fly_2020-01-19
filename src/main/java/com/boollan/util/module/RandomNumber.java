package com.boollan.util.module;

import org.springframework.stereotype.Component;

import java.util.Random;


/**
 * @author 其他开源项目
 */
public class RandomNumber {

    //伪随机数生成
    public static String getRandomNumCode(int number) {
        String codeNum = "";
        int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            //目的是产生足够随机的数，避免产生的数字重复率高的问题
            int next = random.nextInt(10000);
            codeNum += numbers[next % 10];
        }
        return codeNum;
    }

    /**
     * 生成随机码值，包含数字、大小写字母
     *
     * @param number 生成的随机码位数
     * @return 返回随机字符串
     * @author 郑明亮
     */
    public static String getRandomCode(int number) {
        String codeNum = "";
        int[] code = new int[3];
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            int num = random.nextInt(10) + 48;
            int uppercase = random.nextInt(26) + 65;
            int lowercase = random.nextInt(26) + 97;
            code[0] = num;
            code[1] = uppercase;
            code[2] = lowercase;
            codeNum += (char) code[random.nextInt(3)];
        }
        return codeNum;
    }

}

package com.boollan.service;

/**
 * @author Boollan
 */
public interface  IEmailMessageSend {

    /**
     * 发送邮箱
     * @param email 收件人邮箱
     * @return 返回是否发送成功
     */
    boolean sendEmail(String email) throws Exception;

    /**
     * 发送邮箱
     * @param email 收件人邮箱
     * @param sendName 发生人名称
     * @return 是否发送成功
     */
    boolean sendEmail(String email, String sendName) throws Exception;

    /**
     * 发送邮箱
     * @param email 收件人邮箱
     * @param sendName 发送人名称
     * @param title 邮件标题
     * @return 是否发送成功
     */
    boolean sendEmail(String email, String sendName, String title) throws Exception;

    /**
     * 发送邮箱
     * @param email 收件人邮箱
     * @param sendName 发送人名称
     * @param title 邮件标题
     * @param body 邮件内容
     * @return 是否发送成功
     */
    boolean sendEmail(String email, String sendName, String title, String body) throws Exception;

}

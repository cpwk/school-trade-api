package cn.jianchen.com.trade.common.mail;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public interface IMailService {

    boolean send(MailHelper.MailInfo mail);
}
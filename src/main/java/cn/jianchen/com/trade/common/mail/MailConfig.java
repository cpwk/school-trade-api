package cn.jianchen.com.trade.common.mail;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Validated
public class MailConfig {
    @NotNull
    private String subject;
    @NotNull
    private String content;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
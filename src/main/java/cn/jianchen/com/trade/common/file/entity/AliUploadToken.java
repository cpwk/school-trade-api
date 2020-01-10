package cn.jianchen.com.trade.common.file.entity;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class AliUploadToken extends UploadToken {
    private String region;
    private String endpoint;
    private String accessKey;
    private String accessSecret;
    private String stsToken;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public void setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
    }

    public String getStsToken() {
        return stsToken;
    }

    public void setStsToken(String stsToken) {
        this.stsToken = stsToken;
    }

}

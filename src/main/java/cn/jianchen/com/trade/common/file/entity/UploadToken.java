package cn.jianchen.com.trade.common.file.entity;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class UploadToken {
    private String vendor;
    private String bucket;
    private String key;
    private String url;

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

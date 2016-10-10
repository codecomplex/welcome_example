package com.suwish.welcome.model.enity;

/**
 * 国家地区编码实体
 *
 * @author min.su on 2016/9/27.
 */
public class CountryRegionEntity extends ResponseEntity {

    private int id;
    private String displayName;
    private String domainName;
    private String phoneCode;

    //

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }
}

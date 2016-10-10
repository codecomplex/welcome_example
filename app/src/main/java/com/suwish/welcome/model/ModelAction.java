package com.suwish.welcome.model;

/**
 * @author by min.su on 2016/10/5.
 */

public final class ModelAction {

    private ModelAction(){}

    public final static String PATH_GET_AUTH_CODE = "sms/sendSms";
    public final static String PATH_LOGIN = "login/mobileAndSms";
    public final static String PATH_REGISTER = "registration/userRegistration";
    public final static String PATH_MOBILE_VALIDATE = "registration/validateRegiMobile";
    public final static String PATH_COUNTRY_REGION = "registration/getCountryRegionList";
}

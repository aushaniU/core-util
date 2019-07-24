package com.wso2telco.core.maskservice;

public interface PropertyMaskable {

    String encryptProperty(String property) throws Exception;
    String decryptProperty(String property) throws Exception;

}

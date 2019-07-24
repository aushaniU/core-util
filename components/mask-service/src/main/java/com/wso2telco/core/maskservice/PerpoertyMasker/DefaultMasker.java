package com.wso2telco.core.maskservice.PerpoertyMasker;

import com.wso2telco.core.maskservice.PropertyMaskable;

public class DefaultMasker implements PropertyMaskable {
    @Override
    public String encryptProperty(String property) throws Exception {
        return property;
    }

    @Override
    public String decryptProperty(String property) {
        return property;
    }
}

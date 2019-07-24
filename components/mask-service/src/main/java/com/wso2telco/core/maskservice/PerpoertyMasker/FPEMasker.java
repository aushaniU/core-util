package com.wso2telco.core.maskservice.PerpoertyMasker;

import com.wso2telco.core.maskservice.PropertyMaskable;

public class FPEMasker implements PropertyMaskable {
    @Override
    public String encryptProperty(String property) throws Exception{
        return "FPEMasker";
    }

    @Override
    public String decryptProperty(String property) {
        return null;
    }
}

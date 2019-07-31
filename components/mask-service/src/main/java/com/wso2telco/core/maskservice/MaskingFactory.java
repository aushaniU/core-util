package com.wso2telco.core.maskservice;

import com.wso2telco.core.maskservice.PerpoertyMasker.BCMasker;
import com.wso2telco.core.maskservice.PerpoertyMasker.CustomMasker;
import com.wso2telco.core.maskservice.PerpoertyMasker.DefaultMasker;
import com.wso2telco.core.maskservice.PerpoertyMasker.FPEMasker;

public class MaskingFactory {

    public PropertyMaskable getMaskable(String maskingAlgorithem){

        PropertyMaskable maskable;

        switch (maskingAlgorithem) {
            case "BCMask":
                maskable = new BCMasker();
                break;
            case "FPEMasker":
                maskable = new FPEMasker();
                break;
            case "Custom":
                maskable = new CustomMasker();
            default:
                maskable = new DefaultMasker();
                break;
        }

        return maskable;
    }
}

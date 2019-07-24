package com.wso2telco.core.maskservice.MaskerService;

import com.wso2telco.core.maskservice.MaskingFactory;
import com.wso2telco.core.maskservice.PropertyMaskable;

public class MaskingHandler {

    public String getEncryptedValue(String property, String maskAlgorithem){

        MaskingFactory maskingFactory = new MaskingFactory();
        PropertyMaskable propertyMaskable = maskingFactory.getMaskable(maskAlgorithem);
        String maskProperty = null;

        try {
            maskProperty =propertyMaskable.encryptProperty(property);

            String deVal = propertyMaskable.decryptProperty(maskProperty);
        } catch (Exception e){
            e.printStackTrace();
        }
        return maskProperty;
    }
}

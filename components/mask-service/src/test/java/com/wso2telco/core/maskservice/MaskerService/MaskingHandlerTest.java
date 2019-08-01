package com.wso2telco.core.maskservice.MaskerService;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MaskingHandlerTest {

    @Test
    public void testGetEncryptedValue() {
        MaskingHandler maskingHandler =new MaskingHandler();
        String enValue = maskingHandler.getEncryptedValue("94773906141","BCMask");
        System.out.println(enValue);
    }

    @Test
    public void testGetEncryptedValueFPEMask() {
        MaskingHandler maskingHandler =new MaskingHandler();
        String enValue = maskingHandler.getEncryptedValue("94773906141","FPEMasker");
        System.out.println(enValue);
    }

}
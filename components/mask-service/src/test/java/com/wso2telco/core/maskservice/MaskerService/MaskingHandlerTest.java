package com.wso2telco.core.maskservice.MaskerService;

import junit.framework.TestCase;

public class MaskingHandlerTest extends TestCase {

    public void testGetEncryptedValue() {
        MaskingHandler maskingHandler =new MaskingHandler();
        String enValue = maskingHandler.getEncryptedValue("94773906141","BCMask");




    }
}
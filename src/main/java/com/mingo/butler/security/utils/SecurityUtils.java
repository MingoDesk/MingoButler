package com.mingo.butler.security.utils;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SignatureException;

public class SecurityUtils {

    private static final String HMAC_SHA512 = "HmacSHA512";

    public static String calculateHexSHA512HMAC(@Value("${config.dataEncrypt.hash}") String dataEncHash,
                                                String payload) throws SignatureException {
        try {
            Mac mac = Mac.getInstance(HMAC_SHA512);
            mac.init(new SecretKeySpec(dataEncHash.getBytes(StandardCharsets.UTF_8), HMAC_SHA512));
            return Hex.encodeHexString(mac.doFinal(payload.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC: " + e.getMessage());
        }
    }
}

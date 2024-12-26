package com.bank.alfalah.encryption.utils;


import com.bank.alfalah.exception.ServiceException;
import com.bank.alfalah.exception.constant.ErrorCodeEnum;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.lang.NonNull;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.List;

@Slf4j
@UtilityClass
public class EncryptionUtil {

    @SneakyThrows
    public static String encrypt(final String value, final String secret, final String salt) {
        final Cipher cipher = getCipher(secret, salt, Cipher.ENCRYPT_MODE);
        return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes(StandardCharsets.UTF_8.name())));
    }
    
    @SneakyThrows
    public static byte[] encrypt(final InputStream value, final String secret, final String salt) {
        final Cipher cipher = getCipher(secret, salt, Cipher.ENCRYPT_MODE);
        return cipher.doFinal(IOUtils.toByteArray(value));
    }

    @SneakyThrows
    public static String decrypt(final String value, final String secret, final String salt) {
        final Cipher cipher = getCipher(secret, salt, Cipher.DECRYPT_MODE);
        return new String(cipher.doFinal(Base64.getDecoder().decode(value)));
    }
    
    @SneakyThrows
    public static byte[] decrypt(final InputStream value, final String secret, final String salt) {
        final Cipher cipher = getCipher(secret, salt, Cipher.DECRYPT_MODE);
        return cipher.doFinal(IOUtils.toByteArray(value));
    }
 

    @SneakyThrows
    private static Cipher getCipher(final String secret, final String salt, final int mode) {
        byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        final IvParameterSpec iVSpec = new IvParameterSpec(iv);
        final SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        final KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
        final SecretKey tmp = factory.generateSecret(spec);
        final SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
        final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(mode, secretKey, iVSpec);
        return cipher;
    }

    public static String getSecurityKeyFromHeader(@NonNull final HttpInputMessage message) {
        final List<String> securityHeader = message.getHeaders().get("security-key");
        if (CollectionUtils.isNotEmpty(securityHeader)) {
            return securityHeader.stream().findAny().orElse("");
        }
        throw new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "Security-Key header is not valid");
    }

    public static String getSecurityKeyFromHeader(@NonNull final ServerHttpRequest request) {
        final List<String> securityHeader = request.getHeaders().get("security-key");
        if (CollectionUtils.isNotEmpty(securityHeader)) {
            return securityHeader.stream().findAny().orElse("");
        }
        throw new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "Security-Key header is not valid");
    }
    
}

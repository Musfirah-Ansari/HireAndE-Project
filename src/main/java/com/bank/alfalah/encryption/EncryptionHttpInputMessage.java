package com.bank.alfalah.encryption;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.InputStream;


@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EncryptionHttpInputMessage implements HttpInputMessage {

    InputStream body;

    HttpHeaders httpHeaders;

    @Override
    public InputStream getBody() {
        return this.body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return this.httpHeaders;
    }
}

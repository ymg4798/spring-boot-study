package com.springbootstudy.message;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.DefaultMessageCodesResolver;

@Slf4j
@SpringBootTest
public class MessageCodesResolverTest {

    //객체 오류 조회
    @Test
    public void messageCodesResolverObject() {
        DefaultMessageCodesResolver codesResolver = new DefaultMessageCodesResolver();
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "name");
        for (String msg : messageCodes) {
            log.info(msg);
        }
    }

    @Test
    public void messageCodesResolverField() {
        DefaultMessageCodesResolver codesResolver = new DefaultMessageCodesResolver();
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "valids", "value", String.class);
        for (String msg : messageCodes) {
            log.info(msg);
        }
    }
}

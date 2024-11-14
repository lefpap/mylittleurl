package com.lefpap.mylittleurl_api.lib;

import com.lefpap.mylittleurl_api.config.properties.UniqueCodeGeneratorConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class UniqueCodeGenerator {

    private static final Random RANDOM = new Random();
    private final UniqueCodeGeneratorConfigProperties properties;

    public String generateUniqueCode() {
        StringBuilder keyBuilder = new StringBuilder(properties.codeLength());
        for (int i = 0; i < properties.codeLength(); i++) {
            int randomIndex = RANDOM.nextInt(properties.allowedCharacters().length());
            keyBuilder.append(properties.allowedCharacters().charAt(randomIndex));
        }

        return keyBuilder.toString();
    }
}

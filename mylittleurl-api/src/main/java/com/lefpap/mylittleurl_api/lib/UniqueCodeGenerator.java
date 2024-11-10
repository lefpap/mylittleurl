package com.lefpap.mylittleurl_api.lib;

import com.lefpap.mylittleurl_api.config.CodeGeneratorConfig;
import com.lefpap.mylittleurl_api.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class UniqueCodeGenerator {

    private final CodeGeneratorConfig config;
    private final LinkRepository urlRepository;
    private static final Random RANDOM = new Random();

    public String generateUniqueCode() {
        int maxAttempts = config.maxAttempts();

        log.info("Starting unique code generation with max attempts: {}", maxAttempts);
        for (int attempts = 0; attempts < maxAttempts; attempts++) {
            String code = generateRandomCode();
            log.debug("Generated code: {} (Attempt {}/{})", code, attempts, maxAttempts);

            if (Boolean.FALSE.equals(urlRepository.existsByCode(code))) {
                log.info("Successfully generated unique code: {} after {} attempt(s)", code, attempts);
                return code;
            }

            log.debug("Collision detected for code: {} (Attempt {}/{})", code, attempts, maxAttempts);
        }

        throw new IllegalStateException("Failed to generate a unique code after " + maxAttempts + " attempts");
    }

    private String generateRandomCode() {
        StringBuilder keyBuilder = new StringBuilder(config.codeLength());
        for (int i = 0; i < config.codeLength(); i++) {
            int randomIndex = RANDOM.nextInt(config.allowedCharacters().length());
            keyBuilder.append(config.allowedCharacters().charAt(randomIndex));
        }
        return keyBuilder.toString();
    }
}

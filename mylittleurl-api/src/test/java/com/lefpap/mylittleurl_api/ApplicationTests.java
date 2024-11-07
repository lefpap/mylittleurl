package com.lefpap.mylittleurl_api;

import com.lefpap.mylittleurl_api.data.model.Link;
import com.lefpap.mylittleurl_api.repository.LinkRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class ApplicationTests {

	@Autowired
	private LinkRepository urls;

	@Test
	void contextLoads() {
	}

    @Test
    void test_LittleUrl_Repository() {

        Link link = Link.builder()
            .code("xdxd")
            .url("https://www.facebook.com")
            .build();

        assertNull(link.id());
        Link savedLink = urls.save(link);
        assertNotNull(savedLink.id());
    }

}

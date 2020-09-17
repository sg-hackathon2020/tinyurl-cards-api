package com.rakole.tinyurl.integrationtests;

import com.rakole.tinyurl.api.EncoderService;
import com.rakole.tinyurl.api.TUserService;
import com.rakole.tinyurl.model.Url;
import com.rakole.tinyurl.repository.UrlRepository;
import com.rakole.tinyurl.service.UrlServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UrlServiceTestIT {

    Url url;
    @Autowired
    private UrlServiceImpl urlService;

    @MockBean
    private EncoderService encoderService;
    @MockBean
    private UrlRepository urlRepository;
    @MockBean
    private TUserService tUserService;

    @BeforeEach
    public void setUp() {
        urlService.setHostUrl("http://localhost:8080");
        url = Url.builder().hash("xasdas")
                .isActive(true)
                .prefix("ccr").build();
        //given()


    }

    @Test
    @DisplayName("When host url hash is present and prefix is present")
    public void whenHostUrlIsPresentAndPrefixIsPresent() {
        String shortUrl = urlService.prepareTinyUrl(url);

        //then
        assertNotNull(shortUrl);
        assertEquals("http://localhost:8080/ccr/xasdas", shortUrl);
    }

    @TestConfiguration
    static class UrlServiceImplTestContextConfiguration {
        @Bean
        public UrlServiceImpl urlService() {
            return new UrlServiceImpl();
        }
    }
}

package com.rakole.tinyurl.service;

import com.rakole.tinyurl.exception.UrlNotFoundException;
import com.rakole.tinyurl.model.Url;
import com.rakole.tinyurl.repository.UrlRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class UrlServiceImplTest {
    @Mock
    private UrlRepository urlRepository;

    @InjectMocks
    private UrlServiceImpl urlService;

    @Test
    @DisplayName("When find by id is called, and the Url object is present in DB")
    void findById() {
        //given
        Url url = Url.builder().id(1).isActive(true).build();
        given(urlRepository.findById(1)).willReturn(Optional.of(url));

        //when
        Url foundUrl = urlService.findById(1);

        //then
        assertNotNull(foundUrl);
        then(urlRepository).should(times(1)).findById(anyInt());

    }

    @Test
    @DisplayName("When findById is called and Url object is not present in DB")
    void testFindByIdThrows() {
        //given
        given(urlRepository.findById(999)).willReturn(Optional.empty());

        //when
        assertThrows(UrlNotFoundException.class, () -> urlService.findById(999));

        //then
        then(urlRepository).should().findById(999);
    }

    @Test
    void save() {
        //given
        Url url = Url.builder().url("www.google.com").build();
        given(urlRepository.save(url)).willReturn(url);

        //when
        Url urlSaved = urlService.save(url);
        assertNotNull(urlSaved);

        //then
        then(urlRepository).should(times(1)).save(url);

    }

    @Test
    @DisplayName("When find by shortUrl is called, and the Url object is present in DB")
    void findByShortUrl() {
        //given
        Url url = Url.builder().id(1).isActive(true).build();
        given(urlRepository.findByHash("tiny.url/3244a")).willReturn(Optional.of(url));

        //when
        Url foundUrl = urlService.findByHash("tiny.url/3244a");

        //then
        assertNotNull(foundUrl);
        then(urlRepository).should(times(1)).findByHash(anyString());
    }

    @Test
    @DisplayName("When findByShortUrl is called and Url object is not present in DB")
    void testFindShortUrlThrows() {
        //given
        given(urlRepository.findByHash("tiny.url/3244a")).willReturn(Optional.empty());

        //when
        assertThrows(UrlNotFoundException.class, () -> urlService.findByHash("tiny.url/3244a"));

        //then
        then(urlRepository).should().findByHash("tiny.url/3244a");
    }

    @Test
    @DisplayName("method should throw exception if url is not active")
    void getUrl() {
        Url url = Url.builder().isActive(false).build();
        assertThrows(UrlNotFoundException.class, () -> urlService.getUrl(url));
    }
}
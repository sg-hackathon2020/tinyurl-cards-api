package com.rakole.tinyurl.service;

import com.rakole.tinyurl.api.EncoderService;
import com.rakole.tinyurl.api.TUserService;
import com.rakole.tinyurl.api.UrlService;
import com.rakole.tinyurl.enums.EncoderType;
import com.rakole.tinyurl.enums.MessageDigestType;
import com.rakole.tinyurl.exception.UrlNotFoundException;
import com.rakole.tinyurl.model.TUser;
import com.rakole.tinyurl.model.Url;
import com.rakole.tinyurl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Random;

@Service
public class UrlServiceImpl implements UrlService {
    @Autowired
    private UrlRepository urlRepository;
    @Autowired
    private EncoderService encoderService;
    @Autowired
    private TUserService tUserService;


    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    @Value("${custom-host-url}")
    private String hostUrl;

    //check if the url actually works by receiving http status 200
    public static boolean checkUrlWorks(String url) throws IOException {
        URL checkUrl = new URL(url);
        HttpURLConnection huc = (HttpURLConnection) checkUrl.openConnection();
        int responseCode = huc.getResponseCode();
        return responseCode == 200;
    }

    @Override
    public Url save(Url url) {
        return urlRepository.save(url);
    }

    @Override
    public Url findById(int id) {
        Url url = urlRepository.findById(id).orElseThrow(UrlNotFoundException::new);
        return isUrlActive(url);
    }

    @Override
    @Cacheable(cacheNames = "short_url_cache", key = "{#shortUrl}")
    public Url findByHash(String shortUrl) throws UrlNotFoundException {
        Url url = urlRepository.findByHash(shortUrl).orElseThrow(UrlNotFoundException::new);
        return isUrlActive(url);
    }

    protected Url isUrlActive(Url url) throws UrlNotFoundException {
        if (!url.isActive())

            throw new UrlNotFoundException();
        return url;
    }

    public String refactorUrl(String tobeShortedUrl) throws NoSuchAlgorithmException {
        Random random = new Random();
        try {
            checkUrlWorks(tobeShortedUrl);
        } catch (IOException ml) {
            throw new UrlNotFoundException();
        }
        boolean isUnique = false;
        String intermediateUrl = null;
        int counter = 0;
        while (!isUnique) {
            counter++;
            /**If even after 5 attempts same hash exists in DB, then add a random number to the end of the string and start all over again**/
            if (counter == 5) {
                counter = 0;
                tobeShortedUrl = tobeShortedUrl.concat(Integer.toString(random.nextInt()));
            }
            intermediateUrl = encoderService.shortenUrl(EncoderType.ENCODER, MessageDigestType.MD5, tobeShortedUrl);
            if (!urlRepository.existsByHash(intermediateUrl))
                isUnique = true;
        }
        return intermediateUrl;
    }

    public Url createStandAloneUrl(int userId, String url) throws NoSuchAlgorithmException {
        String hash = refactorUrl(url);
        TUser tUser = tUserService.getMyCurrentUser();
        Url url1 = Url.builder().date(LocalDate.now().plusWeeks(1))
                .isActive(true).hash(hash).url(url).user(tUser).build();
        return save(url1);
    }


    //this will return the actual tiny url along with host etc
    public String prepareTinyUrl(Url url) {
        StringBuilder sb = new StringBuilder();
        sb.append(hostUrl).append("/");
        if (null != url.getPrefix())
            sb.append(url.getPrefix()).append("/");
        return sb.append(url.getHash()).toString();
    }


}

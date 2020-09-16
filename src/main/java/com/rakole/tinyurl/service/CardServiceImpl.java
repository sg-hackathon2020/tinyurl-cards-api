package com.rakole.tinyurl.service;

import com.rakole.tinyurl.api.EncoderService;
import com.rakole.tinyurl.enums.EncoderType;
import com.rakole.tinyurl.enums.MessageDigestType;
import com.rakole.tinyurl.exception.CardNotFoundException;
import com.rakole.tinyurl.exception.GroupNotFoundException;
import com.rakole.tinyurl.exception.UrlNotFoundException;
import com.rakole.tinyurl.model.Card;
import com.rakole.tinyurl.model.Group;
import com.rakole.tinyurl.model.Url;
import com.rakole.tinyurl.model.dto.CardCrudDto;
import com.rakole.tinyurl.model.dto.CardDto;
import com.rakole.tinyurl.repository.CardRepository;
import com.rakole.tinyurl.repository.GroupRepository;
import com.rakole.tinyurl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class CardServiceImpl {

    @Value("${custom-host-url}")
    private String hostUrl;

    private final CardRepository cardRepository;
    private final GroupRepository groupRepository;
    private final UrlRepository urlRepository;
    private final EncoderService encoderService;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, GroupRepository groupRepository, UrlRepository urlRepository, EncoderService encoderService) {
        this.cardRepository = cardRepository;
        this.groupRepository = groupRepository;
        this.urlRepository = urlRepository;
        this.encoderService = encoderService;
    }

    public CardDto getCardDtoById(int cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
        return CardDto.builder().cardId(card.getId())
                .description(card.getDescription())
                .fullUrl(card.getUrl().getUrl())
                .groupId(Optional.of(card.getGroup().getId()).orElse(0))
                .title(card.getTitle()).description(card.getDescription())
                .tinyUrl(prepareTinyUrl(card.getUrl())).fullUrl(card.getUrl().getUrl())
                .groupName(card.getGroup().getGroupName()).build();
    }

    private String prepareTinyUrl(Url url) {
        StringBuilder sb = new StringBuilder();
        return sb.append(hostUrl).append("/")
                .append(Optional.of(url.getPrefix()).orElse(""))
                .append("/").append(url.getHash()).toString();
    }

    public void save(CardCrudDto cardCrudDto) throws NoSuchAlgorithmException {
        if (cardCrudDto.getGroupId() == null)
            throw new GroupNotFoundException();

        //first check if the url is correct
        try {
            checkUrlWorks(cardCrudDto.getUrl());
        } catch (IOException ml) {
            throw new UrlNotFoundException();
        }

        boolean isUnique = false;
        String intermediateUrl = null;
        while (!isUnique) {
            intermediateUrl = encoderService.shortenUrl(EncoderType.ENCODER, MessageDigestType.MD5, cardCrudDto.getUrl());
            if (!urlRepository.existsByHash(intermediateUrl))
                isUnique = true;
        }

        Group group = groupRepository.findById(cardCrudDto.getGroupId()).orElseThrow(GroupNotFoundException::new);
        Url url = Url.builder().hash(intermediateUrl).url(cardCrudDto.getUrl()).isActive(true)
                .date(LocalDate.now().withYear(2200)).prefix(cardCrudDto.getPrefix()).build();
        url = urlRepository.save(url);

        Card card = Card.builder().url(url).group(group).title(cardCrudDto.getTitle())
                .description(cardCrudDto.getDescription()).build();
        cardRepository.save(card);
    }

    public static boolean checkUrlWorks(String url) throws IOException {
        URL checkUrl = new URL(url);
        HttpURLConnection huc = (HttpURLConnection) checkUrl.openConnection();
        int responseCode = huc.getResponseCode();
        return responseCode == 200;
    }

    public void save(Card card) {
        cardRepository.save(card);
    }
}

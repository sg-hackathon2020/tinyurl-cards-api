package com.rakole.tinyurl.service;

import com.rakole.tinyurl.exception.CardNotFoundException;
import com.rakole.tinyurl.exception.GroupNotFoundException;
import com.rakole.tinyurl.model.Card;
import com.rakole.tinyurl.model.Group;
import com.rakole.tinyurl.model.Url;
import com.rakole.tinyurl.model.dto.CardCrudDto;
import com.rakole.tinyurl.model.dto.CardDto;
import com.rakole.tinyurl.repository.CardRepository;
import com.rakole.tinyurl.repository.GroupRepository;
import com.rakole.tinyurl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class CardServiceImpl {


    private final CardRepository cardRepository;
    private final GroupRepository groupRepository;
    private final UrlRepository urlRepository;
    private final UrlServiceImpl urlService;


    @Autowired
    public CardServiceImpl(CardRepository cardRepository, GroupRepository groupRepository, UrlRepository urlRepository, UrlServiceImpl urlService) {
        this.cardRepository = cardRepository;
        this.groupRepository = groupRepository;
        this.urlRepository = urlRepository;
        this.urlService = urlService;
    }

    public CardDto getCardDtoById(int cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
        return CardDto.builder().cardId(card.getId())
                .description(card.getDescription())
                .fullUrl(card.getUrl().getUrl())
                .groupId(Optional.of(card.getGroup().getId()).orElse(0))
                .title(card.getTitle()).description(card.getDescription())
                .tinyUrl(urlService.prepareTinyUrl(card.getUrl())).fullUrl(card.getUrl().getUrl())
                .groupName(card.getGroup().getGroupName()).build();
    }


    public void save(CardCrudDto cardCrudDto) throws NoSuchAlgorithmException {
        if (cardCrudDto.getGroupId() == null)
            throw new GroupNotFoundException();


        //first check if the url is correct
        String intermediateUrl = urlService.refactorUrl(cardCrudDto.getUrl());

        Group group = groupRepository.findById(cardCrudDto.getGroupId()).orElseThrow(GroupNotFoundException::new);
        Url url = Url.builder().hash(intermediateUrl).url(cardCrudDto.getUrl()).isActive(true)
                .date(LocalDate.now().withYear(2200)).prefix(cardCrudDto.getPrefix()).build();
        url = urlRepository.save(url);

        Card card = Card.builder().url(url).group(group).title(cardCrudDto.getTitle())
                .description(cardCrudDto.getDescription()).build();
        cardRepository.save(card);
    }


    public void save(Card card) {
        cardRepository.save(card);
    }
}

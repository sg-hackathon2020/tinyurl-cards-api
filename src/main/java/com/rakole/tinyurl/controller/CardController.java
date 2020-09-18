package com.rakole.tinyurl.controller;

import com.google.common.collect.ImmutableSet;
import com.rakole.tinyurl.api.UrlService;
import com.rakole.tinyurl.model.Card;
import com.rakole.tinyurl.model.Group;
import com.rakole.tinyurl.model.dto.CardCrudDto;
import com.rakole.tinyurl.model.dto.CardDto;
import com.rakole.tinyurl.model.dto.CardResponseEntityDto;
import com.rakole.tinyurl.model.dto.CardUpDateRequestDto;
import com.rakole.tinyurl.repository.CardRepository;
import com.rakole.tinyurl.repository.GroupRepository;
import com.rakole.tinyurl.service.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CardController {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UrlService urlService;

    private final CardServiceImpl cardService;

    @Autowired
    public CardController(CardServiceImpl cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/api/v1/cards")
    @CrossOrigin
    /*@PreAuthorize("isAuthenticated()")*/
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @GetMapping("/api/v1/cards/{id}")
    @CrossOrigin
    /*@PreAuthorize("isAuthenticated()")*/
    public Card getCard(@PathVariable int id) {
        return cardRepository.findById(id).get();
    }

    @PostMapping("/api/v1/group")
    @CrossOrigin
    /*@PreAuthorize("isAuthenticated()")*/
    public Group createGroup(@RequestBody Group group) {
        return groupRepository.save(group);
    }

    @GetMapping("/api/v1/group")
    @CrossOrigin
    /*@PreAuthorize("isAuthenticated()")*/
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @GetMapping("/api/v1/testMany")
    @CrossOrigin
    /*@PreAuthorize("isAuthenticated()")*/
    public String test() {
        Group group = groupRepository
                .save(Group.builder().clusterName("test").build());
       /* cardRepository.save(Card.builder().group(group).shortUrl("xxx").build());
        cardRepository.save(Card.builder().group(group).shortUrl("yyy").build());*/
        return null;
    }

    @GetMapping("/api/v1/testPull")
    @CrossOrigin
    /*@PreAuthorize("isAuthenticated()")*/
    public String test2() {
        Group group = groupRepository.findById(1).get();
        System.out.println(group);
        List<Card> cards = group.getCards();
        cards.forEach(System.out::println);
        List<Card> cards2 = cardRepository.findAllById(ImmutableSet.of(1, 2));
        System.out.println("cards2");
        cards2.forEach(System.out::println);
        return null;
    }


    /**
     * final
     **/
    @PostMapping("/api/v1/groups/{groupId}/cards")
    @CrossOrigin
    /*@PreAuthorize("isAuthenticated()")*/
    public ResponseEntity<Void> saveCard(@PathVariable int groupId, @RequestBody CardCrudDto card) throws NoSuchAlgorithmException {
        cardService.save(card);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/v1/groups/cards/{cardId}")
    @CrossOrigin
    /*@PreAuthorize("isAuthenticated()")*/
    public CardDto successGet(@PathVariable int cardId) {
        return cardService.getCardDtoById(cardId);
    }


    @GetMapping("/api/v1/groups/{groupId}/cards")
    @CrossOrigin
    /*@PreAuthorize("isAuthenticated()")*/
    public ResponseEntity<List<CardResponseEntityDto>> getCardsForAGroup(@PathVariable int groupId) {
        return new ResponseEntity<>(cardRepository.
                findAllByGroup(groupRepository.
                        findById(groupId).get()).stream()
                .map(card -> CardResponseEntityDto.builder()
                        .title(card.getTitle()).description(card.getDescription())
                        .longUrl(card.getUrl().getUrl()).id(card.getId())
                        .shortUrl(urlService.prepareTinyUrl(card.getUrl())).build()).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/api/v1/groups/{groupId}/cards")
    public ResponseEntity<Void> updateCard(@PathVariable int groupId, @RequestBody CardUpDateRequestDto card) {
        Group group = groupRepository.findById(groupId).get();
        System.out.println(card);
        Card oldCard = cardRepository.findById(card.getCardId()).get();
        if (card.getTitle() != null) {
            oldCard.setTitle(card.getTitle());
        }
        if (card.getDescription() != null) {
            oldCard.setDescription(card.getDescription());
        }
        cardService.save(oldCard);
        return new ResponseEntity(HttpStatus.OK);
    }

}


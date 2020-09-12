package com.rakole.tinyurl.controller;

import com.google.common.collect.ImmutableSet;
import com.rakole.tinyurl.model.Card;
import com.rakole.tinyurl.model.Group;
import com.rakole.tinyurl.repository.CardRepository;
import com.rakole.tinyurl.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class CardController {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    GroupRepository groupRepository;

    @GetMapping("/api/v1/cards")
    @CrossOrigin
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @GetMapping("/api/v1/cards/{id}")
    @CrossOrigin
    public Card getCard(@PathVariable int id) {
        return cardRepository.findById(id).get();
    }

    @PostMapping("/api/v1/group")
    @CrossOrigin
    public Group createGroup(@RequestBody Group group) {
        return groupRepository.save(group);
    }

    @GetMapping("/api/v1/group")
    @CrossOrigin
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @GetMapping("/api/v1/testMany")
    @CrossOrigin
    public String test() {
        Group group = groupRepository
                .save(Group.builder().clusterName("test").build());
        cardRepository.save(Card.builder().group(group).shortUrl("xxx").build());
        cardRepository.save(Card.builder().group(group).shortUrl("yyy").build());
        return null;
    }

    @GetMapping("/api/v1/testPull")
    @CrossOrigin
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

    @PostMapping("/api/v1/groups/{groupId}/cards")
    @CrossOrigin
    public String success(@PathVariable int groupId, @RequestBody Card card) {
        System.out.println("Group id received:" + groupId);
        System.out.println("card received:" + card);
        return "success";
    }
}


//    let resourceUrl = `http://localhost:8080/api/v1/groups/${groupId}/cards`;
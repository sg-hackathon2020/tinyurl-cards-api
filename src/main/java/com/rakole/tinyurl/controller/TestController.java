package com.rakole.tinyurl.controller;

import com.rakole.tinyurl.model.Card;
import com.rakole.tinyurl.model.CustomEncoderProperties;
import com.rakole.tinyurl.model.Group;
import com.rakole.tinyurl.model.Url;
import com.rakole.tinyurl.repository.CardRepository;
import com.rakole.tinyurl.repository.GroupRepository;
import com.rakole.tinyurl.repository.UrlRepository;
import com.rakole.tinyurl.service.CardServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1")
public class TestController {
    private final UrlRepository urlRepository;
    private final CardRepository cardRepository;
    private final GroupRepository groupRepository;
    private final CardServiceImpl cardService;
    private final CustomEncoderProperties customEncoderProperties;

    public TestController(UrlRepository urlRepository, CardRepository cardRepository, GroupRepository groupRepository, CardServiceImpl cardService, CustomEncoderProperties customEncoderProperties) {
        this.urlRepository = urlRepository;
        this.cardRepository = cardRepository;
        this.groupRepository = groupRepository;
        this.cardService = cardService;
        this.customEncoderProperties = customEncoderProperties;
    }

    @GetMapping("testCardUrl")
    public void test() {
        Group group = groupRepository.findById(1).get();
        Url url = Url.builder().isActive(true).hash("xxere2").prefix("ccr").url("http://www.google.com").date(LocalDate.now()).build();
        Card card = Card.builder().description("Test Card 1 description").title("Test Card 1").group(group).url(url).build();
        urlRepository.save(url);
        cardRepository.save(card);


    }


}

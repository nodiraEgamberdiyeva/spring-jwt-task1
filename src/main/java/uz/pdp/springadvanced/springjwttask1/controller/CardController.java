package uz.pdp.springadvanced.springjwttask1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springadvanced.springjwttask1.entity.Card;
import uz.pdp.springadvanced.springjwttask1.entity.User;
import uz.pdp.springadvanced.springjwttask1.payload.CardDto;
import uz.pdp.springadvanced.springjwttask1.payload.UserDto;
import uz.pdp.springadvanced.springjwttask1.service.CardService;
import uz.pdp.springadvanced.springjwttask1.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping
    public HttpEntity<?> getCards(@RequestParam int page){
        return ResponseEntity.ok(cardService.getCards(page));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getCard(@PathVariable Long id){
        Card card = cardService.getCard(id);
        return ResponseEntity.status(card!=null?200:409).body(card);
    }

    @PostMapping
    public HttpEntity<?> addCard(@RequestBody @Valid CardDto cardDto){
        if (cardService.addCard(cardDto).isStatus()){
            return ResponseEntity.ok(cardService.addCard(cardDto));
        }
        return ResponseEntity.status(409).body(cardService.addCard(cardDto));
    }
}

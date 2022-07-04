package uz.pdp.springadvanced.springjwttask1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.springadvanced.springjwttask1.entity.Card;
import uz.pdp.springadvanced.springjwttask1.entity.User;
import uz.pdp.springadvanced.springjwttask1.payload.CardDto;
import uz.pdp.springadvanced.springjwttask1.payload.ResponseApi;
import uz.pdp.springadvanced.springjwttask1.payload.UserDto;
import uz.pdp.springadvanced.springjwttask1.repository.CardRepository;
import uz.pdp.springadvanced.springjwttask1.repository.UserRepository;

import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private UserRepository userRepository;

    public Page<Card> getCards(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return cardRepository.findAll(pageable);
    }

    public Card getCard(Long id){
        Optional<Card> byId = cardRepository.findById(id);
        return byId.orElse(null);
    }

    public ResponseApi addCard(CardDto cardDto){
        if (cardRepository.existsByNumber(cardDto.getNumber())){
            return new ResponseApi("exist", false);
        }
        Optional<User> byId = userRepository.findById(cardDto.getUserId());
        if (!byId.isPresent()){
            return new ResponseApi("user not exist", false);
        }

        Card card = new Card();
       card.setActive(cardDto.isActive());
       card.setBalance(cardDto.getBalance());
       card.setExpireDate(cardDto.getExpireDate());
       card.setNumber(card.getNumber());
       card.setUser(byId.get());
        cardRepository.save(card);
        return new ResponseApi("added", true);
    }
}

package uz.pdp.springadvanced.springjwttask1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.springadvanced.springjwttask1.entity.Card;
import uz.pdp.springadvanced.springjwttask1.entity.Outcome;
import uz.pdp.springadvanced.springjwttask1.entity.User;
import uz.pdp.springadvanced.springjwttask1.payload.CardDto;
import uz.pdp.springadvanced.springjwttask1.payload.OutcomeDto;
import uz.pdp.springadvanced.springjwttask1.payload.ResponseApi;
import uz.pdp.springadvanced.springjwttask1.repository.CardRepository;
import uz.pdp.springadvanced.springjwttask1.repository.OutcomeRepository;
import uz.pdp.springadvanced.springjwttask1.repository.UserRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class OutcomeService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private OutcomeRepository outcomeRepository;

    public Page<Outcome> getOutcome(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return outcomeRepository.findAll(pageable);
    }

    public Outcome getOutcome(Long id){
        Optional<Outcome> byId = outcomeRepository.findById(id);
        return byId.orElse(null);
    }

    public ResponseApi addOutcome(OutcomeDto outcomeDto){
        if (outcomeDto.getFromCardId().equals(outcomeDto.getToCardId())){
            return new ResponseApi("error", false);
        }

        Optional<Card> fromCarId = cardRepository.findById(outcomeDto.getFromCardId());
        Optional<Card> toCarId = cardRepository.findById(outcomeDto.getToCardId());

        if (!fromCarId.isPresent()){
            return new ResponseApi("card not exist", false);
        }
        if (!toCarId.isPresent()){
            return new ResponseApi("card not exist", false);
        }

        Outcome outcome = new Outcome();
        outcome.setAmount(outcomeDto.getAmount());
        outcome.setDate(new Date());
        outcome.setCommissionAmount(outcomeDto.getCommissionAmount());
        outcome.setFromCard(fromCarId.get());
        outcome.setToCard(toCarId.get());

        outcomeRepository.save(outcome);
        return new ResponseApi("added", true);
    }
}

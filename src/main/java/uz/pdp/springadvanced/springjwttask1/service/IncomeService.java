package uz.pdp.springadvanced.springjwttask1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.springadvanced.springjwttask1.entity.Card;
import uz.pdp.springadvanced.springjwttask1.entity.Income;
import uz.pdp.springadvanced.springjwttask1.entity.Outcome;
import uz.pdp.springadvanced.springjwttask1.payload.IncomeDto;
import uz.pdp.springadvanced.springjwttask1.payload.OutcomeDto;
import uz.pdp.springadvanced.springjwttask1.payload.ResponseApi;
import uz.pdp.springadvanced.springjwttask1.repository.CardRepository;
import uz.pdp.springadvanced.springjwttask1.repository.IncomeRepository;
import uz.pdp.springadvanced.springjwttask1.repository.OutcomeRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class IncomeService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private IncomeRepository incomeRepository;

    public Page<Income> getIncome(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return incomeRepository.findAll(pageable);
    }

    public Income getIncome(Long id){
        Optional<Income> byId = incomeRepository.findById(id);
        return byId.orElse(null);
    }

    public ResponseApi addIncome(IncomeDto incomeDto){
        if (incomeDto.getFromCardId().equals(incomeDto.getToCardId())){
            return new ResponseApi("error", false);
        }

        Optional<Card> fromCarId = cardRepository.findById(incomeDto.getFromCardId());
        Optional<Card> toCarId = cardRepository.findById(incomeDto.getToCardId());

        if (!fromCarId.isPresent()){
            return new ResponseApi("card not exist", false);
        }
        if (!toCarId.isPresent()){
            return new ResponseApi("card not exist", false);
        }

        Income income = new Income();
        income.setAmount(incomeDto.getAmount());
        income.setDate(new Date());
        income.setFromCard(fromCarId.get());
        income.setToCard(toCarId.get());

        incomeRepository.save(income);
        return new ResponseApi("added", true);
    }
}

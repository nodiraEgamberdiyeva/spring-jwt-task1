package uz.pdp.springadvanced.springjwttask1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springadvanced.springjwttask1.entity.Income;
import uz.pdp.springadvanced.springjwttask1.entity.Outcome;
import uz.pdp.springadvanced.springjwttask1.payload.IncomeDto;
import uz.pdp.springadvanced.springjwttask1.payload.OutcomeDto;
import uz.pdp.springadvanced.springjwttask1.service.IncomeService;
import uz.pdp.springadvanced.springjwttask1.service.OutcomeService;

import javax.validation.Valid;

@RestController
@RequestMapping("/outcome")
public class OutcomeController {
    @Autowired
    private OutcomeService outcomeService;

    @GetMapping
    public HttpEntity<?> getOutcomes(@RequestParam int page){
        return ResponseEntity.ok(outcomeService.getOutcome(page));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOutcome(@PathVariable Long id){
        Outcome income = outcomeService.getOutcome(id);
        return ResponseEntity.status(income!=null?200:409).body(income);
    }

    @PostMapping
    public HttpEntity<?> addOutcome(@RequestBody @Valid OutcomeDto cardDto){
        if (outcomeService.addOutcome(cardDto).isStatus()){
            return ResponseEntity.ok(outcomeService.addOutcome(cardDto));
        }
        return ResponseEntity.status(409).body(outcomeService.addOutcome(cardDto));
    }
}

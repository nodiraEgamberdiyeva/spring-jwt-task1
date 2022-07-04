package uz.pdp.springadvanced.springjwttask1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springadvanced.springjwttask1.entity.Income;
import uz.pdp.springadvanced.springjwttask1.payload.IncomeDto;
import uz.pdp.springadvanced.springjwttask1.service.IncomeService;

import javax.validation.Valid;

@RestController
@RequestMapping("/income")
public class IncomeController {
    @Autowired
    private IncomeService incomeService;

    @GetMapping
    public HttpEntity<?> getInputs(@RequestParam int page){
        return ResponseEntity.ok(incomeService.getIncome(page));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getInput(@PathVariable Long id){
        Income income = incomeService.getIncome(id);
        return ResponseEntity.status(income!=null?200:409).body(income);
    }

    @PostMapping
    public HttpEntity<?> addInput(@RequestBody @Valid IncomeDto cardDto){
        if (incomeService.addIncome(cardDto).isStatus()){
            return ResponseEntity.ok(incomeService.addIncome(cardDto));
        }
        return ResponseEntity.status(409).body(incomeService.addIncome(cardDto));
    }
}

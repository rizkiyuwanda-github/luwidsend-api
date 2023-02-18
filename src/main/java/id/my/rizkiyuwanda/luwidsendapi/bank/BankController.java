package id.my.rizkiyuwanda.luwidsendapi.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping
    public Bank create(@RequestBody Bank bank){
        return bankService.save(bank);
    }

    @GetMapping
    public Iterable<Bank>findAll(){
        return bankService.findAll();
    }
}

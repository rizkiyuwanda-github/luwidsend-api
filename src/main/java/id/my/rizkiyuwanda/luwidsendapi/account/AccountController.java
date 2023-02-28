package id.my.rizkiyuwanda.luwidsendapi.account;

import id.my.rizkiyuwanda.luwidsendapi.utility.ResponseDTO;
import id.my.rizkiyuwanda.luwidsendapi.bank.Bank;
import id.my.rizkiyuwanda.luwidsendapi.utility.StringUtility;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO<Account>> save(@Valid @RequestBody AccountDTO accountDTO, Errors errors){
        List<String> messages = new ArrayList<>();
        if (errors.hasErrors()) {
            for (ObjectError objectError : errors.getAllErrors()) {
                messages.add(objectError.getDefaultMessage());
            }
            ResponseDTO<Account> responseDTO = new ResponseDTO<>(false, messages, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }

        Account account = new Account();
        account.setId(accountDTO.getId());
        Bank bank= new Bank();
        bank.setId(accountDTO.getBankId());
        account.setBank(bank);
        account.setName(accountDTO.getName());
        account.setBalance(accountDTO.getBalance());

        Account entity = accountService.save(account);
        if(entity == null){
            messages.add(StringUtility.FAILED);
            ResponseDTO<Account> responseDTO = new ResponseDTO<>(false, messages, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }else{
            messages.add(StringUtility.SUCCESS);
            ResponseDTO<Account> responseDTO = new ResponseDTO<>(true, messages, entity);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }
    }

    @DeleteMapping("/deletebyid/{id}")
    public void deleteById(@PathVariable("id") String id) {
        accountService.deleteById(id);
    }

    @GetMapping("/findbyid/{id}")
    public Optional<Account> findById(@PathVariable("id") String id) {
        Optional<Account> account = accountService.findById(id);
        if (account.isPresent() == false) {
            return null;
        } else {
            return account;
        }
    }

    @GetMapping("/findbyidandbankid/{id}/{bankid}")
    public Optional<Account> findById(@PathVariable("id") String id, @PathVariable("bankid") String bankId) {
        Optional<Account> account = accountService.findByIdAndBankId(id, bankId);
        if (account.isPresent() == false) {
            return null;
        } else {
            return account;
        }
    }

    @GetMapping("/findall")
    public Iterable<Account> findAll() {
        return accountService.findAll();
    }

}

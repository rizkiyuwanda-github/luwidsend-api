package id.my.rizkiyuwanda.luwidsendapi.account;

import id.my.rizkiyuwanda.luwidsendapi.ResponseDTO;
import id.my.rizkiyuwanda.luwidsendapi.bank.Bank;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<Account>> register(@RequestBody AccountDTO accountDTO){
        ResponseDTO<Account>response = new ResponseDTO<>();

        Account account = new Account();
        account.setId(accountDTO.getId());
        Bank bank= new Bank();
        bank.setId(accountDTO.getBank_id());
        account.setBank(bank);account.setName(accountDTO.getName());
        account.setBalance(accountDTO.getBalance());
        account.setEmail(accountDTO.getEmail());
        account.setPassword(accountDTO.getPassword());
        account.setAccess(accountDTO.getAccess());

        response.setPayload(accountService.registerAccount(account));
        response.setStatus(true);
        List<String> messages = new ArrayList<>();
        messages.add("Account saved");
        response.setMessages(messages);

        return ResponseEntity.ok(response);
    }

}

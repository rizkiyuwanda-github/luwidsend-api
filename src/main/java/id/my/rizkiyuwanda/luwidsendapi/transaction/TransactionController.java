package id.my.rizkiyuwanda.luwidsendapi.transaction;

import id.my.rizkiyuwanda.luwidsendapi.account.Account;
import id.my.rizkiyuwanda.luwidsendapi.account.AccountService;
import id.my.rizkiyuwanda.luwidsendapi.utility.ResponseDTO;
import id.my.rizkiyuwanda.luwidsendapi.utility.StringUtility;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountService accountService;


    @PostMapping("/transfer")
    public ResponseEntity<ResponseDTO<Transaction>> transfer(@Valid @RequestBody TransactionDTO transactionDTO, Errors errors) {

        List<String> messages = new ArrayList<>();
        if (errors.hasErrors()) {
            for (ObjectError objectError : errors.getAllErrors()) {
                messages.add(objectError.getDefaultMessage());
            }
            ResponseDTO<Transaction> responseDTO = new ResponseDTO<>(false, messages, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }

        Optional<Account> senderAccount = accountService.findByIdAndBankId(transactionDTO.getSenderAccountId(), transactionDTO.getSenderBankId());
        if(senderAccount.isPresent() == false){
            messages.add("Sender account with ID "+transactionDTO.getSenderAccountId()+" not found");
            ResponseDTO<Transaction> responseDTO = new ResponseDTO<>(false, messages, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }
        Optional<Account> receiverAccount = accountService.findByIdAndBankId(transactionDTO.getReceiverAccountId(), transactionDTO.getReceiverBankId());
        if(receiverAccount.isPresent() == false){
            messages.add("Receiver account with ID "+transactionDTO.getReceiverAccountId()+" not found");
            ResponseDTO<Transaction> responseDTO = new ResponseDTO<>(false, messages, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }
        if(senderAccount.get().getBalance().compareTo(transactionDTO.getAmount()) == -1){
            messages.add("Insufficient balance");
            ResponseDTO<Transaction> responseDTO = new ResponseDTO<>(false, messages, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }

        Transaction entity = transactionService.transfer(senderAccount.get(), receiverAccount.get(), transactionDTO.getAmount(), transactionDTO.getNote());
        if (entity == null) {
            messages.add(StringUtility.FAILED);
            ResponseDTO<Transaction> responseDTO = new ResponseDTO<>(false, messages, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        } else {
            messages.add(StringUtility.SUCCESS);
            ResponseDTO<Transaction> responseDTO = new ResponseDTO<>(true, messages, entity);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }
    }


    @GetMapping("/findbysender/{senderAccountId}/{senderBankId}")
    public Iterable<Transaction> findBySender(@PathVariable("senderAccountId") String senderAccountId, @PathVariable("senderBankId") String senderBankId) {
        return transactionService.findBySender(senderAccountId, senderBankId);
    }

    @GetMapping("/findbyreceiver/{receiverAccountId}/{receiverBankId}")
    public Iterable<Transaction> findByReceiver(@PathVariable("receiverAccountId") String receiverAccountId, @PathVariable("receiverBankId") String receiverBankId) {
        return transactionService.findByReceiver(receiverAccountId, receiverBankId);
    }

    @GetMapping("/findByIdAndReceiverAccountIdAndAmount/{id}/{receiverAccountId}/{amount}")
    public Optional<Transaction> findByIdAndReceiverAccountIdAndAmount(
            @PathVariable("id") String id,
            @PathVariable("receiverAccountId") String receiverAccountId,
            @PathVariable("amount") BigDecimal amount) {
        return transactionService.findByIdAndReceiverAccountIdAndAmount(id, receiverAccountId, amount);
    }
}

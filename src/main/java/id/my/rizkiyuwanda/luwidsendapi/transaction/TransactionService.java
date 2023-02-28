package id.my.rizkiyuwanda.luwidsendapi.transaction;

import id.my.rizkiyuwanda.luwidsendapi.account.Account;
import id.my.rizkiyuwanda.luwidsendapi.account.AccountRepository;
import id.my.rizkiyuwanda.luwidsendapi.utility.StringUtility;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    public String transfer(String senderAccountId, String senderBankId,
                           String receiverAccountId, String receiverBankId,
                           BigDecimal amount, String note){

        Optional<Account> senderAccount = accountRepository.findByIdAndBankId(senderAccountId, senderBankId);
        if(senderAccount.isPresent() == false){
            return "Account with ID "+senderAccountId+" not found";
        }
        if(senderAccount.get().getBalance().compareTo(amount) == -1){
            return "Insufficient balance";
        }
        Optional<Account> receiverAccount = accountRepository.findByIdAndBankId(receiverAccountId, receiverBankId);
        if(receiverAccount.isPresent() == false){
            return "Account with ID "+receiverAccountId+" not found";
        }

        LocalDateTime ldt = LocalDateTime.now();

        //Transaction
        Transaction transaction = new Transaction();
        transaction.setId("T"+ldt.getYear()+ldt.getMonthValue()+ldt.getDayOfMonth()+ldt.getHour()+ldt.getMinute()+ldt.getSecond()+ldt.getNano());
        transaction.setSenderAccountId(senderAccount.get().getId());
        transaction.setSenderBankId(senderAccount.get().getBank().getId());
        transaction.setSenderName(senderAccount.get().getName());
        transaction.setReceiverAccountId(receiverAccount.get().getId());
        transaction.setReceiverBankId(receiverAccount.get().getBank().getId());
        transaction.setReceiverName(receiverAccount.get().getName());
        transaction.setTime(ldt);
        transaction.setAmount(amount);
        transaction.setNote(note);
        transactionRepository.save(transaction);

        //Account Balance out
        senderAccount.get().setBalance(senderAccount.get().getBalance().subtract(amount));
        accountRepository.save(senderAccount.get());

        //Account Balance in
        receiverAccount.get().setBalance(receiverAccount.get().getBalance().add(amount));
        accountRepository.save(receiverAccount.get());

        return StringUtility.SUCCESS;
    }
}

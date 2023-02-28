package id.my.rizkiyuwanda.luwidsendapi.transaction;

import id.my.rizkiyuwanda.luwidsendapi.account.Account;
import id.my.rizkiyuwanda.luwidsendapi.account.AccountRepository;
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

    public Transaction transfer(Account senderAccount, Account receiverAccount, BigDecimal amount, String note){
        LocalDateTime ldt = LocalDateTime.now();

        //Account Balance out
        senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
        accountRepository.save(senderAccount);

        //Account Balance in
        receiverAccount.setBalance(receiverAccount.getBalance().add(amount));
        accountRepository.save(receiverAccount);

        //Transaction
        Transaction transaction = new Transaction();
        transaction.setId("T"+ldt.getYear()+ldt.getMonthValue()+ldt.getDayOfMonth()+ldt.getHour()+ldt.getMinute()+ldt.getSecond()+ldt.getNano());
        transaction.setSenderAccountId(senderAccount.getId());
        transaction.setSenderBankId(senderAccount.getBank().getId());
        transaction.setSenderName(senderAccount.getName());
        transaction.setReceiverAccountId(receiverAccount.getId());
        transaction.setReceiverBankId(receiverAccount.getBank().getId());
        transaction.setReceiverName(receiverAccount.getName());
        transaction.setTime(ldt);
        transaction.setAmount(amount);
        transaction.setNote(note);
        return transactionRepository.save(transaction);
    }

    public Iterable<Transaction> findBySender(String senderAccountId, String senderBankId){
        return transactionRepository.findBySenderAccountIdAndSenderBankId(senderAccountId, senderBankId);
    }

    public Iterable<Transaction> findByReceiver(String receiverAccountId, String receiverBankId){
        return transactionRepository.findByReceiverAccountIdAndReceiverBankId(receiverAccountId, receiverBankId);
    }
}

package id.my.rizkiyuwanda.luwidsendapi.account;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountRepositoryJDBCTemplate accountRepositoryJDBCTemplate;

    public Account save(Account account){
        return accountRepository.save(account);
    }

    public void deleteById(String id){
        accountRepository.deleteById(id);
    }

    public Optional<Account> findById(String id){
        return accountRepository.findById(id);
    }

    public Optional<Account> findByIdAndBankId(String id, String bankId){
        return accountRepository.findByIdAndBankId(id, bankId);
    }


    public List<Account> findAllByBankId(String bankId){
        return accountRepositoryJDBCTemplate.findAllByBankId(bankId);
    }

    public List<Account> findAll(){
        return accountRepositoryJDBCTemplate.findAll();
    }


}

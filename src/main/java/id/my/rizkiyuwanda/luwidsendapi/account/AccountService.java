package id.my.rizkiyuwanda.luwidsendapi.account;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account save(Account account){
        return accountRepository.save(account);
    }

    public void deleteById(String id){
        accountRepository.deleteById(id);
    }

    public Optional<Account> findById(String id){
        return accountRepository.findById(id);
    }

    public Iterable<Account> findAll(){
        return accountRepository.findAll();
    }


}

package id.my.rizkiyuwanda.luwidsendapi.bank;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankRepository bankRepository;

    public Bank save(Bank bank){
        return bankRepository.save(bank);
    }

    public void remove(String id){
        bankRepository.deleteById(id);
    }

    public Bank find(String id){
        return bankRepository.findById(id).get();
    }

    public Iterable<Bank> findAll(){
        return bankRepository.findAll();
    }
}

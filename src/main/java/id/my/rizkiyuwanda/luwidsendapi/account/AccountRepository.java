package id.my.rizkiyuwanda.luwidsendapi.account;

import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, String> {
    //Tidak terpakai karena spring sudah menyediakan  query ini, query dibawah menggunakan JQl(JPA Query Language)
    //@Query("SELECT a FROM account a WHERE a.id = :id AND a.bank.id = :bankId")
    public Optional<Account> findByIdAndBankId(String id,  String bankId);
}

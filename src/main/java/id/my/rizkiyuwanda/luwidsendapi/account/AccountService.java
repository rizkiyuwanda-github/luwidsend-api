package id.my.rizkiyuwanda.luwidsendapi.account;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Transactional
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         Account account = accountRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with email '%s' not found", email))
        );
        return new org.springframework.security.core.userdetails.User(account.getEmail(), account.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(account.getAccess())));
    }

    public Account registerAccount(Account account){
        boolean userExist = accountRepository.findByEmail(account.getEmail()).isPresent();
        if(userExist == true){
            throw new RuntimeException(String.format("User with email '%s' already exist", account.getEmail()));
        }

        String encodedPassword = bCryptPasswordEncoder.encode(account.getPassword());
        account.setPassword(encodedPassword);
        return accountRepository.save(account);
    }
}

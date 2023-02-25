package id.my.rizkiyuwanda.luwidsendapi.userapp;

import id.my.rizkiyuwanda.luwidsendapi.account.Account;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Transactional
public class UserAppService implements UserDetailsService {
    @Autowired
    private UserAppRepository userAppRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        UserApp userApp = userAppRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with ID '%s' not found", id))
        );
        return new User(
                userApp.getId(), userApp.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" +userApp.getRole()))//Harus ditambahkan ROLE_ sesuai aturan spring
        );
    }

    public UserApp register(UserApp userApp){
        boolean userAppExist = userAppRepository.findById(userApp.getId()).isPresent();
        if(userAppExist == true){
            throw new RuntimeException(String.format("User with ID '%s' already exist", userApp.getId()));
        }

        String encodedPassword = bCryptPasswordEncoder.encode(userApp.getPassword());
        userApp.setPassword(encodedPassword);
        return userAppRepository.save(userApp);
    }
}

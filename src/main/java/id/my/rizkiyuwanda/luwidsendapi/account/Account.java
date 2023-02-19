package id.my.rizkiyuwanda.luwidsendapi.account;

import id.my.rizkiyuwanda.luwidsendapi.bank.Bank;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "account")
@Data
public class Account  {

    public static final String ACCESS_USER = "User";
    @Id
    @Column(length = 50)
    private String id;
    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;
    @NotEmpty(message = "Name is required")
    @Column(length = 100, nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal balance;
    @Email(message = "Email format is required")
    @Column(length = 100, nullable = false, unique = true)
    private String email;
    @Column(length = 100, nullable = false)
    private String password;
    @Column(length = 100, nullable = false)
    private String access;


}

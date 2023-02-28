package id.my.rizkiyuwanda.luwidsendapi.account;

import id.my.rizkiyuwanda.luwidsendapi.bank.Bank;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
@Data
public class Account {

    @Size(max = 50)
    @NotNull
    @Id
    @Column(length = 50)
    private String id;

    @NotNull
    @JoinColumn(name = "bank_id", nullable = false)
    @ManyToOne
    private Bank bank;

    @NotNull
    @Size(max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @DecimalMin(value = "0.0", inclusive = true)
    @Digits(integer=19, fraction=2)
    @Column(nullable = false)
    private BigDecimal balance;


}

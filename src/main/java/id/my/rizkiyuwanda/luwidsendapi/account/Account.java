package id.my.rizkiyuwanda.luwidsendapi.account;

import id.my.rizkiyuwanda.luwidsendapi.bank.Bank;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
@Data
public class Account {


    @Size(max = 50)
    @Id
    @Column(length = 50)
    private String id;

    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;

    @NotEmpty()
    @Column(length = 100, nullable = false)
    private String name;

    @NotEmpty()
    @Column(nullable = false)
    private BigDecimal balance;


}

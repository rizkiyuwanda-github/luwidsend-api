package id.my.rizkiyuwanda.luwidsendapi.transaction;

import id.my.rizkiyuwanda.luwidsendapi.account.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
public class Transaction {
    @Size(max = 50)
    @NotNull
    @Id
    @Column(length = 50)
    private String id;

    @NotNull
    @Column(name = "sender_bank_id", length = 5)
    private String senderBankId;

    @NotNull
    @Column(name = "sender_bank_name", length = 100)
    private String senderBankName;

    @NotNull
    @Column(name = "sender_account_id", length = 50)
    private String senderAccountId;

    @NotNull
    @Column(name = "sender_account_name", length = 100)
    private String senderAccountName;

    @NotNull
    @Column(name = "receiver_bank_id", length = 5)
    private String receiverBankId;

    @NotNull
    @Column(name = "receiver_bank_name", length = 100)
    private String receiverBankName;

    @NotNull
    @Column(name = "receiver_account_id", length = 50)
    private String receiverAccountId;

    @NotNull
    @Column(name = "receiver_account_name", length = 100)
    private String receiverAccountName;

    @NotNull
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime time;

    @DecimalMin(value = "0.0", inclusive = true)
    @Digits(integer=19, fraction=2)
    @Column(nullable = false)
    private BigDecimal amount;


    private String note;

}

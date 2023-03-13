package id.my.rizkiyuwanda.luwidsendapi.transaction;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    private String senderBankId;
    private String senderAccountId;
    private String receiverBankId;
    private String receiverAccountId;
    private BigDecimal amount;
    private String note;
}

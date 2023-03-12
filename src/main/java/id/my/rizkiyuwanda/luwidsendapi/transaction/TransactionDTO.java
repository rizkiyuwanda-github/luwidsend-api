package id.my.rizkiyuwanda.luwidsendapi.transaction;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    private String id;
    private String senderAccountId;
    private String senderBankId;
    private String receiverAccountId;
    private String receiverBankId;
    private BigDecimal amount;
    private String note;
}

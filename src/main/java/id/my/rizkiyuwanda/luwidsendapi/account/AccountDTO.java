package id.my.rizkiyuwanda.luwidsendapi.account;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {
    private String id;
    private String bankId;
    private String name;
    private BigDecimal balance;

}

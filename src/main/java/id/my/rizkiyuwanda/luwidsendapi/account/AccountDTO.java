package id.my.rizkiyuwanda.luwidsendapi.account;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {
    private String id;
    private String bank_id;
    private String name;
    private BigDecimal balance;
    private String email;
    private String password;
    private String access;

}

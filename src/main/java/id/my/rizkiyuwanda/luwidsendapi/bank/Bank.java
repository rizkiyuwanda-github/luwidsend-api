package id.my.rizkiyuwanda.luwidsendapi.bank;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "bank")
@Data
public class Bank {
    @Id
    @Column(length = 5)
    private String id;
    @Nonnull
    @Column(length = 200)
    private String name;
}

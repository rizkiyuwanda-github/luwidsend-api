package id.my.rizkiyuwanda.luwidsendapi.transaction;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TransactionRepository extends CrudRepository<Transaction, String> {
    public Iterable<Transaction> findBySenderAccountIdAndSenderBankId(String senderAccountId, String senderBankId);
    public Iterable<Transaction> findByReceiverAccountIdAndReceiverBankId(String receiverAccountId, String receiverBankId);
}

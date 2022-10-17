package com.pfms.Personal_Finance_Management.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pfms.Personal_Finance_Management.model.Piechart;
import com.pfms.Personal_Finance_Management.model.TransactionDetails;
import com.pfms.Personal_Finance_Management.model.Wallet;
@Repository
public interface TransRepository extends JpaRepository<TransactionDetails, Long> {
	
	
    @Query(value="SELECT * FROM Transactions t where t.uid= :user_id ORDER BY date DESC LIMIT 3", nativeQuery = true)
	List<TransactionDetails>getTop3(Long user_id);

//    @Query(value="SELECT * FROM Transactions t where t.bank_name= :walletName", nativeQuery = true)
//    List<TransactionDetails> findAllByname(String walletName);

    
    @Query(value="SELECT category, SUM(amount) from Transactions t  where t.bank_name= :wallet_name AND t.uid= :user_id GROUP BY category", nativeQuery=true)
	List<Object[]> getData(String wallet_name, Long user_id);

	@Query(value="SELECT * FROM Transactions t where t.bank_name= :wallet_name AND t.uid=:user_id", nativeQuery = true)
	List<TransactionDetails> findByIdAndName(long user_id, String wallet_name);

	
	
	@Query(value="SELECT category, SUM(amount) FROM Transactions WHERE uid=:user_id AND date BETWEEN :fDate and :dDate GROUP BY category",nativeQuery = true)
	List<Object[]> getdataByDate(String fDate, String dDate, long user_id);



}

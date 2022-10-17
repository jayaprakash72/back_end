package com.pfms.Personal_Finance_Management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pfms.Personal_Finance_Management.model.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long>{

	@Query(value="SELECT * from wallet w where w.uid= :user_id", nativeQuery = true)
	List<Wallet> findAllById(long user_id);

	@Query(value="SELECT * from wallet w where w.wallet_name= :wallet_name",nativeQuery = true)
	Wallet findByName(String wallet_name);

	@Query(value="SELECT * from wallet w where w.wallet_name= :name AND w.uid= :user_id",nativeQuery = true)
	Wallet findByIdAndName(long user_id, String name);
	
	@Query(value="SELECT sum(wallet_amount) from Wallet w where w.uid= :user_id",nativeQuery = true)
	Double getTotalAmountById(long user_id);
	
	@Query(value="DELETE from Wallet w where w.uid= :user_id AND w.wallet_name= :walletName ",nativeQuery = true)
	void deleteWalletByIdAndName(long user_id, String walletName);

//	@Query(value="SELECT * from wallet w where w.wallet_name= :wallet_name And w.uid= :user_id",nativeQuery = true)
//	Wallet findByIdAndName(long user_id, String walletName);
	

}

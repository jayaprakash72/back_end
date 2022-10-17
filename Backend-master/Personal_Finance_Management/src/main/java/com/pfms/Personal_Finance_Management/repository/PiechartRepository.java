package com.pfms.Personal_Finance_Management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pfms.Personal_Finance_Management.model.Piechart;
import com.pfms.Personal_Finance_Management.model.TransactionDetails;

@Repository
public interface PiechartRepository extends JpaRepository<Piechart,String>{
   
//	@Query("select new com.pfms.Personal_Finance_Management.model.Piechart(a.category, a.amount) from TransactionDetails a ")
//    List<Piechart> getData();


	

}

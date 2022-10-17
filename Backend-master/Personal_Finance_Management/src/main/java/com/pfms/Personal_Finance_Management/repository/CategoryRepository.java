package com.pfms.Personal_Finance_Management.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.pfms.Personal_Finance_Management.model.Category;




@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query("select c.category from Category c")
	public List<String> getOnlyCategories();

	@Query("select c.categoryId from Category c where c.category = :category")
	public int getIdForCategory(@Param("category") String category);


	public Category findByCategory(String category);
	
	
//	@Query("SELECT c.category, SUM(e.amount) FROM Category c, Transactions e WHERE c.categoryId IN (SELECT categoryId from Transactions t where t.date BETWEEN :fDate and :eDate )GROUP BY c.category")
//	public List<Object[]> getPieDataByDate(Date fDate, Date tDate);


	@Query(value = "SELECT category from Category c where c.uid= :user_id ", nativeQuery = true)
	public List<String> findAllById(long user_id);



//	@Query("SELECT c.category, SUM(e.amount) FROM Category c, Transaction e WHERE c.categoryId = e.categoryId GROUP BY c.category")
//	public List<Object[]> getPieData();


}

package com.pfms.Personal_Finance_Management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pfms.Personal_Finance_Management.model.Goal;



@Repository
public interface GoalRepository extends JpaRepository<Goal, Long>{

	@Query(value="SELECT * from Goal g where g.uid = :user_id ",nativeQuery = true)
	List<Goal> findAllByID(long user_id);

	@Query(value="Select * from Goal g where g.goal_name = :goalName",nativeQuery = true)
	Goal findByName(String goalName);
	
	@Query(value="SELECT * from Goal g where g.uid = :user_id ",nativeQuery = true)
	List<Object[]> findAllBYID(long user_id);

//	@Query(value="SELECT Sum(goal_amount) from Goal g where g.uid = :user_id",nativeQuery = true)
//	Double getToalAmountById(long user_id);
	
	@Query(value="SELECT gid from Goal g order by g.gid DESC LIMIT 1",nativeQuery = true)
	Long findLastID();

	@Query(value="SELECT * from Goal g where g.cid= :categoryId",nativeQuery = true)
	List<Goal> findAllByCategoryId(long categoryId);

}

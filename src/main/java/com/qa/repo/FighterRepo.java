package com.qa.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.data.Fighter;

@Repository
public interface FighterRepo extends JpaRepository<Fighter,Integer>{
	List<Fighter> findByName(String name);
}

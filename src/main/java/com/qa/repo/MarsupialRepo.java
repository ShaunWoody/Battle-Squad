package com.qa.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.data.Fighter;

@Repository
public interface MarsupialRepo extends JpaRepository<Fighter,Integer>{

}

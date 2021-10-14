package com.qa.service;

import java.util.List;

import com.qa.data.Fighter;

public interface FighterService {
	
	public Fighter getFigtherByIndex(Integer id);
	
	public List<Fighter> getAllFighters();
	
	public Fighter createFighter(Fighter fighter);
	
	public Fighter updateFighter(Fighter fighter, Integer id);
	
	public void deleteFighter(Integer id);
	
	

}
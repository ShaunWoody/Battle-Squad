package com.qa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;

import com.qa.data.Fighter;

@Primary
public class FighterServiceList implements FighterService {

	private List<Fighter> fighters = new ArrayList<>();
	
	@Override
	public Fighter getFigtherByIndex(Integer id) {
		
		return this.fighters.get(id);
	}

	@Override
	public List<Fighter> getAllFighters() {
		
		return this.fighters;
	}

	@Override
	public Fighter createFighter(Fighter fighter) {
		this.fighters.add(fighter);
		return this.fighters.get(this.fighters.size()-1);
	}

	@Override
	public Fighter updateFighter(Fighter fighter, Integer id) {
		return this.fighters.set(id,fighter);
	}

	@Override
	public void deleteFighter(Integer id) {
		Fighter delete = this.fighters.get(id);
		this.fighters.remove(delete);
		
	}
	
	

}

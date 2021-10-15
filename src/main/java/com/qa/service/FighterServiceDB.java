package com.qa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.qa.data.Fighter;
import com.qa.repo.FighterRepo;

@Primary
@Service
public class FighterServiceDB implements FighterService {

	private FighterRepo repo;

	public FighterServiceDB(FighterRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Fighter getFigtherByIndex(Integer id) {
		return this.repo.findById(id).orElseThrow(null);
	}

	@Override
	public List<Fighter> getFigtherByName(String name) {

		return this.repo.findByName(name);
	}

	@Override
	public List<Fighter> getAllFighters() {
		return this.repo.findAll();
	}

	@Override
	public Fighter createFighter(Fighter fighter) {
		return this.repo.save(fighter);
	}

	@Override
	public Fighter updateFighter(Fighter fighter, Integer id) {
		Optional<Fighter> optionalFighter = this.repo.findById(id);
		Fighter toUpdate = optionalFighter.get();
		
		toUpdate.setName(fighter.getName());
		toUpdate.setWeapon(fighter.getWeapon());
		toUpdate.setArmour(fighter.getArmour());
		toUpdate.setDefence(fighter.getDefence());
		toUpdate.setHealth(fighter.getHealth());
		toUpdate.setMeleeskill(fighter.getMeleeskill());
		
		return this.repo.save(toUpdate);
	}

	@Override
	public boolean deleteFighter(Integer id) {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		return !exists;

	}

}

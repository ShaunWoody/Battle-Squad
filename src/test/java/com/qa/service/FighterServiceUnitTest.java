package com.qa.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.data.Fighter;
import com.qa.repo.FighterRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FighterServiceUnitTest {

	@Autowired
	private FighterServiceDB service;

	@MockBean
	private FighterRepo repo;

	@Test
	void testGetByName() {
		final String name = "Shaun";
		final List<Fighter> fighters = List.of(new Fighter(1, name, "Sword", "Plate",100,20,20));

		Mockito.when(this.repo.findByName(name)).thenReturn(fighters);

		assertThat(this.service.getFigtherByName(name)).isEqualTo(fighters);

		Mockito.verify(this.repo, Mockito.times(1)).findByName(name);
	}

	@Test
	void testGetById() {
		final Integer Id = 1;
		final Fighter fighter = new Fighter(Id, "Shaun", "Sword", "Plate",100,20,20);

		Mockito.when(this.repo.findById(Id)).thenReturn(Optional.of(fighter));

		assertThat(this.service.getFigtherByIndex(Id)).isEqualTo(fighter);

		Mockito.verify(this.repo, Mockito.times(1)).findById(Id);
	}

	@Test
	void testGetAllFighters() {
		final List<Fighter> fighters = List.of(new Fighter(1, "Shaun", "Sword", "Plate",100,20,20),
				new Fighter(2, "Harold", "Axe", "Plate-mail",90,10,10));

		Mockito.when(this.repo.findAll()).thenReturn(fighters);

		assertThat(this.service.getAllFighters()).isEqualTo(fighters);

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	void testUpdate() { 
		final Integer id = 1;
		Fighter fighter = new Fighter(id, "Shaun", "Sword", "Plate",100,20,20);
		Optional<Fighter> optionalFighter = Optional.of(fighter);

		Fighter newFighter = new Fighter(id, "Harold", "Axe", "Plate-mail",90,10,10);

		Mockito.when(this.repo.findById(id)).thenReturn(optionalFighter);
		Mockito.when(this.repo.save(newFighter)).thenReturn(newFighter);

		assertThat(this.service.updateFighter(newFighter, fighter.getId())).isEqualTo(newFighter);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(newFighter);
	}

	@Test
	void testCreate() {
		Fighter newFighter = new Fighter(null, "Harold", "Axe", "Plate-mail",90,10,10);
		Fighter savedFighter = new Fighter(1, "Harold", "Axe", "Plate-mail",90,10,10);

		Mockito.when(this.repo.save(newFighter)).thenReturn(savedFighter);

		assertThat(this.service.createFighter(newFighter)).isEqualTo(savedFighter);

		Mockito.verify(this.repo, Mockito.times(1)).save(newFighter);
	}

	@Test
	void testDelete() {
		final Integer id = 1;

		Mockito.when(this.repo.existsById(id)).thenReturn(false);

		assertThat(this.service.deleteFighter(id)).isEqualTo(true);

		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}
}
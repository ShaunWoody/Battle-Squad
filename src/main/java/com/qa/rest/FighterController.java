package com.qa.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.data.Fighter;
import com.qa.service.FighterService;

@CrossOrigin
@RestController // enables http endpoints and tells spring to make a bean of this class
public class FighterController {

	private FighterService service;

	public FighterController(FighterService service) {
		super();
		this.service = service;
	}

	@GetMapping("/getFighter/{id}")
	public Fighter getFighterByIndex(@PathVariable Integer id) {
		return this.service.getFigtherByIndex(id);
	}

	@GetMapping("getFighterByName/{name}")
	public List<Fighter> getFighterByName(@PathVariable String name) {
		return this.service.getFigtherByName(name);
	}

	@GetMapping("/getAllFighters")
	public List<Fighter> getAllFighters() {
		return this.service.getAllFighters();
	}

	@PostMapping("/createFighter")
	public ResponseEntity<Fighter> createFighter(@RequestBody Fighter fighter) {
		Fighter responseB = this.service.createFighter(fighter);
		ResponseEntity<Fighter> response = new ResponseEntity<Fighter>(responseB, HttpStatus.CREATED);
		return response;
	}
	
	@PutMapping("/updateFighter/{id}")
	public ResponseEntity<Fighter> updateFighter(@RequestBody Fighter fighter, @PathVariable Integer id){
		Fighter responseB = this.service.updateFighter(fighter, id);
		return new ResponseEntity<Fighter>(responseB,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/removeFighter/{id}")
	public ResponseEntity<?> deleteFighter(@PathVariable Integer id){
		boolean deleted = this.service.deleteFighter(id);
		if (deleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

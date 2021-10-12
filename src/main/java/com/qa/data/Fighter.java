package com.qa.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // tells Spring that this is a table in the db
public class Fighter {

	@Id // PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT

	private Integer id;

	private String name;

	private String weapon;

	private String armour;

	private Integer health;

	private Integer defence;

	private Integer meleeskill;

	public Fighter(Integer id, String name, String weapon, String armour, Integer health, Integer defence,
			Integer meleeskill) { // Constructor
		super();
		this.id = id;
		this.name = name;
		this.weapon = weapon;
		this.armour = armour;
		this.health = health;
		this.defence = defence;
		this.meleeskill = meleeskill;
	}

	public Fighter()  { // Empty Constructor
		super();
	}

	
	
	// Getters and setters
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public String getArmour() {
		return armour;
	}

	public void setArmour(String armour) {
		this.armour = armour;
	}

	public Integer getHealth() {
		return health;
	}

	public void setHealth(Integer health) {
		this.health = health;
	}

	public Integer getDefence() {
		return defence;
	}

	public void setDefence(Integer defence) {
		this.defence = defence;
	}

	public Integer getMeleeskill() {
		return meleeskill;
	}

	public void setMeleeskill(Integer meleeskill) {
		this.meleeskill = meleeskill;
	}

}

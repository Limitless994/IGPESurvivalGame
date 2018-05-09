package dev.igpe.theamazingame.items.weapons;

public abstract class Weapon {
	
	private int damage;
	private float fireRate;
	
	public Weapon(float fireRate, int calibro, int damage) {
		this.damage=damage;
		this.fireRate=fireRate;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public float getFireRate() {
		return fireRate;
	}

	public void setFireRate(float fireRate) {
		this.fireRate = fireRate;
	}

	
	
	
	
}

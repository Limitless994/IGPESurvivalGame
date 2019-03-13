package dev.igpe.theamazingame.items.weapons;

import java.awt.image.BufferedImage;

import dev.igpe.theamazingame.audio.AudioPlayer;
import dev.igpe.theamazingame.items.Item;

public abstract class Weapon extends Item{
	public int getFireRange() {
		return fireRange;
	}

	public void setFireRange(int fireRange) {
		this.fireRange = fireRange;
	}

	protected int damage;
	protected float fireRate;
	protected int calibro;
	protected int fireRange;
	protected AudioPlayer shootSound;
	public Weapon(BufferedImage texture, String name, int id, int damage, long fireRate, int fireRange, AudioPlayer shootSound) {
		super(texture, name, id);
		this.damage=damage;
		this.fireRate=fireRate;
		this.fireRange = fireRange;
		this.shootSound = shootSound;
	}

	public AudioPlayer getShootSound() {
		return shootSound;
	}

	public void setShootSound(AudioPlayer shootSound) {
		this.shootSound = shootSound;
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

	public void setFireRate(long fireRate) {
		this.fireRate = fireRate;
	}
	
	

	
	
	
	
}

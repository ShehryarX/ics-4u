package entities;
import interfaces.Config;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Entity implements Config {
	protected int x, y, radius, health;
	protected Color colour;
	 
	public void show(Graphics g) {
		g.setColor(colour);
		g.fillOval(x * SCALING_FACTOR ,y * SCALING_FACTOR, radius, radius);
	}
	
	public String toString() {
		return String.format("{name: %s, x: %d, y: %d, radius: %d, health: %d}", this.getClass().getSimpleName(), x, y, radius, health);
	}
}

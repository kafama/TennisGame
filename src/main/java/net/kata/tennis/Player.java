package net.kata.tennis;

public class Player {

	private String name;
	private int score;
	
	public int getScore() {
		return score;
	}
	
	public Player() {
		// TODO Auto-generated constructor stub
	}
	
	public Player(String name) {
		this.name = name;
	}
	

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public void incrementScore() {
		this.score++;
	}
}

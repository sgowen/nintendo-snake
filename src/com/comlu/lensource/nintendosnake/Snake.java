package com.comlu.lensource.nintendosnake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake {
	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	private static final int NES_TURN = 5;
	private static final int SNES_TURN = 4;
	private static final int N64_TURN = 3;
	private static final int GC_TURN = 2;
	private static final int WII_TURN = 1;
	
	public List<SnakePart> parts = new ArrayList<SnakePart>();
	public int direction;
	public int bonusScore;
	private int gracePeriodForTurns;
	private int turnLoss;
	public int itemsEaten;
	private int selectedTheme = 1;
	public int morphBallAnim = 2;
	
	Random random = new Random();
	
	public Snake(int selectedTheme){
		this.selectedTheme = selectedTheme;
		direction = UP;
		gracePeriodForTurns = 0;
		bonusScore = 10;
		turnLoss = NES_TURN;
		itemsEaten = 0;
		parts.add(new SnakePart(5,6,selectedTheme));
		parts.add(new SnakePart(5,7,selectedTheme));
		parts.add(new SnakePart(5,8,selectedTheme));
	}
	
	public void turnLeft(){
		if(gracePeriodForTurns != 0){
			if(bonusScore >= turnLoss){
				bonusScore -= turnLoss;
			}
		}
		gracePeriodForTurns++;
		direction += 1;
		if(direction > RIGHT){
			direction = UP;
		}
	}
	
	public void turnRight(){
		if(gracePeriodForTurns != 0){
			if(bonusScore >= turnLoss){
				bonusScore -= turnLoss;
			}
		}
		gracePeriodForTurns++;
		direction -= 1;
		if(direction < UP){
			direction = RIGHT;
		}
	}
	
	public void eat(){
		gracePeriodForTurns = 0;
		bonusScore = 10;
		itemsEaten++;
		SnakePart end = parts.get(parts.size()-1);
		parts.add(new SnakePart(end.x, end.y, selectedTheme));
		if(itemsEaten < 12){
			turnLoss = NES_TURN;
		} else if(itemsEaten < 24){
			turnLoss = SNES_TURN;
		} else if(itemsEaten < 36){
			turnLoss = N64_TURN;
		} else if(itemsEaten < 48){
			turnLoss = GC_TURN;
		} else{
			turnLoss = WII_TURN;
		}
	}
	
	public void advance(){
		morphBallAnim++;
		if(morphBallAnim > 2){
			morphBallAnim = 0;
		}
		SnakePart head = parts.get(0);
		
		int len = parts.size() - 1;
		for(int i = len; i > 0; i--){
			SnakePart before = parts.get(i-1);
			SnakePart part = parts.get(i);
			part.x = before.x;
			part.y = before.y;
		}
		
		if(direction == UP){
			head.y -= 1;
		}
		if(direction == LEFT){
			head.x -= 1;
		}
		if(direction == DOWN){
			head.y += 1;
		}
		if(direction == RIGHT){
			head.x += 1;
		}
		
		if(head.x < 0){
			head.x = 9;
		}
		if(head.x > 9){
			head.x = 0;
		}
		if(head.y < 0){
			head.y = 12;
		}
		if(head.y > 12){
			head.y = 0;
		}
	}
	
	public boolean checkBitten(){
		int len = parts.size();
		SnakePart head = parts.get(0);
		for(int i =1; i < len; i++){
			SnakePart part = parts.get(i);
			if(part.x == head.x && part.y == head.y){
				return true;
			}
		}
		return false;
	}
}

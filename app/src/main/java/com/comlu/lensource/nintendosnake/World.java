package com.comlu.lensource.nintendosnake;

import java.util.Random;

public class World {
	static final int WORLD_WIDTH = 10;
	static final int WORLD_HEIGHT = 13;
	static final int SCORE_INCREMENT = 10;
	static final float TICK_INITIAL = 0.5f;
	static final float TICK_DECREMENT = 0.05f;
	
	public Snake snake;
	public Item item;
	public boolean gameOver = false;
	public int score = 0;
	public int bonusScore = 10;
	
	boolean fields[][] = new boolean[WORLD_WIDTH][WORLD_HEIGHT];
	Random random = new Random();
	float tickTime = 0;
	float tick = TICK_INITIAL;
	
	int selectedTheme = 0;
	
	public World(int selectedTheme){
		snake = new Snake(selectedTheme);
		this.selectedTheme = selectedTheme;
		placeItem(selectedTheme);
	}
	
	private void placeItem(int theme){
		for(int x = 0; x < WORLD_WIDTH; x++){
			for(int y = 0; y < WORLD_HEIGHT; y++){
				fields[x][y] = false;
			}
		}
		
		int len = snake.parts.size();
		for(int i = 0; i < len; i++){
			SnakePart part = snake.parts.get(i);
			fields[part.x][part.y] = true;
		}
		
		int itemX = random.nextInt(WORLD_WIDTH);
		int itemY = random.nextInt(WORLD_HEIGHT);
		
		while(true){
			if(fields[itemX][itemY] == false){
				break;
			}
			
			itemX += 1;
			if(itemX >= WORLD_WIDTH){
				itemX = 0;
				itemY += 1;
				if(itemY >= WORLD_HEIGHT){
					itemY = 0;
				}
			}
		}
		
		item = new Item(itemX, itemY, theme);
	}
	
	public void update(float deltaTime){
		bonusScore = snake.bonusScore;
		if(gameOver){
			return;
		}
		
		tickTime += deltaTime;
		while(tickTime > tick){
			tickTime -= tick;
			snake.advance();
			if(snake.checkBitten()){
				gameOver = true;
				return;
			}
			
			SnakePart head = snake.parts.get(0);
			
			if(head.x == item.x && head.y == item.y){
				score += SCORE_INCREMENT;
				//score += bonusScore;
				snake.eat();
				if(snake.parts.size() == WORLD_WIDTH * WORLD_HEIGHT){
					gameOver = true;
					return;
				} else{
					placeItem(selectedTheme);
				}
				
				if(snake.itemsEaten % 12 == 0 && tick - TICK_DECREMENT > 0){
					tick -= TICK_DECREMENT;
				}
			}
		}
	}
}

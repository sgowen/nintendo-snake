package com.comlu.lensource.nintendosnake;

import java.util.List;

import android.graphics.Color;

import com.comlu.lensource.framework.Game;
import com.comlu.lensource.framework.Graphics;
import com.comlu.lensource.framework.Pixmap;
import com.comlu.lensource.framework.Screen;
import com.comlu.lensource.framework.Input.TouchEvent;

public class EndGameScreen extends Screen{
	enum EndGameState {NewHighScore, GameOver};
	EndGameState egState;
	Graphics g;
	Animation waitTime;
	Pixmap gameOverPixmap;
	Pixmap submitPixmap;
	Pixmap numbersPixmap;
	String score;
	int numericScore;
	int firstIndex;
	int secondIndex;
	int thirdIndex;
	public EndGameScreen(Game game) {
		super(game);
		g = game.getGraphics();
		waitTime = new Animation();
		firstIndex = 0;
		secondIndex = 0;
		thirdIndex = 0;
		
		if(Settings.isNewHighScore){
			egState = EndGameState.NewHighScore;
			numericScore = Settings.highscores[Settings.newHighScoreRow];
			score = Integer.toString(numericScore);
		} else{
			egState = EndGameState.GameOver;
		}
		if(Settings.getSettingAt(0)){
			gameOverPixmap = Assets.gameOver1;
			submitPixmap = Assets.submit1;
			numbersPixmap = Assets.numbers1;
		} else if(Settings.getSettingAt(1)){
			gameOverPixmap = Assets.gameOver2;
			submitPixmap = Assets.submit2;
			numbersPixmap = Assets.numbers2;
		} else if(Settings.getSettingAt(2)){
			gameOverPixmap = Assets.gameOver3;
			submitPixmap = Assets.submit3;
			numbersPixmap = Assets.numbers3;
		}
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		if(egState == EndGameState.NewHighScore){
			updateNewHighScore(touchEvents, deltaTime);
		}
		if(egState == EndGameState.GameOver){
			updateGameOver(touchEvents, deltaTime);
		}
	}
	
	private void updateNewHighScore(List<TouchEvent> touchEvents, float deltaTime){
		int len = touchEvents.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP){
				if(event.y > 245 && event.y < 245+40){
					if(event.x > 62 && event.x < 62+48){
						handleSound(0);
						firstIndex++;
						if(firstIndex > 25){
							firstIndex = 0;
						}
					} else if(event.x > 141 && event.x < 141+48){
						handleSound(0);
						secondIndex++;
						if(secondIndex > 25){
							secondIndex = 0;
						}
					} else if(event.x > 219 && event.x < 219+48){
						handleSound(0);
						thirdIndex++;
						if(thirdIndex > 25){
							thirdIndex = 0;
						}
					}
				} else if(event.y > 323 && event.y < 323+40){
					if(event.x > 62 && event.x < 62+48){
						handleSound(0);
						firstIndex--;
						if(firstIndex < 0){
							firstIndex = 25;
						}
					} else if(event.x > 141 && event.x < 141+48){
						handleSound(0);
						secondIndex--;
						if(secondIndex < 0){
							secondIndex = 25;
						}
					} else if(event.x > 219 && event.x < 219+48){
						handleSound(0);
						thirdIndex--;
						if(thirdIndex < 0){
							thirdIndex = 25;
						}
					}
				} else if(event.y > 410){
					handleSound(0);
					Settings.highscoresInitials[Settings.newHighScoreRow] = Settings.letters[firstIndex] + Settings.letters[secondIndex] + Settings.letters[thirdIndex];
					Settings.save(game);
					egState = EndGameState.GameOver;
					return;
				}
			}
		}
	}

	private void updateGameOver(List<TouchEvent> touchEvents, float deltaTime){
		if(waitTime.count == 0){
			waitTime.update(deltaTime, Animation.TICK_WAIT, Animation.WAIT);
		}
		if(waitTime.count > 0){
			int len = touchEvents.size();
			for(int i = 0; i < len; i++){
				TouchEvent event = touchEvents.get(i);
				if(event.type == TouchEvent.TOUCH_UP){
					if(event.x > 60 && event.x < 60+200 && event.y > 320 && event.y < 320+80){
						handleSound(0);
						game.setScreen(new GameScreen(game));					
						return;
					} else if(event.x > 60 && event.x < 60+200 && event.y > 320+80 && event.y < 320+160){
						handleSound(0);
						game.setScreen(new LoadingScreen(game));					
						return;
					}
				}
			}
		}
	}
	
	@Override
	public void present(float deltaTime) {
		g.drawPixmap(Assets.blackness, 0, 0);
		
		if(egState == EndGameState.NewHighScore){
			drawNewHighScoreUI(deltaTime);
		}
		if(egState == EndGameState.GameOver){
			drawGameOverUI(deltaTime);
		}
	}

	private void drawNewHighScoreUI(float deltaTime){
		g.drawPixmap(Assets.newHighScore, 40, 60);
		
		g.drawPixmap(Assets.scrollUp, 62, 245);
		g.drawPixmap(Assets.scrollUp, 141, 245);
		g.drawPixmap(Assets.scrollUp, 219, 245);
		
		g.drawRect(62, 284, 32, 32, Color.WHITE);
		g.drawRect(141, 284, 32, 32, Color.WHITE);
		g.drawRect(219, 284, 32, 32, Color.WHITE);
		
		g.drawPixmap(Assets.scrollDown, 62, 323);
		g.drawPixmap(Assets.scrollDown, 141, 323);
		g.drawPixmap(Assets.scrollDown, 219, 323);
		
		g.drawText(Settings.letters[firstIndex]+ "  " + Settings.letters[secondIndex]+ "  " + Settings.letters[thirdIndex], 155, 310, 26, "", Color.BLACK);
		
		g.drawPixmap(submitPixmap, 80, 410);
		drawText(score, g.getWidth() / 2 - score.length()*20 / 2, 16);
	}
	private void drawGameOverUI(float deltaTime){
		g.drawPixmap(Assets.gameOver, 40, 60);
		g.drawPixmap(gameOverPixmap, 60, 320);
	}
	
	public void drawText(String line, int x, int y){
		int len = line.length();
		for(int i = 0; i < len; i++){
			char character = line.charAt(i);
			int srcX = 0;
			int srcWidth = 0;
			
			String tempChar = "";
			tempChar += character;
			srcX = Integer.parseInt(tempChar) * 20;
			srcWidth = 20;
			
			g.drawPixmap(numbersPixmap, x, y, srcX, 0, srcWidth, 32);
			x += srcWidth;
		}
	}
	
	private void handleSound(int soundToPlay){
		if(Settings.playSound){
			switch(soundToPlay){
			case 0:
				Assets.click.play(1);
				break;
			default:
				break;
			}
		}
	}
	
	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		waitTime = null;
		try {
			finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}

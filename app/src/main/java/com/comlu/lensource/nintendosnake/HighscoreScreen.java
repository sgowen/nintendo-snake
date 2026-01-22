package com.comlu.lensource.nintendosnake;

import java.util.List;

import android.graphics.Color;

import com.comlu.lensource.framework.Game;
import com.comlu.lensource.framework.Graphics;
import com.comlu.lensource.framework.Screen;
import com.comlu.lensource.framework.Input.TouchEvent;

public class HighscoreScreen extends Screen{
	Graphics g;
	
	public HighscoreScreen(Game game) {
		super(game);
		g = game.getGraphics();
		g.drawPixmap(Assets.background, 0, 0);
		g.drawPixmap(Assets.highscores, 20, 10);
		g.drawPixmap(Assets.back, 10, 440);
		g.drawLine(20, 64, 300, 64, Color.MAGENTA);
		g.drawLine(95, 64, 95, 400, Color.MAGENTA);
		g.drawLine(220, 64, 220, 400, Color.MAGENTA);
		g.drawLine(20, 400, 300, 400, Color.MAGENTA);
		int y = 104;
		for(int i = 0; i < Settings.highscores.length; i++){
			g.drawText(Settings.highscoresRanks[i], 50, y, 18, "", Color.BLACK);
			g.drawText(Integer.toString(Settings.highscores[i]), 155, y, 18, "", Color.BLACK);
			g.drawText(Settings.highscoresInitials[i], 265, y, 18, "", Color.BLACK);
			y += 32;
		}
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.x < 64 && event.y > 416){
				handleSound(0);
				game.setScreen(new MainMenuScreen(game));
				return;
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		
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
		
	}
}

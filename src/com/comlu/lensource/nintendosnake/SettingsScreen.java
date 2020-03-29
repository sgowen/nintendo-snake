package com.comlu.lensource.nintendosnake;

import java.util.List;

import android.graphics.Color;

import com.comlu.lensource.framework.Game;
import com.comlu.lensource.framework.Graphics;
import com.comlu.lensource.framework.Screen;
import com.comlu.lensource.framework.Input.TouchEvent;

public class SettingsScreen extends Screen{
	Graphics g;
	public SettingsScreen(Game game) {
		super(game);
		g = game.getGraphics();
		g.drawPixmap(Assets.background, 0, 0);
		g.drawPixmap(Assets.calibrateTouch, 40, 10);
		g.drawPixmap(Assets.configKeyboard, 40, 130);
		g.drawPixmap(Assets.sound, 60, 275);
		g.drawPixmap(Assets.back, 10, 440);
		g.drawLine(0, 120, 320, 120, Color.MAGENTA);
		g.drawLine(0, 240, 320, 240, Color.MAGENTA);
		g.drawLine(0, 360, 320, 360, Color.MAGENTA);
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP){
				if(event.y < 120){
					handleSound(0);
					game.setScreen(new CalibrationScreen(game));
					return;
				} else if(event.y < 240){
					handleSound(0);
					game.setScreen(new KeyConfigScreen(game));
					return;
				} else if(event.y < 360){
					handleSound(0);
					game.setScreen(new AdjustSoundScreen(game));
					return;
				}
				if(event.x < 64 && event.y > 416){
					handleSound(0);
					game.setScreen(new MainMenuScreen(game));
					return;
				}
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
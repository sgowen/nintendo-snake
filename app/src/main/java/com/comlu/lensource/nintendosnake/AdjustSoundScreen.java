package com.comlu.lensource.nintendosnake;

import java.util.List;

import android.graphics.Color;

import com.comlu.lensource.framework.Game;
import com.comlu.lensource.framework.Graphics;
import com.comlu.lensource.framework.Screen;
import com.comlu.lensource.framework.Input.TouchEvent;

public class AdjustSoundScreen extends Screen{
	Graphics g;
	String message;
	boolean showText;
	boolean musicOn;
	boolean soundOn;
	public AdjustSoundScreen(Game game) {
		super(game);
		g = game.getGraphics();
		message = "";
		showText = false;
		musicOn = Settings.playMusic;
		soundOn = Settings.playSound;
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		
		int len = touchEvents.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_DOWN || event.type == TouchEvent.TOUCH_DRAGGED){
				showText = false;
				if(event.x > 160 && event.x < 160+45 && event.y > 160+60 && event.y < 160+60+60){
					musicOn = true;
					return;
				} else if(event.x > 160+45 && event.x < 160+130 && event.y > 160+60 && event.y < 160+60+60){
					musicOn = false;
					return;
				}
				if(event.x > 160 && event.x < 160+45 && event.y > 320+60 && event.y < 320+60+60){
					soundOn = true;
					return;
				} else if(event.x > 160+45 && event.x < 160+130 && event.y > 320+60 && event.y < 320+60+60){
					soundOn = false;
					return;
				}
			}
			if(event.type == TouchEvent.TOUCH_UP){
				if(event.y < 64){
					if(event.x < 64){
						handleSound(0);
						game.setScreen(new SettingsScreen(game));
					} else if(event.x < 190 ){
						handleSound(0);
						musicOn = true;
						soundOn = true;
					} else if(event.x < 310){
						handleSound(0);
						Settings.playMusic = musicOn;
						Settings.playSound = soundOn;
						Settings.save(game);
						showText = true;
						message = "Sound Settings Saved!";
					}
				}
				if(event.x > 160 && event.x < 160+45 && event.y > 160+60 && event.y < 160+60+60){
					handleSound(0);
				} else if(event.x > 160+45 && event.x < 160+130 && event.y > 160+60 && event.y < 160+60+60){
					handleSound(0);
				}
				if(event.x > 160 && event.x < 160+45 && event.y > 320+60 && event.y < 320+60+60){
					handleSound(0);
				} else if(event.x > 160+45 && event.x < 160+130 && event.y > 320+60 && event.y < 320+60+60){
					handleSound(0);
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		g.drawPixmap(Assets.whiteness, 0, 0);
		g.drawPixmap(Assets.back, 10, 10);
		g.drawPixmap(Assets.defaults, 130, 10);
		g.drawPixmap(Assets.save, 250, 10);
		g.drawPixmap(Assets.musicLbl, 10, 160);
		g.drawPixmap(Assets.soundLbl, 10, 320);
		g.drawPixmap(Assets.onOff, 160, 160+60);
		g.drawPixmap(Assets.onOff, 160, 320+60);
		
		if(showText){
			g.drawText(message, g.getWidth()/2, 64, 12, "emulogic", Color.BLACK);
		}
		if(musicOn){
			g.drawPixmap(Assets.on, 160, 160+60);
		} else{
			g.drawPixmap(Assets.off, 160+45, 160+60);
		}
		if(soundOn){
			g.drawPixmap(Assets.on, 160, 320+60);
		} else{
			g.drawPixmap(Assets.off, 160+45, 320+60);
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
		
	}
}
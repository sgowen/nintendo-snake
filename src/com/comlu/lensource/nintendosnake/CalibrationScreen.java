package com.comlu.lensource.nintendosnake;

import java.util.List;

import android.graphics.Color;

import com.comlu.lensource.framework.Game;
import com.comlu.lensource.framework.Graphics;
import com.comlu.lensource.framework.Screen;
import com.comlu.lensource.framework.Input.TouchEvent;

public class CalibrationScreen extends Screen{
	int x;
	int y;
	String message;
	boolean showText;
	Game game;
	
	public CalibrationScreen(Game game) {
		super(game);
		this.game = game;
		x = Settings.xCal;
		y = Settings.yCal;
		message = "";
		showText = false;
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		if(len > 0){
			for(int i = 0; i < len; i++){
				TouchEvent event = touchEvents.get(i);
				if(event.type == TouchEvent.TOUCH_DOWN || event.type == TouchEvent.TOUCH_DRAGGED){
					showText = false;
					if(event.y > 64){
						x = event.x;
						y = event.y;
					}
				}
				if(event.type == TouchEvent.TOUCH_UP){
					if(event.y < 64){
						if(event.x < 70){
							handleSound(0);
							game.setScreen(new SettingsScreen(game));
						} else if(event.x < 190 ){
							handleSound(0);
							Settings.xCal = 160;
							Settings.yCal = 400;
							x = Settings.xCal;
							y = Settings.yCal;
						} else if(event.x < 310){
							handleSound(0);
							showText = true;
							Settings.xCal = x;
							Settings.yCal = y;
							Settings.save(game);
							message = "Calibration Saved!";
						}
					}
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		
		g.drawPixmap(Assets.whiteness, 0, 0);
		g.drawPixmap(Assets.back, 10, 10);
		g.drawPixmap(Assets.defaults, 130, 10);
		g.drawPixmap(Assets.save, 250, 10);
		g.drawPixmap(Assets.arrowLeft, x-64, y-32);
		g.drawPixmap(Assets.arrowRight, x, y-32);
		g.drawPixmap(Assets.arrowUp, x-32, y-64);
		g.drawPixmap(Assets.arrowDown, x-32, y);
		g.drawLine(0, y, 320, y, Color.DKGRAY);
		g.drawLine(x, 0, x, 480, Color.DKGRAY);
		if(showText){
			g.drawText(message, g.getWidth()/2, 64, 12, "emulogic", Color.BLACK);
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
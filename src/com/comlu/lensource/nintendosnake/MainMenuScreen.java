package com.comlu.lensource.nintendosnake;

import java.util.List;

import android.content.Intent;
import android.net.Uri;

import com.comlu.lensource.framework.Game;
import com.comlu.lensource.framework.Graphics;
import com.comlu.lensource.framework.Input.TouchEvent;
import com.comlu.lensource.framework.Screen;

public class MainMenuScreen extends Screen{
	Game game;
	Graphics g;
	boolean[] highlight = {false, false, false};
	
	public MainMenuScreen(Game game) {
		super(game);
		this.game = game;
		Settings.load(game);
		g = game.getGraphics();
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_DOWN || event.type == TouchEvent.TOUCH_DRAGGED){
				if(inBounds(event, 40+15, 230+15, 210, 35)){
					highlight[0] = true;
					highlight[1] = false;
					highlight[2] = false;
				} else{
					highlight[0] = false;
				}
				if(inBounds(event, 40+15, 230+35, 210, 65)){
					highlight[1] = true;
					highlight[0] = false;
					highlight[2] = false;
				} else{
					highlight[1] = false;
				}
				if(inBounds(event, 40+15, 230+35+70, 210, 45)){
					highlight[2] = true;
					highlight[0] = false;
					highlight[1] = false;
				} else{
					highlight[2] = false;
				}
			}
			if(event.type == TouchEvent.TOUCH_UP){
				if(inBounds(event, 40+15, 230+15, 210, 35)){
					game.setScreen(new ChooseThemeScreen(game));
					handleSound(0);
					return;
				}
				if(inBounds(event, 40+15, 230+15+35, 210, 65)){
					game.setScreen(new HighscoreScreen(game));
					handleSound(0);
					return;
				}
				if(inBounds(event, 40+15, 230+15+35+65, 210, 45)){
					handleSound(0);
					Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://lensource.comlu.com/"));
					game.getActivity().startActivity(intent);
					return;
				}
				if(inBounds(event, 280, 440, 40, 40)){
					game.setScreen(new SettingsScreen(game));
					handleSound(0);
				}
			}
		}
	}
	
	private boolean inBounds(TouchEvent event, int x, int y, int width, int height){
		if(event.x > x && event.x < x + width -1 && event.y > y && event.y < y + height -1){
			return true;
		} else{
			return false;
		}
	}

	@Override
	public void present(float deltaTime) {
		g.drawPixmap(Assets.background, 0, 0);
		g.drawPixmap(Assets.logo, 10, 10);
		g.drawPixmap(Assets.mainMenu, 40, 230);
		g.drawPixmap(Assets.settingsButton, 280, 440);
		if(highlight[0] == true){
			g.drawPixmap(Assets.highlight1, 40+15, 230+15);
		} else if(highlight[1] == true){
			g.drawPixmap(Assets.highlight2, 40+15, 230+15+35);
		} else if(highlight[2] == true){
			g.drawPixmap(Assets.highlight3, 40+15, 230+15+35+65);
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

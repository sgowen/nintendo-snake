package com.comlu.lensource.nintendosnake;

import java.util.List;

import com.comlu.lensource.framework.Game;
import com.comlu.lensource.framework.Graphics;
import com.comlu.lensource.framework.Screen;
import com.comlu.lensource.framework.Input.TouchEvent;

public class ChooseThemeScreen extends Screen{
	Graphics g;
	final int mario = 0;
	final int zelda = 1;
	final int metroid = 2;
	
	public ChooseThemeScreen(Game game) {
		super(game);
		g = game.getGraphics();
		g.drawPixmap(Assets.chooseThemeScreen, 0, 0);
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP){
				if(event.y > 8 && event.y < 158 && event.x > 40 && event.x < 280){
					handleSound(0);
					Settings.setSettingAt(mario, true);
					Settings.setSettingAt(zelda, false);
					Settings.setSettingAt(metroid, false);
					game.setScreen(new GameScreen(game));
					return;
				}
				if(event.y > 166 && event.y < 323 && event.x > 40 && event.x < 280){
					handleSound(0);
					Settings.setSettingAt(mario, false);
					Settings.setSettingAt(zelda, true);
					Settings.setSettingAt(metroid, false);
					game.setScreen(new GameScreen(game));
					return;
				}
				if(event.y > 323 && event.y < 472 && event.x > 40 && event.x < 280){
					handleSound(0);
					Settings.setSettingAt(mario, false);
					Settings.setSettingAt(zelda, false);
					Settings.setSettingAt(metroid, true);
					game.setScreen(new GameScreen(game));
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
		try {
			finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
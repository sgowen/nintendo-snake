package com.comlu.lensource.nintendosnake;

import java.util.List;

import android.graphics.Color;

import com.comlu.lensource.framework.Game;
import com.comlu.lensource.framework.Graphics;
import com.comlu.lensource.framework.Input.KeyEvent;
import com.comlu.lensource.framework.Screen;
import com.comlu.lensource.framework.Input.TouchEvent;

public class KeyConfigScreen extends Screen{
	enum KeyState{ processKeyUp, processKeyLeft, processKeyDown, processKeyRight, processKeyPause, processTouch };
	KeyState keyState;
	Graphics g;
	String message;
	int key_up;
	int key_left;
	int key_down;
	int key_right;
	String key_up_display;
	String key_left_display;
	String key_down_display;
	String key_right_display;
	boolean showText;
	public KeyConfigScreen(Game game) {
		super(game);
		g = game.getGraphics();
		keyState = KeyState.processTouch;
		message = "";
		key_up = Settings.KEY_UP;
		key_left = Settings.KEY_LEFT;
		key_down = Settings.KEY_DOWN;
		key_right = Settings.KEY_RIGHT;
		
		key_up_display = Settings.tempKeyUp;
		key_left_display = Settings.tempKeyLeft;
		key_down_display = Settings.tempKeyDown;
		key_right_display = Settings.tempKeyRight;
		showText = false;
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		List<KeyEvent> keyEvents = game.getInput().getKeyEvents();
		
		key_up_display = key_up_display.equals("19") ? "D-PAD UP" : key_up_display;
		key_down_display = key_down_display.equals("20") ? "D-PAD DOWN" : key_down_display;
		key_left_display = key_left_display.equals("21") ? "D-PAD LEFT" : key_left_display;
		key_right_display = key_right_display.equals("22") ? "D-PAD RIGHT" : key_right_display;
		
		if(keyState == KeyState.processTouch){
			updateTouch(touchEvents);
		} else if(keyState == KeyState.processKeyUp){
			key_up_display = "";
			processKeyPress(keyEvents, 0);
		} else if(keyState == KeyState.processKeyLeft){
			key_left_display = "";
			processKeyPress(keyEvents, 1);
		} else if(keyState == KeyState.processKeyDown){
			key_down_display = "";
			processKeyPress(keyEvents, 2);
		} else if(keyState == KeyState.processKeyRight){
			key_right_display = "";
			processKeyPress(keyEvents, 3);
		}
	}

	private void updateTouch(List<TouchEvent> touchEvents){
		int len = touchEvents.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(showText == true){
				if(event.type == TouchEvent.TOUCH_DOWN){
					showText = false;
					return;
				}
			}
			if(event.type == TouchEvent.TOUCH_UP){
				if(event.y < 64){
					if(event.x < 64){
						game.setScreen(new SettingsScreen(game));
						handleSound(0);
						return;
					} else if(event.x < 190 ){
						key_up = android.view.KeyEvent.KEYCODE_DPAD_UP;
						key_left = android.view.KeyEvent.KEYCODE_DPAD_LEFT;
						key_down = android.view.KeyEvent.KEYCODE_DPAD_DOWN;
						key_right = android.view.KeyEvent.KEYCODE_DPAD_RIGHT;
						key_up_display = "D-PAD UP";
						key_left_display = "D-PAD LEFT";
						key_down_display = "D-PAD DOWN";
						key_right_display = "D-PAD RIGHT";
						handleSound(0);
						return;
					} else if(event.x < 310){
						handleSound(0);
						showText = true;
						if(isValid()){
							Settings.KEY_UP = key_up;
							Settings.KEY_LEFT = key_left;
							Settings.KEY_DOWN = key_down;
							Settings.KEY_RIGHT = key_right;
							Settings.tempKeyUp = key_up_display;
							Settings.tempKeyLeft = key_left_display;
							Settings.tempKeyDown = key_down_display;
							Settings.tempKeyRight = key_right_display;
							Settings.save(game);
							return;
						}
					}
				}
				if(event.y > 240-128 && event.y < 240-64 && event.x > 160-32 && event.x < 160+32){
					keyState = KeyState.processKeyUp;
					showText = true;
					message = "Press Key to Configure 'UP'";
					handleSound(0);
					return;
				} else if(event.y > 240-32 && event.y < 240+32 && event.x > 160-128 && event.x < 160-64){
					keyState = KeyState.processKeyLeft;
					showText = true;
					message = "Press Key to Configure 'LEFT'";
					handleSound(0);
					return;
				} else if(event.y > 240+64 && event.y < 240+64+64 && event.x > 160-32 && event.x < 160+32){
					keyState = KeyState.processKeyDown;
					showText = true;
					message = "Press Key to Configure 'DOWN'";
					handleSound(0);
					return;
				} else if(event.y > 240-32 && event.y < 240+32 && event.x > 160+64 && event.x < 160+128){
					keyState = KeyState.processKeyRight;
					showText = true;
					message = "Press Key to Configure 'RIGHT'";
					handleSound(0);
					return;
				}
			}
		}
	}
	
	private void processKeyPress(List<KeyEvent> keyEvents, int key){
		int lenk = keyEvents.size();
		for(int j = 0; j < lenk; j++){
			KeyEvent eventK = keyEvents.get(j);
			if(eventK.type == KeyEvent.KEY_DOWN){
				if(eventK.keyCode != android.view.KeyEvent.KEYCODE_BACK 
						&& eventK.keyCode != android.view.KeyEvent.KEYCODE_MENU 
						&& eventK.keyCode != android.view.KeyEvent.KEYCODE_SEARCH){
					
					showText = false;
					keyState = KeyState.processTouch;
					handleSound(0);
					switch(key){
					case 0:
						key_up = eventK.keyCode;
						if(eventK.keyCode == android.view.KeyEvent.KEYCODE_DPAD_UP){
							key_up_display += "D-PAD UP";
							return;
						} else if(eventK.keyCode == android.view.KeyEvent.KEYCODE_DPAD_LEFT){
							key_up_display += "D-PAD LEFT";
							return;
						} else if(eventK.keyCode == android.view.KeyEvent.KEYCODE_DPAD_DOWN){
							key_up_display += "D-PAD DOWN";
							return;
						} else if(eventK.keyCode == android.view.KeyEvent.KEYCODE_DPAD_RIGHT){
							key_up_display += "D-PAD RIGHT";
							return;
						} else if(eventK.keyCode == android.view.KeyEvent.KEYCODE_DPAD_CENTER){
							key_up_display += "D-PAD CENTER";
							return;
						} else if(eventK.keyCode >= android.view.KeyEvent.KEYCODE_A && eventK.keyCode <= android.view.KeyEvent.KEYCODE_Z){
							key_up_display += eventK.keyChar;
							return;
						} else if(eventK.keyCode >= android.view.KeyEvent.KEYCODE_0 && eventK.keyCode <= android.view.KeyEvent.KEYCODE_9){
							key_up_display += eventK.keyChar;
							return;
						} else{
							key_up_display += "Invalid Key";
							return;
						}
					case 1:
						key_left = eventK.keyCode;
						if(eventK.keyCode == android.view.KeyEvent.KEYCODE_DPAD_UP){
							key_left_display += "D-PAD UP";
							return;
						} else if(eventK.keyCode == android.view.KeyEvent.KEYCODE_DPAD_LEFT){
							key_left_display += "D-PAD LEFT";
							return;
						} else if(eventK.keyCode == android.view.KeyEvent.KEYCODE_DPAD_DOWN){
							key_left_display += "D-PAD DOWN";
							return;
						} else if(eventK.keyCode == android.view.KeyEvent.KEYCODE_DPAD_RIGHT){
							key_left_display += "D-PAD RIGHT";
							return;
						} else if(eventK.keyCode == android.view.KeyEvent.KEYCODE_DPAD_CENTER){
							key_left_display += "D-PAD CENTER";
							return;
						} else if(eventK.keyCode >= android.view.KeyEvent.KEYCODE_A && eventK.keyCode <= android.view.KeyEvent.KEYCODE_Z){
							key_left_display += eventK.keyChar;
							return;
						} else if(eventK.keyCode >= android.view.KeyEvent.KEYCODE_0 && eventK.keyCode <= android.view.KeyEvent.KEYCODE_9){
							key_left_display += eventK.keyChar;
							return;
						} else{
							key_left_display += "Invalid Key";
							return;
						}
					case 2:
						key_down = eventK.keyCode;
						if(eventK.keyCode == android.view.KeyEvent.KEYCODE_DPAD_UP){
							key_down_display += "D-PAD UP";
							return;
						} else if(eventK.keyCode == android.view.KeyEvent.KEYCODE_DPAD_LEFT){
							key_down_display += "D-PAD LEFT";
							return;
						} else if(eventK.keyCode == android.view.KeyEvent.KEYCODE_DPAD_DOWN){
							key_down_display += "D-PAD DOWN";
							return;
						} else if(eventK.keyCode == android.view.KeyEvent.KEYCODE_DPAD_RIGHT){
							key_down_display += "D-PAD RIGHT";
							return;
						} else if(eventK.keyCode == android.view.KeyEvent.KEYCODE_DPAD_CENTER){
							key_down_display += "D-PAD CENTER";
							return;
						} else if(eventK.keyCode >= android.view.KeyEvent.KEYCODE_A && eventK.keyCode <= android.view.KeyEvent.KEYCODE_Z){
							key_down_display += eventK.keyChar;
							return;
						} else if(eventK.keyCode >= android.view.KeyEvent.KEYCODE_0 && eventK.keyCode <= android.view.KeyEvent.KEYCODE_9){
							key_down_display += eventK.keyChar;
							return;
						} else{
							key_down_display += "Invalid Key";
							return;
						}
					case 3:
						key_right = eventK.keyCode;
						if(eventK.keyCode == android.view.KeyEvent.KEYCODE_DPAD_UP){
							key_right_display += "D-PAD UP";
							return;
						} else if(eventK.keyCode == android.view.KeyEvent.KEYCODE_DPAD_LEFT){
							key_right_display += "D-PAD LEFT";
							return;
						} else if(eventK.keyCode == android.view.KeyEvent.KEYCODE_DPAD_DOWN){
							key_right_display += "D-PAD DOWN";
							return;
						} else if(eventK.keyCode == android.view.KeyEvent.KEYCODE_DPAD_RIGHT){
							key_right_display += "D-PAD RIGHT";
							return;
						} else if(eventK.keyCode == android.view.KeyEvent.KEYCODE_DPAD_CENTER){
							key_right_display += "D-PAD CENTER";
							return;
						} else if(eventK.keyCode >= android.view.KeyEvent.KEYCODE_A && eventK.keyCode <= android.view.KeyEvent.KEYCODE_Z){
							key_right_display += eventK.keyChar;
							return;
						} else if(eventK.keyCode >= android.view.KeyEvent.KEYCODE_0 && eventK.keyCode <= android.view.KeyEvent.KEYCODE_9){
							key_right_display += eventK.keyChar;
							return;
						} else{
							key_right_display += "Invalid Key";
							return;
						}
					}
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
		
		g.drawPixmap(Assets.arrowUp, 160-32, 240-128);
		g.drawPixmap(Assets.arrowLeft, 160-128, 240-32);
		g.drawPixmap(Assets.arrowDown, 160-32, 240+64);
		g.drawPixmap(Assets.arrowRight, 160+64, 240-32);
		
		g.drawText(key_up_display, 160, 240-64+16, 11, "", Color.BLACK);
		g.drawText(key_left_display, 160-64-32, 240-32+64+16, 11, "", Color.BLACK);
		g.drawText(key_down_display, 160, 240+64+64+16, 11, "", Color.BLACK);
		g.drawText(key_right_display, 160+64+32, 240-32+64+16, 11, "", Color.BLACK);
		
		if(showText){
			g.drawText(message, g.getWidth()/2, 64, 10, "emulogic", Color.BLACK);
		}
	}
	
	private boolean isValid(){
		if(key_up_display.startsWith("Invalid", 0) 
				|| key_left_display.startsWith("Invalid", 0) 
				|| key_down_display.startsWith("Invalid", 0) 
				|| key_right_display.startsWith("Invalid", 0)){
			message = "Cannot Save Invalid Keys!";
			return false;
		}
		if(key_up_display.equals(key_left_display) 
				|| key_up_display.equals(key_down_display) 
				|| key_up_display.equals(key_right_display)
				|| key_left_display.equals(key_down_display)
				|| key_left_display.equals(key_right_display)
				|| key_down_display.equals(key_right_display)){
			message = "Cannot Save Duplicate Keys!";
			return false;
		}
		message = "Keyboard Configuration Saved!";
		return true;
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
package com.comlu.lensource.framework.imp;

import java.util.List;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;

import com.comlu.lensource.framework.Input;

public class AndroidInput implements Input{
	KeyboardHandler keyHandler;
	TouchHandler touchHandler;
	
	public AndroidInput(Context context, View view, float scaleX, float scaleY){
		keyHandler = new KeyboardHandler(view);
		touchHandler = (Integer.parseInt(VERSION.SDK) < 5) ? new SingleTouchHandler(view, scaleX, scaleY) : new MultiTouchHandler(view, scaleX, scaleY);
	}
	@Override
	public boolean isKeyPressed(int keyCode) {
		return keyHandler.isKeyPressed(keyCode);
	}

	@Override
	public boolean isTouchDown(int pointer) {
		return touchHandler.isTouchDown(pointer);
	}

	@Override
	public int getTouchX(int pointer) {
		return touchHandler.getTouchX(pointer);
	}

	@Override
	public int getTouchY(int pointer) {
		return touchHandler.getTouchY(pointer);
	}

	@Override
	public List<KeyEvent> getKeyEvents() {
		return keyHandler.getKeyEvents();
	}

	@Override
	public List<TouchEvent> getTouchEvents() {
		return touchHandler.getTouchEvents();
	}

}

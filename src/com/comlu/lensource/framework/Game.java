package com.comlu.lensource.framework;

import android.app.Activity;
import android.content.SharedPreferences;

public interface Game {
	public Input getInput();
	
	public Graphics getGraphics();
	
	public Audio getAudio();
	
	public void setScreen(Screen screen);
	
	public Screen getCurrentScreen();
	
	public Screen getStartScreen();
	
	public Activity getActivity();
	
	public SharedPreferences getSaveData();
	
	public SharedPreferences.Editor getSaveDataEditor();
}

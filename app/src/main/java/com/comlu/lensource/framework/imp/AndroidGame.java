package com.comlu.lensource.framework.imp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.comlu.lensource.framework.Audio;
import com.comlu.lensource.framework.Game;
import com.comlu.lensource.framework.Graphics;
import com.comlu.lensource.framework.Input;
import com.comlu.lensource.framework.Screen;

public abstract class AndroidGame extends Activity implements Game{
	public static final String SAVE_DATA = "NintendoSnakeData";
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	Activity activity;
	AndroidFastRenderView renderView;
	Graphics graphics;
	Bitmap frameBuffer;
	Audio audio;
	Input input;
	Screen screen;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
		int frameBufferWidth = isLandscape ? 480 : 320;
		int frameBufferHeight = isLandscape ? 320 : 480;
		frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight, Config.RGB_565);
		float scaleX = (float) frameBufferWidth / getWindowManager().getDefaultDisplay().getWidth();
		float scaleY = (float) frameBufferHeight / getWindowManager().getDefaultDisplay().getHeight();
		
		settings = getSharedPreferences(SAVE_DATA, 0);
		editor = settings.edit();
		activity = this;
		renderView = new AndroidFastRenderView(this, frameBuffer);
		graphics = new AndroidGraphics(getAssets(), frameBuffer);
		audio = new AndroidAudio(this);
		input = new AndroidInput(this, renderView, scaleX, scaleY);
		screen = getStartScreen();
		setContentView(renderView);
	}
	
	public void onResume(){
		super.onResume();
		screen.resume();
		renderView.resume();
	}
	
	public void onPause(){
		super.onPause();
		screen.pause();
		renderView.pause();
		if(isFinishing()){
			screen.dispose();
		}
	}
	
	public Input getInput(){
		return input;
	}
	
	public Graphics getGraphics(){
		return graphics;
	}
	
	public Audio getAudio(){
		return audio;
	}
	
	public void setScreen(Screen screen){
		if(screen == null){
			throw new IllegalArgumentException("Screen must not be null");
		}
		
		this.screen.pause();
		this.screen.dispose();
		screen.resume();
		screen.update(0);
		this.screen = screen;
	}
	
	public Screen getCurrentScreen(){
		return screen;
	}
	
	public Activity getActivity(){
		return activity;
	}
	
	public SharedPreferences getSaveData(){
		return settings;
	}
	
	public SharedPreferences.Editor getSaveDataEditor(){
		return editor;
	}
}

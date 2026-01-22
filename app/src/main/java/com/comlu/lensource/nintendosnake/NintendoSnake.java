package com.comlu.lensource.nintendosnake;

import com.comlu.lensource.framework.Screen;
import com.comlu.lensource.framework.imp.AndroidGame;

public class NintendoSnake extends AndroidGame{
	@Override
	public Screen getStartScreen() {
		return new LoadingScreen(this);
	}
}
package com.comlu.lensource.nintendosnake;

import java.io.File;

import com.comlu.lensource.framework.Game;
import com.comlu.lensource.framework.Graphics;
import com.comlu.lensource.framework.Graphics.PixmapFormat;
import com.comlu.lensource.framework.Screen;

public class LoadingScreen extends Screen{
	Game game;
	public LoadingScreen(Game game){
		super(game);
		this.game = game;
	}
	
	public void update(float deltaTime){
		Graphics g = game.getGraphics();
		
		Assets.background = g.newPixmap("gui" + File.separator + "all" + File.separator + "background.png", PixmapFormat.RGB565);
		Assets.blackness = g.newPixmap("gui" + File.separator + "all" + File.separator + "black.png", PixmapFormat.RGB565);
		Assets.whiteness = g.newPixmap("gui" + File.separator + "all" + File.separator + "white.png", PixmapFormat.RGB565);
		Assets.chooseThemeScreen = g.newPixmap("gui" + File.separator + "all" + File.separator + "ctscreen.png", PixmapFormat.RGB565);
		Assets.mariobg = g.newPixmap("gui" + File.separator + "1" + File.separator + "mariobg.png", PixmapFormat.RGB565);
		Assets.zeldabg = g.newPixmap("gui" + File.separator + "2" + File.separator + "zeldabg.png", PixmapFormat.RGB565);
		Assets.metroidbg = g.newPixmap("gui" + File.separator + "3" + File.separator + "metroidbg.png", PixmapFormat.RGB565);
		Assets.calibrateTouch = g.newPixmap("gui" + File.separator + "all" + File.separator + "cali.png", PixmapFormat.ARGB4444);
		Assets.configKeyboard = g.newPixmap("gui" + File.separator + "all" + File.separator + "config.png", PixmapFormat.ARGB4444);
		Assets.sound = g.newPixmap("gui" + File.separator + "all" + File.separator + "soundlbl.png", PixmapFormat.ARGB4444);
		Assets.score = g.newPixmap("gui" + File.separator + "all" + File.separator + "score.png", PixmapFormat.ARGB4444);
		Assets.bonus = g.newPixmap("gui" + File.separator + "all" + File.separator + "bonus.png", PixmapFormat.ARGB4444);
		Assets.back = g.newPixmap("gui" + File.separator + "all" + File.separator + "back.png", PixmapFormat.ARGB4444);
		Assets.defaults = g.newPixmap("gui" + File.separator + "all" + File.separator + "defaults.png", PixmapFormat.ARGB4444);
		Assets.save = g.newPixmap("gui" + File.separator + "all" + File.separator + "save.png", PixmapFormat.ARGB4444);
		Assets.soundLbl = g.newPixmap("gui" + File.separator + "all" + File.separator + "sound.png", PixmapFormat.ARGB4444);
		Assets.musicLbl = g.newPixmap("gui" + File.separator + "all" + File.separator + "music.png", PixmapFormat.ARGB4444);
		Assets.onOff = g.newPixmap("gui" + File.separator + "all" + File.separator + "onoff.png", PixmapFormat.ARGB4444);
		Assets.on = g.newPixmap("gui" + File.separator + "all" + File.separator + "1.png", PixmapFormat.ARGB4444);
		Assets.off = g.newPixmap("gui" + File.separator + "all" + File.separator + "0.png", PixmapFormat.ARGB4444);
		Assets.logo = g.newPixmap("gui" + File.separator + "all" + File.separator + "logo.png", PixmapFormat.ARGB4444);
		Assets.mainMenu = g.newPixmap("gui" + File.separator + "all" + File.separator + "mainmenu.png", PixmapFormat.ARGB4444);
		Assets.settingsButton = g.newPixmap("gui" + File.separator + "all" + File.separator + "settingsbutton.png", PixmapFormat.ARGB4444);
		Assets.highlight1 = g.newPixmap("gui" + File.separator + "all" + File.separator + "hl1.png", PixmapFormat.ARGB4444);
		Assets.highlight2 = g.newPixmap("gui" + File.separator + "all" + File.separator + "hl2.png", PixmapFormat.ARGB4444);
		Assets.highlight3 = g.newPixmap("gui" + File.separator + "all" + File.separator + "hl3.png", PixmapFormat.ARGB4444);
		Assets.highscores = g.newPixmap("gui" + File.separator + "all" + File.separator + "highscores.png", PixmapFormat.ARGB4444);
		Assets.newHighScore = g.newPixmap("gui" + File.separator + "all" + File.separator + "newhs.png", PixmapFormat.ARGB4444);
		Assets.pause1 = g.newPixmap("gui" + File.separator + "1" + File.separator + "pause1.png", PixmapFormat.ARGB4444);
		Assets.pause2 = g.newPixmap("gui" + File.separator + "2" + File.separator + "pause2.png", PixmapFormat.ARGB4444);
		Assets.pause3 = g.newPixmap("gui" + File.separator + "3" + File.separator + "pause3.png", PixmapFormat.ARGB4444);
		Assets.numbers0 = g.newPixmap("gui" + File.separator + "all" + File.separator + "num0.png", PixmapFormat.ARGB4444);
		Assets.numbers1 = g.newPixmap("gui" + File.separator + "1" + File.separator + "num1.png", PixmapFormat.ARGB4444);
		Assets.numbers2 = g.newPixmap("gui" + File.separator + "2" + File.separator + "num2.png", PixmapFormat.ARGB4444);
		Assets.numbers3 = g.newPixmap("gui" + File.separator + "3" + File.separator + "num3.png", PixmapFormat.ARGB4444);
		Assets.ready1 = g.newPixmap("gui" + File.separator + "1" + File.separator + "ready1.png", PixmapFormat.ARGB4444);
		Assets.ready2 = g.newPixmap("gui" + File.separator + "2" + File.separator + "ready2.png", PixmapFormat.ARGB4444);
		Assets.ready3 = g.newPixmap("gui" + File.separator + "3" + File.separator + "ready3.png", PixmapFormat.ARGB4444);
		Assets.pauseMenu1 = g.newPixmap("gui" + File.separator + "1" + File.separator + "pausemenu1.png", PixmapFormat.ARGB4444);
		Assets.pauseMenu2 = g.newPixmap("gui" + File.separator + "2" + File.separator + "pausemenu2.png", PixmapFormat.ARGB4444);
		Assets.pauseMenu3 = g.newPixmap("gui" + File.separator + "3" + File.separator + "pausemenu3.png", PixmapFormat.ARGB4444);
		Assets.gameOver = g.newPixmap("gui" + File.separator + "all" + File.separator + "gameover.png", PixmapFormat.ARGB4444);
		Assets.gameOver1 = g.newPixmap("gui" + File.separator + "1" + File.separator + "gameover1.png", PixmapFormat.ARGB4444);
		Assets.gameOver2 = g.newPixmap("gui" + File.separator + "2" + File.separator + "gameover2.png", PixmapFormat.ARGB4444);
		Assets.gameOver3 = g.newPixmap("gui" + File.separator + "3" + File.separator + "gameover3.png", PixmapFormat.ARGB4444);
		Assets.submit1 = g.newPixmap("gui" + File.separator + "1" + File.separator + "submit1.png", PixmapFormat.ARGB4444);
		Assets.submit2 = g.newPixmap("gui" + File.separator + "2" + File.separator + "submit2.png", PixmapFormat.ARGB4444);
		Assets.submit3 = g.newPixmap("gui" + File.separator + "3" + File.separator + "submit3.png", PixmapFormat.ARGB4444);
		
		Assets.headUp1 = g.newPixmap("headparts" + File.separator + "headup1.png", PixmapFormat.ARGB4444);
		Assets.headLeft1 = g.newPixmap("headparts" + File.separator + "headleft1.png", PixmapFormat.ARGB4444);
		Assets.headDown1 = g.newPixmap("headparts" + File.separator + "headdown1.png", PixmapFormat.ARGB4444);
		Assets.headRight1 = g.newPixmap("headparts" + File.separator + "headright1.png", PixmapFormat.ARGB4444);
		Assets.headUp2 = g.newPixmap("headparts" + File.separator + "headup2.png", PixmapFormat.ARGB4444);
		Assets.headLeft2 = g.newPixmap("headparts" + File.separator + "headleft2.png", PixmapFormat.ARGB4444);
		Assets.headDown2 = g.newPixmap("headparts" + File.separator + "headdown2.png", PixmapFormat.ARGB4444);
		Assets.headRight2 = g.newPixmap("headparts" + File.separator + "headright2.png", PixmapFormat.ARGB4444);
		Assets.headUp3 = g.newPixmap("headparts" + File.separator + "headup3.png", PixmapFormat.ARGB4444);
		Assets.headLeft3 = g.newPixmap("headparts" + File.separator + "headleft3.png", PixmapFormat.ARGB4444);
		Assets.headDown3 = g.newPixmap("headparts" + File.separator + "headdown3.png", PixmapFormat.ARGB4444);
		Assets.headRight3 = g.newPixmap("headparts" + File.separator + "headright3.png", PixmapFormat.ARGB4444);
		
		Assets.headUp3a = g.newPixmap("headparts" + File.separator + "headup3a.png", PixmapFormat.ARGB4444);
		Assets.headLeft3a = g.newPixmap("headparts" + File.separator + "headleft3a.png", PixmapFormat.ARGB4444);
		Assets.headDown3a = g.newPixmap("headparts" + File.separator + "headdown3a.png", PixmapFormat.ARGB4444);
		Assets.headRight3a = g.newPixmap("headparts" + File.separator + "headright3a.png", PixmapFormat.ARGB4444);
		
		Assets.headUp3b = g.newPixmap("headparts" + File.separator + "headup3b.png", PixmapFormat.ARGB4444);
		Assets.headLeft3b = g.newPixmap("headparts" + File.separator + "headleft3b.png", PixmapFormat.ARGB4444);
		Assets.headDown3b = g.newPixmap("headparts" + File.separator + "headdown3b.png", PixmapFormat.ARGB4444);
		Assets.headRight3b = g.newPixmap("headparts" + File.separator + "headright3b.png", PixmapFormat.ARGB4444);
		
		Assets.marioTailNes = g.newPixmap("tailparts" + File.separator + "marioTailNes.png", PixmapFormat.ARGB4444);
		Assets.linkTailNes = g.newPixmap("tailparts" + File.separator + "linkTailNes.png", PixmapFormat.ARGB4444);
		Assets.samusTailNes = g.newPixmap("tailparts" + File.separator + "samusTailNes.png", PixmapFormat.ARGB4444);
		Assets.marioTailSnes = g.newPixmap("tailparts" + File.separator + "marioTailSnes.png", PixmapFormat.ARGB4444);
		Assets.linkTailSnes = g.newPixmap("tailparts" + File.separator + "linkTailSnes.png", PixmapFormat.ARGB4444);
		Assets.samusTailSnes = g.newPixmap("tailparts" + File.separator + "samusTailSnes.png", PixmapFormat.ARGB4444);
		Assets.marioTailN64 = g.newPixmap("tailparts" + File.separator + "marioTailN64.png", PixmapFormat.ARGB4444);
		Assets.linkTailN64 = g.newPixmap("tailparts" + File.separator + "linkTailN64.png", PixmapFormat.ARGB4444);
		Assets.samusTailN64 = g.newPixmap("tailparts" + File.separator + "samusTailN64.png", PixmapFormat.ARGB4444);
		Assets.marioTailGC = g.newPixmap("tailparts" + File.separator + "marioTailGC.png", PixmapFormat.ARGB4444);
		Assets.linkTailGC = g.newPixmap("tailparts" + File.separator + "linkTailGC.png", PixmapFormat.ARGB4444);
		Assets.samusTailGC = g.newPixmap("tailparts" + File.separator + "samusTailGC.png", PixmapFormat.ARGB4444);
		Assets.marioTailWii = g.newPixmap("tailparts" + File.separator + "marioTailWii.png", PixmapFormat.ARGB4444);
		Assets.linkTailWii = g.newPixmap("tailparts" + File.separator + "linkTailWii.png", PixmapFormat.ARGB4444);
		Assets.samusTailWii = g.newPixmap("tailparts" + File.separator + "samusTailWii.png", PixmapFormat.ARGB4444);
		
		Assets.item1 = g.newPixmap("items" + File.separator + "item1.png", PixmapFormat.ARGB4444);
		Assets.item2 = g.newPixmap("items" + File.separator + "item2.png", PixmapFormat.ARGB4444);
		Assets.item3 = g.newPixmap("items" + File.separator + "item3.png", PixmapFormat.ARGB4444);
		Assets.item4 = g.newPixmap("items" + File.separator + "item4.png", PixmapFormat.ARGB4444);
		Assets.item5 = g.newPixmap("items" + File.separator + "item5.png", PixmapFormat.ARGB4444);
		Assets.item6 = g.newPixmap("items" + File.separator + "item6.png", PixmapFormat.ARGB4444);
		
		Assets.arrowUp = g.newPixmap("gui" + File.separator + "all" + File.separator + "arrowup.png", PixmapFormat.ARGB4444);
		Assets.arrowLeft = g.newPixmap("gui" + File.separator + "all" + File.separator + "arrowleft.png", PixmapFormat.ARGB4444);
		Assets.arrowDown = g.newPixmap("gui" + File.separator + "all" + File.separator + "arrowdown.png", PixmapFormat.ARGB4444);
		Assets.arrowRight = g.newPixmap("gui" + File.separator + "all" + File.separator + "arrowright.png", PixmapFormat.ARGB4444);
		Assets.scrollUp = g.newPixmap("gui" + File.separator + "all" + File.separator + "scrollup.png", PixmapFormat.ARGB4444);
		Assets.scrollDown = g.newPixmap("gui" + File.separator + "all" + File.separator + "scrolldown.png", PixmapFormat.ARGB4444);
		
		Assets.click = game.getAudio().newSound("sounds" + File.separator + "click.ogg");
		Assets.sound1 = game.getAudio().newSound("sounds" + File.separator + "sound1.ogg");
		Assets.sound2 = game.getAudio().newSound("sounds" + File.separator + "sound2.ogg");
		Assets.sound3 = game.getAudio().newSound("sounds" + File.separator + "sound3.ogg");
		
		Assets.mariogo1 = game.getAudio().newSound("sounds" + File.separator + "mariogo1.ogg");
		Assets.mariogo2 = game.getAudio().newSound("sounds" + File.separator + "mariogo2.ogg");
		Assets.zeldago1 = game.getAudio().newSound("sounds" + File.separator + "zeldago1.ogg");
		Assets.zeldago2 = game.getAudio().newSound("sounds" + File.separator + "zeldago2.ogg");
		Assets.metroidgo1 = game.getAudio().newSound("sounds" + File.separator + "metroidgo1.ogg");
		Assets.metroidgo2 = game.getAudio().newSound("sounds" + File.separator + "metroidgo2.ogg");
		
		Assets.bgm1 = game.getAudio().newMusic("music" + File.separator + "bgm1.ogg");
		Assets.bgm2 = game.getAudio().newMusic("music" + File.separator + "bgm2.ogg");
		Assets.bgm3 = game.getAudio().newMusic("music" + File.separator + "bgm3.ogg");
		
		Assets.bgm1.setLooping(true);
		Assets.bgm2.setLooping(true);
		Assets.bgm3.setLooping(true);
		
		Settings.load(game);
		game.setScreen(new MainMenuScreen(game));
	}

	@Override
	public void present(float deltaTime) {
		
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

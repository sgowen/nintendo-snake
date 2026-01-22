package com.comlu.lensource.nintendosnake;

import java.util.List;
import java.util.Random;

import android.graphics.Color;

import com.comlu.lensource.framework.Game;
import com.comlu.lensource.framework.Music;
import com.comlu.lensource.framework.Graphics;
import com.comlu.lensource.framework.Input.KeyEvent;
import com.comlu.lensource.framework.Input.TouchEvent;
import com.comlu.lensource.framework.Pixmap;
import com.comlu.lensource.framework.Screen;
import com.comlu.lensource.framework.Sound;

public class GameScreen extends Screen{
	Game game;
	Graphics g;
	enum GameState{ Ready, Running, Paused, HeadSpinning };
	GameState state;
	World world;
	Animation spin;
	Animation flash;
	Animation shiftLeft;
	int oldScore;
	int oldBonusScore;
	int oldItemsEaten;
	int direction;
	int oldCount;
	String score;
	String bonusScore;
	String animatedBonusScore;
	int receivedBonusScore;
	
	Pixmap backgroundPixmap;
	Pixmap pauseButtonPixmap;
	Pixmap gameOverPixmap;
	Pixmap numbersPixmap;
	Pixmap pauseMenuPixmap;
	Pixmap readyMenuPixmap;
	Pixmap itemFirst;
	Pixmap itemSecond;
	Pixmap tailNES;
	Pixmap tailSNES;
	Pixmap tailN64;
	Pixmap tailGC;
	Pixmap tailWII;
	
	Music bgmTheme;
	Sound gameOverSound;
	Sound collectSound;
	int endingHeadX;
	int endingHeadY;
	Random rand;
	
	public GameScreen(Game game) {
		super(game);
		this.game = game;
		g = game.getGraphics();
		world = null;
		backgroundPixmap = null;
		pauseButtonPixmap = null;
		gameOverPixmap = null;
		numbersPixmap = null;
		pauseMenuPixmap = null;
		readyMenuPixmap = null;
		itemFirst = null;
		itemSecond = null;
		tailNES = null;
		tailSNES = null;
		tailN64 = null;
		tailGC = null;
		tailWII = null;
		bgmTheme = null;
		gameOverSound = null;
		collectSound = null;
		state = GameState.Ready;
		flash = new Animation();
		shiftLeft = null;
		spin = null;
		oldScore = 0;
		oldBonusScore = 0;
		receivedBonusScore = 0;
		oldItemsEaten = 0;
		score = "0";
		bonusScore = "10";
		animatedBonusScore = "10";
		endingHeadX = 0;
		endingHeadY = 0;
		rand = new Random();
		int selectedGameOverSound = rand.nextInt(2);
		direction = 0;
		oldCount = 0;
		
		if(Settings.getSettingAt(0)){
			backgroundPixmap = Assets.mariobg;
			world = new World(Item.TYPE_1);
			bgmTheme = Assets.bgm1;
			gameOverSound = selectedGameOverSound == 0 ? Assets.mariogo1 : Assets.mariogo2;
			collectSound = Assets.sound1;
			pauseButtonPixmap = Assets.pause1;
			gameOverPixmap = Assets.gameOver1;
			numbersPixmap = Assets.numbers1;
			pauseMenuPixmap = Assets.pauseMenu1;
			readyMenuPixmap = Assets.ready1;
			itemFirst = Assets.item1;
			itemSecond = Assets.item4;
			tailNES = Assets.marioTailNes;
			tailSNES = Assets.marioTailSnes;
			tailN64 = Assets.marioTailN64;
			tailGC = Assets.marioTailGC;
			tailWII = Assets.marioTailWii;
		} else if(Settings.getSettingAt(1)){
			backgroundPixmap = Assets.zeldabg;
			world = new World(Item.TYPE_2);
			bgmTheme = Assets.bgm2;
			gameOverSound = selectedGameOverSound == 0 ? Assets.zeldago1 : Assets.zeldago2;
			collectSound = Assets.sound2;
			pauseButtonPixmap = Assets.pause2;
			gameOverPixmap = Assets.gameOver2;
			numbersPixmap = Assets.numbers2;
			pauseMenuPixmap = Assets.pauseMenu2;
			readyMenuPixmap = Assets.ready2;
			itemFirst = Assets.item2;
			itemSecond = Assets.item5;
			tailNES = Assets.linkTailNes;
			tailSNES = Assets.linkTailSnes;
			tailN64 = Assets.linkTailN64;
			tailGC = Assets.linkTailGC;
			tailWII = Assets.linkTailWii;
		} else if(Settings.getSettingAt(2)){
			backgroundPixmap = Assets.metroidbg;
			world = new World(Item.TYPE_3);
			bgmTheme = Assets.bgm3;
			gameOverSound = selectedGameOverSound == 0 ? Assets.metroidgo1 : Assets.metroidgo2;
			collectSound = Assets.sound3;
			pauseButtonPixmap = Assets.pause3;
			gameOverPixmap = Assets.gameOver3;
			numbersPixmap = Assets.numbers3;
			pauseMenuPixmap = Assets.pauseMenu3;
			readyMenuPixmap = Assets.ready3;
			itemFirst = Assets.item3;
			itemSecond = Assets.item6;
			tailNES = Assets.samusTailNes;
			tailSNES = Assets.samusTailSnes;
			tailN64 = Assets.samusTailN64;
			tailGC = Assets.samusTailGC;
			tailWII = Assets.samusTailWii;
		}
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		List<KeyEvent> keyEvents = game.getInput().getKeyEvents();
		
		if(state == GameState.Ready){
			updateReady(touchEvents);
		}
		if(state == GameState.Running){
			updateRunning(touchEvents, keyEvents, deltaTime);
		}
		if(state == GameState.Paused){
			updatePaused(touchEvents);
		}
		if(state == GameState.HeadSpinning){
			updateHeadSpinning(deltaTime);
		}
	}

	public void updateReady(List<TouchEvent> touchEvents){
		int len = touchEvents.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP){
				handleMusic();
				state = GameState.Running;
				return;
			}
		}
	}
	
	public void updateRunning(List<TouchEvent> touchEvents, List<KeyEvent> keyEvents, float deltaTime){
		Snake snake = world.snake;
		
		int len = touchEvents.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP){
				if(event.x < 64 && event.y < 64){
					handleSound(0);
					state = GameState.Paused;
					handleMusic();
					return;
				}
			}
			if(event.y > 64 && event.type == TouchEvent.TOUCH_DOWN){
				if(snake.direction == 0){
					if(event.x < Settings.xCal){
						world.snake.turnLeft();
					} else{
						world.snake.turnRight();
					}
				} else if(snake.direction == 2){
					if(event.x > Settings.xCal){
						world.snake.turnLeft();
					} else{
						world.snake.turnRight();
					}
				} else if(snake.direction == 1){
					if(event.y > Settings.yCal){
						world.snake.turnLeft();
					} else{
						world.snake.turnRight();
					}
				} else if(snake.direction == 3){
					if(event.y < Settings.yCal){
						world.snake.turnLeft();
					} else{
						world.snake.turnRight();
					}
				}
			}
		}
		
		int lenk = keyEvents.size();
		for(int i = 0; i < lenk; i++){
			KeyEvent event = keyEvents.get(i);
			if(event.type == KeyEvent.KEY_DOWN){
				if(event.keyCode == Settings.KEY_UP){
					if(snake.direction == 1){
						world.snake.turnRight();
					} else if(snake.direction == 3){
						world.snake.turnLeft();
					}
				}
				if(event.keyCode == Settings.KEY_LEFT){
					if(snake.direction == 0){
						world.snake.turnLeft();
					} else if(snake.direction == 2){
						world.snake.turnRight();
					}
				}
				if(event.keyCode == Settings.KEY_DOWN){
					if(snake.direction == 1){
						world.snake.turnLeft();
					} else if(snake.direction == 3){
						world.snake.turnRight();
					}
				}
				if(event.keyCode == Settings.KEY_RIGHT){
					if(snake.direction == 0){
						world.snake.turnRight();
					} else if(snake.direction == 2){
						world.snake.turnLeft();
					}
				}
			}
		}
		
		world.update(deltaTime);
		oldBonusScore = world.bonusScore;
		bonusScore = Integer.toString(oldBonusScore);
		
		if(world.gameOver){
			handleSound(1);
			handleMusic();
			Settings.addScore(world.score);
			endingHeadX = world.snake.parts.get(0).x * 32 + 16;
			endingHeadY = world.snake.parts.get(0).y * 32 + 16 + 64;
			spin = new Animation();
			state = GameState.HeadSpinning;
			return;
		}
		if(world.snake.itemsEaten != oldItemsEaten){
			oldItemsEaten = world.snake.itemsEaten;
			oldScore = world.score;
			score = Integer.toString(oldScore);
			if(shiftLeft == null){
				receivedBonusScore = oldBonusScore;
				animatedBonusScore = Integer.toString(receivedBonusScore);
			} else if(shiftLeft != null){
				animatedBonusScore = "";
				world.score += receivedBonusScore;
				oldScore = world.score;
				score = Integer.toString(oldScore);
				receivedBonusScore = oldBonusScore;
				animatedBonusScore = Integer.toString(receivedBonusScore);
			}
			if(receivedBonusScore != 0){
				shiftLeft = new Animation();
			}
			handleSound(2);
		}
	}
	
	private void updatePaused(List<TouchEvent> touchEvents){
		int len = touchEvents.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP){
				if(event.x > 80 && event.x <= 240){
					if(event.y > 100 && event.y < 150){
						handleSound(0);
						handleMusic();
						state = GameState.Running;
						return;
					}
					if(event.y > 150 && event.y < 200){
						handleSound(0);
						game.setScreen(new LoadingScreen(game));
						return;
					}
				}
			}
		}
	}
	
	private void updateHeadSpinning(float deltaTime){
		if(spin.count >= 15){
			game.setScreen(new EndGameScreen(game));
			return;
		}
	}
	
	@Override
	public void present(float deltaTime) {
		if(state == GameState.HeadSpinning){
			drawHeadSpinningUI(deltaTime);
		} else{
			drawWorld(world);
		}
		
		if(state == GameState.Ready){
			drawReadyUI(deltaTime);
		}
		if(state == GameState.Running){
			drawRunningUI(deltaTime);
		}
		if(state == GameState.Paused){
			drawPausedUI(deltaTime);
		}
	}

	private void drawWorld(World world){
		g.drawPixmap(backgroundPixmap, 0, 0);
		g.drawPixmap(Assets.score, g.getWidth() / 2-30, 1);
		g.drawPixmap(Assets.bonus, g.getWidth()-64, 1);
		drawText(score, g.getWidth() / 2 - score.length()*20 / 2, 31);
		drawText(bonusScore, g.getWidth()-48, 31);
		Snake snake = world.snake;
		SnakePart head = snake.parts.get(0);
		Item item = world.item;
		
		int x = item.x * 32;
		int y = item.y * 32 + 64;
		if(snake.itemsEaten < 36){
			g.drawPixmap(itemFirst, x, y);
		} else{
			g.drawPixmap(itemSecond, x, y);
		}
		
		int len = snake.parts.size();
		for(int i = 1; i < len; i++){
			SnakePart part = snake.parts.get(i);
			x = part.x * 32;
			y = part.y * 32 + 64;
			if(snake.itemsEaten < 12){
				g.drawPixmap(tailNES, x, y);
			} else if(snake.itemsEaten < 24){
				g.drawPixmap(tailSNES, x, y);
			} else if(snake.itemsEaten < 36){
				g.drawPixmap(tailN64, x, y);
			} else if(snake.itemsEaten < 48){
				g.drawPixmap(tailGC, x, y);
			} else{
				g.drawPixmap(tailWII, x, y);
			}
		}
		
		Pixmap headPixmap = null;
		//Mario Block
		if(Settings.settings[0] == true){
			if(snake.direction == Snake.UP){
				headPixmap = Assets.headUp1;
			}
			if(snake.direction == Snake.LEFT){
				headPixmap = Assets.headLeft1;
			}
			if(snake.direction == Snake.DOWN){
				headPixmap = Assets.headDown1;
			}
			if(snake.direction == Snake.RIGHT){
				headPixmap = Assets.headRight1;
			}
		}
		//Zelda Block
		if(Settings.settings[1] == true){
			if(snake.direction == Snake.UP){
				headPixmap = Assets.headUp2;
			}
			if(snake.direction == Snake.LEFT){
				headPixmap = Assets.headLeft2;
			}
			if(snake.direction == Snake.DOWN){
				headPixmap = Assets.headDown2;
			}
			if(snake.direction == Snake.RIGHT){
				headPixmap = Assets.headRight2;
			}
		}
		//Metroid Block
		if(Settings.settings[2] == true){
			if(snake.direction == Snake.UP){
				if(snake.morphBallAnim == 0){
					headPixmap = Assets.headUp3;
				} else if(snake.morphBallAnim == 1){
					headPixmap = Assets.headUp3a;
				} else if(snake.morphBallAnim == 2){
					headPixmap = Assets.headUp3b;
				}
			}
			if(snake.direction == Snake.LEFT){
				if(snake.morphBallAnim == 0){
					headPixmap = Assets.headLeft3;
				} else if(snake.morphBallAnim == 1){
					headPixmap = Assets.headLeft3a;
				} else if(snake.morphBallAnim == 2){
					headPixmap = Assets.headLeft3b;
				}
			}
			if(snake.direction == Snake.DOWN){
				if(snake.morphBallAnim == 0){
					headPixmap = Assets.headDown3;
				} else if(snake.morphBallAnim == 1){
					headPixmap = Assets.headDown3a;
				} else if(snake.morphBallAnim == 2){
					headPixmap = Assets.headDown3b;
				}
			}
			if(snake.direction == Snake.RIGHT){
				if(snake.morphBallAnim == 0){
					headPixmap = Assets.headRight3;
				} else if(snake.morphBallAnim == 1){
					headPixmap = Assets.headRight3a;
				} else if(snake.morphBallAnim == 2){
					headPixmap = Assets.headRight3b;
				}
			}
		}
		x = head.x * 32 + 16;
		y = head.y * 32 + 16 + 64;
		g.drawPixmap(headPixmap, x - headPixmap.getWidth() / 2, y - headPixmap.getHeight() / 2);
	}
	
	private void drawReadyUI(float deltaTime){ //Draw Directional Arrows for the player so they know how to control
		flash.update(deltaTime, Animation.TICK_FLASH, Animation.FLASH);
		
		g.drawPixmap(readyMenuPixmap, 40, 80);
		
		if(flash.showArrows == true){
			g.drawPixmap(Assets.arrowLeft, Settings.xCal-64, Settings.yCal-32);
			g.drawPixmap(Assets.arrowRight, Settings.xCal, Settings.yCal-32);
			g.drawPixmap(Assets.arrowUp, Settings.xCal-32, Settings.yCal-64);
			g.drawPixmap(Assets.arrowDown, Settings.xCal-32, Settings.yCal);
			g.drawLine(0, Settings.yCal, 320, Settings.yCal, Color.DKGRAY);
			g.drawLine(Settings.xCal, 0, Settings.xCal, 480, Color.DKGRAY);
		}
	}
	
	private void drawRunningUI(float deltaTime){
		g.drawPixmap(pauseButtonPixmap, 0, 0); //Pause Button
		g.drawLine(0, 64, 480, 64, Color.BLACK);
		
		if(shiftLeft != null){
			shiftLeft.update(deltaTime, Animation.TICK_SHIFT_LEFT, Animation.SHIFT_LEFT);
			if(shiftLeft.count < 11){
				int adjustment = shiftLeft.count*12;
				drawText(animatedBonusScore, g.getWidth()-40-adjustment, 31);
			} else{
				animatedBonusScore = "";
				world.score += receivedBonusScore;
				oldScore = world.score;
				score = Integer.toString(oldScore);
				shiftLeft = null;
			}
		}
	}
	
	private void drawPausedUI(float deltaTime){
		flash.update(deltaTime, Animation.TICK_FLASH, Animation.FLASH);
		g.drawPixmap(pauseMenuPixmap, 80, 100);
		if(flash.showArrows == true){
			g.drawPixmap(Assets.arrowLeft, Settings.xCal-64, Settings.yCal-32);
			g.drawPixmap(Assets.arrowRight, Settings.xCal, Settings.yCal-32);
			g.drawPixmap(Assets.arrowUp, Settings.xCal-32, Settings.yCal-64);
			g.drawPixmap(Assets.arrowDown, Settings.xCal-32, Settings.yCal);
			g.drawLine(0, Settings.yCal, 320, Settings.yCal, Color.DKGRAY);
			g.drawLine(Settings.xCal, 0, Settings.xCal, 480, Color.DKGRAY);
		}
	}
	
	private void drawHeadSpinningUI(float deltaTime){
		g.drawPixmap(Assets.blackness, 0, 0);
		Pixmap headPixmap = null;
		if(spin.count < 15){
			spin.update(deltaTime, Animation.TICK_HEAD_SPIN, Animation.HEAD_SPIN);
		}
		if(oldCount != spin.count){
			oldCount = spin.count;
			direction++;
			if(direction > Snake.RIGHT){
				direction = Snake.UP;
			}
		}
		if(Settings.getSettingAt(0)){
			if(direction == Snake.UP){
				headPixmap = Assets.headUp1;
			} else if(direction == Snake.LEFT){
				headPixmap = Assets.headLeft1;
			} else if(direction == Snake.DOWN){
				headPixmap = Assets.headDown1;
			} else{
				headPixmap = Assets.headRight1;
			}
		} else if(Settings.getSettingAt(1)){
			if(direction == Snake.UP){
				headPixmap = Assets.headUp2;
			} else if(direction == Snake.LEFT){
				headPixmap = Assets.headLeft2;
			} else if(direction == Snake.DOWN){
				headPixmap = Assets.headDown2;
			} else{
				headPixmap = Assets.headRight2;
			}
		} else if(Settings.getSettingAt(2)){
			if(direction == Snake.UP){
				headPixmap = Assets.headUp3;
			} else if(direction == Snake.LEFT){
				headPixmap = Assets.headLeft3;
			} else if(direction == Snake.DOWN){
				headPixmap = Assets.headDown3;
			} else{
				headPixmap = Assets.headRight3;
			}
		}
		g.drawPixmap(headPixmap, endingHeadX - headPixmap.getWidth()/2, endingHeadY - headPixmap.getHeight()/2);
	}
	
	public void drawText(String line, int x, int y){
		int len = line.length();
		for(int i = 0; i < len; i++){
			char character = line.charAt(i);
			int srcX = 0;
			int srcWidth = 0;
			
			String tempChar = "";
			tempChar += character;
			srcX = Integer.parseInt(tempChar) * 20;
			srcWidth = 20;
			
			g.drawPixmap(numbersPixmap, x, y, srcX, 0, srcWidth, 32);
			x += srcWidth;
		}
	}
	
	private void handleMusic(){
		if(bgmTheme != null){
			if(Settings.playMusic){
				if(bgmTheme.isPlaying()){
					bgmTheme.pause();
				} else{
					bgmTheme.play();
				}
			}
		}
	}
	
	private void handleSound(int soundToPlay){
		if(Settings.playSound){
			switch(soundToPlay){
			case 0:
				Assets.click.play(1);
				break;
			case 1:
				gameOverSound.play(1);
				break;
			case 2:
				collectSound.play(1);
				break;
			case 3:
				break;
			default:
				break;
			}
		}
	}
	
	@Override
	public void pause() {
		if(state == GameState.Running){
			handleMusic();
			state = GameState.Paused;
		}
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		world = null;
		backgroundPixmap = null;
		pauseButtonPixmap = null;
		pauseMenuPixmap = null;
		readyMenuPixmap = null;
		itemFirst = null;
		itemSecond = null;
		tailNES = null;
		tailSNES = null;
		tailN64 = null;
		tailGC = null;
		tailWII = null;
		bgmTheme = null;
		collectSound = null;
		flash = null;
		shiftLeft = null;
		spin = null;
		try {
			finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
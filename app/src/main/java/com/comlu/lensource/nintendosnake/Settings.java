package com.comlu.lensource.nintendosnake;

import com.comlu.lensource.framework.Game;

public class Settings{
	public static String[] highscoresRanks = { "1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th" };
	public static boolean[] settings = {false,false,false}; //changes per game session based on which theme is chosen, therefore does not need to be saved
	public static boolean isNewHighScore = false; //Is set to true if and only if a new score is recorded in the highscores Array.
	public static int newHighScoreRow = 0;
	public static String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
	
	public static int xCal;
	public static int yCal;
	public static int KEY_UP;
	public static int KEY_LEFT;
	public static int KEY_DOWN;
	public static int KEY_RIGHT;
	public static boolean playMusic;
	public static boolean playSound;
	public static String tempKeyUp;
	public static String tempKeyLeft;
	public static String tempKeyDown;
	public static String tempKeyRight;
	
	public static int[] highscores = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	public static String[] highscoresInitials = new String[10];
	
	public static String[] highscoreRows1 = { "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "a10"};
	public static String[] highscoreRows2 = { "b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8", "b9", "b10"};
	
	public static void load(Game game){
		tempKeyUp = game.getSaveData().getString("key_up_display", "D-PAD UP");
		tempKeyDown = game.getSaveData().getString("key_down_display", "D-PAD DOWN");
		tempKeyLeft = game.getSaveData().getString("key_left_display", "D-PAD LEFT");
		tempKeyRight = game.getSaveData().getString("key_right_display", "D-PAD RIGHT");
		playMusic = game.getSaveData().getBoolean("play_music", true);
		playSound = game.getSaveData().getBoolean("play_sound", true);
		KEY_UP = game.getSaveData().getInt("key_up", android.view.KeyEvent.KEYCODE_DPAD_UP);
		KEY_LEFT = game.getSaveData().getInt("key_left", android.view.KeyEvent.KEYCODE_DPAD_LEFT);
		KEY_DOWN = game.getSaveData().getInt("key_down", android.view.KeyEvent.KEYCODE_DPAD_DOWN);
		KEY_RIGHT = game.getSaveData().getInt("key_right", android.view.KeyEvent.KEYCODE_DPAD_RIGHT);
		xCal = game.getSaveData().getInt("x_cal", 160);
		yCal = game.getSaveData().getInt("y_cal", 400);
		for(int i = 0; i < highscores.length; i++){
			highscores[i] = game.getSaveData().getInt(highscoreRows1[i], 0);
			highscoresInitials[i] = game.getSaveData().getString(highscoreRows2[i], "");
		}
	}
	
	public static void save(Game game){
		game.getSaveDataEditor().putString("key_up_display", tempKeyUp);
		game.getSaveDataEditor().putString("key_left_display", tempKeyLeft);
		game.getSaveDataEditor().putString("key_down_display", tempKeyDown);
		game.getSaveDataEditor().putString("key_right_display", tempKeyRight);
		game.getSaveDataEditor().putBoolean("play_music", playMusic);
		game.getSaveDataEditor().putBoolean("play_sound", playSound);
		game.getSaveDataEditor().putInt("key_up", KEY_UP);
		game.getSaveDataEditor().putInt("key_left", KEY_LEFT);
		game.getSaveDataEditor().putInt("key_down", KEY_DOWN);
		game.getSaveDataEditor().putInt("key_right", KEY_RIGHT);
		game.getSaveDataEditor().putInt("x_cal", xCal);
		game.getSaveDataEditor().putInt("y_cal", yCal);
		for(int i = 0; i < highscores.length; i++){
			game.getSaveDataEditor().putInt(highscoreRows1[i], highscores[i]);
			game.getSaveDataEditor().putString(highscoreRows2[i], highscoresInitials[i]);
		}
		game.getSaveDataEditor().commit();
	}
	
	public static void addScore(int score){
		for(int i = 0; i < highscores.length; i++){
			if(highscores[i] < score){
				for(int j = highscores.length -1; j > i; j--){
					highscores[j] = highscores[j-1];
					highscoresInitials[j] = highscoresInitials[j-1];
				}
				highscores[i] = score;
				newHighScoreRow = i;
				isNewHighScore = true;
				return;
			} else{
				isNewHighScore = false;
				newHighScoreRow = 0;
			}
		}
	}
	
	public static boolean getSettingAt(int i){ //Used for Selecting Playable Theme
		return settings[i];
	}
	
	public static void setSettingAt(int i, boolean j){ //The appropriate theme is set in the ChooseThemeScreen
		settings[i] = j;
	}
}

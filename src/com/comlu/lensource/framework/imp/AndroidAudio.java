package com.comlu.lensource.framework.imp;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.comlu.lensource.framework.Audio;
import com.comlu.lensource.framework.Music;
import com.comlu.lensource.framework.Sound;

public class AndroidAudio implements Audio{
	AssetManager assets;
	SoundPool soundPool;
	
	public AndroidAudio(Activity activity){
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assets = activity.getAssets();
		this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
	}
	@Override
	public Music newMusic(String filename) {
		try{
			AssetFileDescriptor assetDescriptor = assets.openFd(filename);
			return new AndroidMusic(assetDescriptor);
		} catch(IOException ex){
			throw new RuntimeException("Couldn't load music '" + filename + "'");
		}
	}

	@Override
	public Sound newSound(String filename) {
		try{
			AssetFileDescriptor assetDescriptor = assets.openFd(filename);
			int soundID = soundPool.load(assetDescriptor, 0);
			return new AndroidSound(soundPool, soundID);
		} catch(IOException ex){
			throw new RuntimeException("Couldn't load sound '" + filename + "'");
		}
	}

}

package com.comlu.lensource.framework.imp;

import java.io.IOException;

import com.comlu.lensource.framework.Music;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class AndroidMusic implements Music, OnCompletionListener{
	MediaPlayer mediaPlayer;
	boolean isPrepared = false;
	
	public AndroidMusic(AssetFileDescriptor assetDescriptor){
		mediaPlayer = new MediaPlayer();
		try{
			mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(), assetDescriptor.getStartOffset(), assetDescriptor.getLength());
			mediaPlayer.prepare();
			isPrepared = true;
			mediaPlayer.setOnCompletionListener(this);
		} catch(Exception ex){
			throw new RuntimeException("Couldn't load music, good fucking job!");
		}
	}
	
	@Override
	public void onCompletion(MediaPlayer mediaPlayer) {
		synchronized(this){
			isPrepared = false;
		}
	}

	@Override
	public void play() {
		if(mediaPlayer.isPlaying()){
			return;
		}
		try{
			synchronized(this){
				if(!isPrepared){
					mediaPlayer.prepare();
				}
				mediaPlayer.start();
			}
		} catch(IllegalStateException ex){
			//well fuck wouldn't be very good...
		} catch(IOException ex){
			//and neither would this...
		}
	}

	@Override
	public void stop() {
		mediaPlayer.stop();
		synchronized(this){
			isPrepared = false;
		}
	}

	@Override
	public void pause() {
		mediaPlayer.pause();
	}

	@Override
	public void setLooping(boolean isLooping) {
		mediaPlayer.setLooping(isLooping);
	}

	@Override
	public void setVolume(float volume) {
		mediaPlayer.setVolume(volume, volume);
	}

	@Override
	public boolean isPlaying() {
		return mediaPlayer.isPlaying();
	}

	@Override
	public boolean isStopped() {
		return !isPrepared;
	}

	@Override
	public boolean isLooping() {
		return mediaPlayer.isLooping();
	}

	@Override
	public void dispose() {
		if(mediaPlayer.isPlaying()){
			mediaPlayer.stop();
		}
		mediaPlayer.release();
	}
}

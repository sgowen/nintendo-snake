package com.comlu.lensource.nintendosnake;

public class Animation {
	//animTimes
	static final float TICK_FLASH = 1.0f;
	static final float TICK_HEAD_SPIN = 0.1f;
	static final float TICK_SHIFT_LEFT = .08f;
	static final float TICK_WAIT = .5f;
	//animTypes
	public static final int FLASH = 0;
	public static final int HEAD_SPIN = 1;
	public static final int SHIFT_LEFT = 2;
	public static final int WAIT = 3;
	
	float tickTime = 0;
	int count; //can be used for anything, specifically for terminating animations =)
	boolean showArrows; //Used specifically for flashing the Directional Arrows to the screen!
	
	public Animation(){
		tickTime = 0f;
		count = 0;
		showArrows = true;
	}
	
	public void update(float deltaTime, float animTime, int animType){
		tickTime += deltaTime;
		
		if(tickTime > animTime){
			switch(animType){
			case FLASH:
				if(showArrows == false){
					showArrows = true;
				} else if(showArrows == true){
					showArrows = false;
				}
				tickTime = 0;
				break;
			case HEAD_SPIN:
				count++;
				tickTime = 0;
				break;
			case SHIFT_LEFT:
				count++;
				tickTime = 0;
				break;
			case WAIT:
				count++;
				tickTime = 0;
				break;
			default:
				break;
			}
		}
	}
}
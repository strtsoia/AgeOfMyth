package sound;

import pulpcore.sound.*;

public class SoundManager {
	
	private static SoundManager soundManager;
	static Playback playback;
	static Playback playbackloop;
	
	
	static Sound battleSound;
	static Sound winBattle;
	static Sound boardSound;
	
	private SoundManager()
	{
		battleSound = Sound.load("sound/battle.wav");
		winBattle = Sound.load("sound/winbattle.wav");
		boardSound = Sound.load("sound/board.wav");
	}
	
	public static void Init()
	{
		battleSound.play();
		winBattle.play();
		boardSound.play();
	}
	
	public static SoundManager GetInstance()
	{
		if(soundManager == null){
			soundManager = new SoundManager();
			return soundManager;
		}
		
		return soundManager;
	}
	
	public static void PlayBattleSound()
	{
		playback = battleSound.play();
		playbackloop = battleSound.loop();
	}
	
	public static void PlayWinBattle()
	{
		playback = winBattle.play();
	}
	
	public static void PlayBoardSound()
	{
		playback = boardSound.play();
		playbackloop = boardSound.loop();
	}
	
	public static void Stop()
	{
		playback.stop();
		playbackloop.stop();
	}
}

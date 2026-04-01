package a.entity.gus06.file.wav.play;

import a.framework.*;
import java.io.File;
import java.io.FileInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.Clip;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20250520";}
	
	
	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(file))
		{
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			playClip(clip);
		}
	}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		return new Holder(file);
	}
	
	private class Holder implements P
	{
		private File file;
		private Clip clip;
		
		public Holder(File file)
		{this.file = file;}
		
		public void p(Object obj) throws Exception
		{
			String s = (String) obj;
			
			if(s.equals("play")) {play();return;}
			if(s.equals("close")) {close();return;}
			throw new Exception("Unknown command "+s);
		}
		
		private void play()
		{
			close();
			try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(file))
			{
				clip = AudioSystem.getClip();
				clip.open(audioStream);
				playClip(clip);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this, "play()", e);}
		}
		
		private void close()
		{
			if (clip != null && clip.isRunning())
			{
				clip.stop();
				clip.close();
			}
			clip = null;
		}
	}
	
	
	private void playClip(Clip clip)
	{
		try
		{
			clip.start();
			do
			{
				try {Thread.sleep(100);}
				catch (InterruptedException e)
				{
					clip.stop();
					clip.close();
					return;
				}
			}
			while (clip.isRunning());
		}
		catch(Exception e)
		{Outside.err(this,"play(Clip)",e);}
	}
}
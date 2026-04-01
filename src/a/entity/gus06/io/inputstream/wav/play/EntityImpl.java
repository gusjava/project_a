package a.entity.gus06.io.inputstream.wav.play;

import a.framework.*;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20250524";}
	
	public void p(Object obj) throws Exception
	{
		InputStream is = toInputStream(obj);
		try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(is))
		{
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			playClip(clip);
		}
	}
	
	private InputStream toInputStream(Object obj) throws Exception
	{
		if(obj instanceof InputStream) return (InputStream) obj;
		if(obj instanceof File) return new FileInputStream((File) obj);
		if(obj instanceof G) return (InputStream) ((G) obj).g();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	public Object t(Object obj) throws Exception
	{
		G g = (G) obj;
		return new Holder(g);
	}
	
	private class Holder implements P
	{
		private G g;
		private Clip clip;
		
		public Holder(G g)
		{this.g = g;}
		
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
			try (
			InputStream is = (InputStream) g.g();
			BufferedInputStream bis = new BufferedInputStream(is);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(bis)
			)
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

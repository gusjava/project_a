package a.entity.gus06.file.mp3.play;

import a.framework.*;
import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20250520";}
	
	
	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		try (FileInputStream fis = new FileInputStream(file))
		{
			Player player = new Player(fis);
			player.play();
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
		private Player player;
		
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
			if(player!=null && !player.isComplete())
			player.close();
			
			try (FileInputStream fis = new FileInputStream(file))
			{
				player = new Player(fis);
				player.play();
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this, "play()", e);}
		}
		
		private void close()
		{
			if(player!=null && !player.isComplete())
			player.close();
		}
	}
}
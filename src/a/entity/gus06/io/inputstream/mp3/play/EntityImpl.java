package a.entity.gus06.io.inputstream.mp3.play;

import a.framework.*;
import javazoom.jl.player.Player;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20250523";}
	
	public void p(Object obj) throws Exception
	{
		InputStream is = toInputStream(obj);
		Player player = new Player(is);
		player.play();
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
		private Player player;
		
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
			if(player!=null && !player.isComplete())
			player.close();
			
			try (InputStream is = (InputStream) g.g())
			{
				player = new Player(is);
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

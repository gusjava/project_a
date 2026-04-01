package a.entity.gus06.file.audio.play.generic.findplayer;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250521";}


	private Service player_wav;
	private Service player_mp3;

	public EntityImpl() throws Exception
	{
		player_wav = Outside.service(this,"gus06.file.wav.play");
		player_mp3 = Outside.service(this,"gus06.file.mp3.play");
	}

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		return find(file);
	}
	
	private Service find(File file) throws Exception
	{
		String s = file.getName().toLowerCase();
		
		if(en(s,"wav")) return player_wav;
		if(en(s,"mp3")) return player_mp3;
		return null;
	}
	
	private boolean en(String s, String ext)
	{return s.endsWith("."+ext);}
}
package a.entity.gus06.file.audio.dsj.infomap;

import a.framework.*;
import java.io.File;
import de.humatic.dsj.DSMovie;
import de.humatic.dsj.DSFiltergraph;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191202";}


	private Service arrayToString;

	public EntityImpl() throws Exception
	{
		arrayToString = Outside.service(this,"gus06.tostring.array.join.semicolon");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		DSMovie audio = new DSMovie(file.getAbsolutePath(),DSFiltergraph.DD7,null);
		
		String audioCodec = audio.getAudioCodec();
		String duration = ""+audio.getDuration();
		String audioProps = (String) arrayToString.t(audio.getAudioProperties());
		
		audio.stop();
		audio.dispose();
		
		Map map = new HashMap();
		
		map.put("audioCodec",audioCodec);
		map.put("duration",duration);
		map.put("audioProps",audioProps);
		
		return map;
	}
}

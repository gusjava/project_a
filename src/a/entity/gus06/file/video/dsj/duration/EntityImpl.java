package a.entity.gus06.file.video.dsj.duration;

import a.framework.*;
import java.io.File;
import de.humatic.dsj.DSMovie;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201030";}
	

	private Service buildDSMovie;

	public EntityImpl() throws Exception
	{
		buildDSMovie = Outside.service(this,"gus06.file.video.dsj.build.dsmovie");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		DSMovie movie = (DSMovie) buildDSMovie.t(file);
		int duration = movie.getDuration();
		
		movie.stop();
		movie.dispose();
		
		return Long.valueOf(duration);
	}
}
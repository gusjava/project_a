package a.entity.gus06.file.video.dsj.capture;

import a.framework.*;
import java.io.File;
import de.humatic.dsj.DSMovie;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191226";}


	private Service findWidth;
	private Service findPos;
	private Service buildDSMovie;

	public EntityImpl() throws Exception
	{
		findWidth = Outside.service(this,"gus06.file.image.width.rebuild");
		findPos = Outside.service(this,"gus06.file.video.duration.rebuild");
		buildDSMovie = Outside.service(this,"gus06.file.video.dsj.build.dsmovie");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object posObj = o[1];
		Object widthObj = o[2];
		
		DSMovie movie = (DSMovie) buildDSMovie.t(file);
		int totalWidth = movie.getMediaDimension().width;
		int duration = movie.getDuration();
		
		int pos = (int) findPos.t(new Object[]{duration,posObj});
		int width = (int) findWidth.t(new Object[]{totalWidth,widthObj});
		
		BufferedImage image = movie.getThumbnail(pos,width);
		
		movie.stop();
		movie.dispose();
		
		return image;
	}
	
	
	
	
}

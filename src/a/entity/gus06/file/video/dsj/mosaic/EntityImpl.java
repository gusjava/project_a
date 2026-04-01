package a.entity.gus06.file.video.dsj.mosaic;

import a.framework.*;
import java.io.File;
import de.humatic.dsj.DSMovie;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200107";}
	

	private Service merge;
	private Service findWidth;
	private Service buildDSMovie;

	public EntityImpl() throws Exception
	{
		merge = Outside.service(this,"gus06.awt.bufferedimage.merge.grid");
		findWidth = Outside.service(this,"gus06.file.image.width.rebuild");
		buildDSMovie = Outside.service(this,"gus06.file.video.dsj.build.dsmovie");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object widthObj = o[1];
		Integer nb = (Integer) o[2];
		
		BufferedImage[][] im = new BufferedImage[nb][nb];
		
		DSMovie movie = (DSMovie) buildDSMovie.t(file);
		int totalWidth = movie.getMediaDimension().width;
		int duration = movie.getDuration();
		
		int width = (int) findWidth.t(new Object[]{totalWidth,widthObj});
		
		int dt = (int) ((double)duration/(double) (nb*nb+1));
		int dw = (int) ((double)width/(double) nb);
		
		int pos = 0;
		for(int i=0;i<nb;i++)
		for(int j=0;j<nb;j++)
		{
			pos += dt;
			im[i][j] = movie.getThumbnail(pos,dw);
		}
		
		movie.stop();
		movie.dispose();
		
		return merge.t(im);
	}
}
package a.entity.gus06.file.video.generic.mosaic;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200114";}


	private Service useDsj;
	private Service useJCodec;

	public EntityImpl() throws Exception
	{
		useDsj = Outside.service(this,"gus06.file.video.dsj.mosaic");
		useJCodec = Outside.service(this,"gus06.file.video.jcodec.mosaic");
	}
	
	public Object t(Object obj) throws Exception
	{
		try{return useDsj.t(obj);}
		catch(Exception e){}
		
		return useJCodec.t(obj);
	}
}

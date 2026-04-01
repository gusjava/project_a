package a.entity.gus06.file.image.extraction.jpegphoto.originaltime.yyyymmdd.s;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180218";}
	
	public static final String NOT_FOUND = "NOT_FOUND";


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.file.image.extraction.jpegphoto.originaltime.yyyymmdd");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		try{return perform.t(obj);}
		catch(Exception e){return NOT_FOUND;}
	}
}

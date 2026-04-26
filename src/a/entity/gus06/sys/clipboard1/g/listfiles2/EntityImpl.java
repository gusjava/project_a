package a.entity.gus06.sys.clipboard1.g.listfiles2;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20151021";}


	private Service accessListFiles;
	private Service accessString;
	private Service convertString;


	public EntityImpl() throws Exception
	{
		accessListFiles = Outside.service(this,"gus.y.clipboard1.files");
		accessString = Outside.service(this,"gus.x.clipboard.string");
		convertString = Outside.service(this,"gus06.sys.clipboard1.g.listfiles2.convertstring");
	}

	
	
	public Object g() throws Exception
	{
		Object list = accessListFiles.g();
		if(list!=null) return list;
		
		Object string = accessString.g();
		if(string!=null) return convertString.t(string);
		
		return null;
	}
}

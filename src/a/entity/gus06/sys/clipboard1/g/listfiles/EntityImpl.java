package a.entity.gus06.sys.clipboard1.g.listfiles;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20151006";}


	private Service accessImage;
	private Service accessString;
	private Service accessListFiles;
	
	private Service convertImage;
	private Service convertString;


	public EntityImpl() throws Exception
	{
		accessImage = Outside.service(this,"gus06.clipboard.access.image");
		accessString = Outside.service(this,"gus.x.clipboard.string");
		accessListFiles = Outside.service(this,"gus.y.clipboard1.files");
		
		convertImage = Outside.service(this,"gus06.sys.clipboard1.g.listfiles.convertimage");
		convertString = Outside.service(this,"gus06.sys.clipboard1.g.listfiles.convertstring");
	}

	
	
	public Object g() throws Exception
	{
		Object list = accessListFiles.g();
		if(list!=null) return list;
		
		Object img = accessImage.g();
		if(img!=null) return convertImage.t(img);
		
		Object string = accessString.g();
		if(string!=null) return convertString.t(string);
		
		return null;
	}
}

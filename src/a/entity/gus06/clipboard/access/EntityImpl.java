package a.entity.gus06.clipboard.access;

import a.framework.*;
import java.awt.Image;
import java.util.List;
import java.io.File;

public class EntityImpl implements Entity, P, G {

	public String creationDate() {return "20150530";}


	private Service accessImage;
	private Service accessListFiles;
	private Service accessString;


	public EntityImpl() throws Exception
	{
		accessImage = Outside.service(this,"gus.x.clipboard.image");
		accessListFiles = Outside.service(this,"gus.y.clipboard1.files");
		accessString = Outside.service(this,"gus.x.clipboard.string");
	}

	
	public Object g() throws Exception
	{
		Object string = accessString.g();
		if(string!=null) return string;
		
		Object list = accessListFiles.g();
		if(list!=null) return list;
		
		return accessImage.g();
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) return;
		
		if(obj instanceof String) {accessString.p(obj);return;}
		if(obj instanceof Image) {accessImage.p(obj);return;}
		if(obj instanceof List) {accessListFiles.p(obj);return;}
		if(obj instanceof File) {accessListFiles.p(obj);return;}
		if(obj instanceof File[]) {accessListFiles.p(obj);return;}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}

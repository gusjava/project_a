package a.entity.gus06.file.editor.main.checksize;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F, G {

	public String creationDate() {return "20231026";}

	public static final String PROPKEY = "editor.file.maxsize";
	public static final long DEFAULT_MAXSIZE = 50000000;


	private Service getProp;
	private Service isZip;
	private Long maxSize;
	

	public EntityImpl() throws Exception
	{
		getProp = Outside.service(this,"gus06.app.prop.getlong");
		isZip = Outside.service(this,"gus06.file.filter.ext.istype.archive.zip");
		maxSize = (Long) getProp.r(PROPKEY);
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		File file = (File) obj;
		if(file==null || !file.isFile()) return true;
		if(isZip.f(file)) return true;
		
		long v = findMaxSize();
		return v==-1 || file.length() < v;
	}
	
	
	
	public Object g() throws Exception
	{return findMaxSize();}
	
	
	private long findMaxSize()
	{return maxSize!=null ? maxSize : DEFAULT_MAXSIZE;}
}

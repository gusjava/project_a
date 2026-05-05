package a.entity.gus06.filter.filearray.same;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150702";}


	private Service buildMd5;


	public EntityImpl() throws Exception
	{
		buildMd5 = Outside.service(this,"gus.y.crypto1.hash.md5.hexa");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		File[] ff = (File[]) obj;
		if(ff.length<2) return true;
		
		long size = ff[0].length();
		for(int i=1;i<ff.length;i++) 
		if(ff[i].length()!=size) return false;
		
		String md5 = md5(ff[0]);
		for(int i=1;i<ff.length;i++) 
		if(!md5(ff[i]).equals(md5)) return false;
		
		return true;
	}
	
	
	private String md5(File f) throws Exception
	{return (String) buildMd5.t(f);}
}

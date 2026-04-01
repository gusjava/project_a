package a.entity.gus06.file.neighbours.findfiles.same;

import a.framework.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.FileFilter;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151017";}


	private Service buildMd5;

	public static final FileFilter FILTER = new FileFilter() {
		public boolean accept(File f) {return f.isFile();}
	};
	
	
	
	public EntityImpl() throws Exception
	{buildMd5 = Outside.service(this,"gus06.crypto.hash.md5.hexa.s");}

	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		List l = new ArrayList();
		
		if(!file.isFile()) return l;
		
		File parent = file.getParentFile();
		File[] ff = parent.listFiles(FILTER);
		StringBuffer md5Buff = new StringBuffer();
		
		for(File f:ff) if(!f.equals(file))
		{
			 if(sameContent(f,file,md5Buff)) l.add(f);
		}
		return l;
	}
	
	
	
	private boolean sameContent(File f1, File f2, StringBuffer md5Buff) throws Exception
	{
		if(f1.length()!=f2.length()) return false;
		
		if(md5Buff.length()==0)
		md5Buff.append(md5(f2));
		
		String md5_1 = md5(f1);
		String md5_2 = md5Buff.toString();
		
		return md5_1.equals(md5_2);
	}
	
	
	private String md5(File file) throws Exception
	{return (String) buildMd5.t(file);}
}

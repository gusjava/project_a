package a.entity.gus06.moduledev.refactor.rename.prepare;

import a.framework.*;
import java.io.File;
import java.io.FileFilter;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140926";}


	private File dir;
	
	public EntityImpl() throws Exception
	{
		dir = (File) Outside.resource(this,"path#path.dev.managerdir2");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String name1 = o[0];
		String name2 = o[1];
		
		if(!name1.startsWith("m")) throw new Exception("Invalid module name: "+name1);
		if(!name2.startsWith("m")) throw new Exception("Invalid module name: "+name2);
		
		if(!name1.contains(".")) name1 = extendsName1(name1);
		if(!name1.contains(".")) throw new Exception("Invalid module name: "+name1);
		
		if(!name2.contains(".")) name2 = extendsName2(name2,name1);
		if(!name2.contains(".")) throw new Exception("Invalid module name: "+name2);
		
		return new String[]{name1,name2};
	}
	
	
	
	private String extendsName1(String name) throws Exception
	{
		StringBuffer b = new StringBuffer(name);
		File d = new File(dir,name);
		if(!d.isDirectory()) throw new Exception("Unknown module name: "+name);
		
		FileFilter filter = new FileFilter() {
			public boolean accept(File f) {return f.isDirectory();}
		};
		
		while(d!=null)
		{
			File[] dd = d.listFiles(filter);
			if(dd==null || dd.length==0) d = null;
			else
			{
				d = dd[0];
				b.append("."+d.getName());
			}
		}
		return b.toString();
	}
	
	
	
	
	private String extendsName2(String name2, String name1)
	{
		String[] n = name1.split("\\.",2);
		return name2+"."+n[1];
	}
}

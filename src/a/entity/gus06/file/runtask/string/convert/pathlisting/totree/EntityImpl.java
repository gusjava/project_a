package a.entity.gus06.file.runtask.string.convert.pathlisting.totree;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.Properties;
import java.util.ArrayList;
import java.io.PrintStream;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210131";}


	private Service read;
	private Service splitPath;
	private Service getExtension;

	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus06.file.read.string.list.autodetect");
		splitPath = Outside.service(this,"gus06.file.filepath.split.all");
		getExtension = Outside.service(this,"gus06.file.getextension.lowercase");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File outputFile = new File(file.getAbsolutePath()+".tree");
		PrintStream p = new PrintStream(outputFile);
		
		List listing = (List) read.t(file);
		int nb = listing.size();
		
		String[] path0 = new String[]{};
		if(progress!=null) ((V)progress).v("size",""+nb);
		for(int i=0;i<nb;i++)
		{
			String line = (String) listing.get(i);
			String[] path = (String[]) splitPath.t(line);
			int pathNb = path.length;
			
			boolean same = true;
			for(int j=0;j<pathNb;j++)
			{
				String part = path[j];
				if(path0.length<=j || !path0[j].equals(part)) same = false;
				if(!same)
				{
					String iconKey = j==pathNb-1 ? iconKeyForFile(part) : "dir";
					p.println(offset(j)+iconKey+"#"+part);
				}
			}
			path0 = path;
			if(progress!=null) ((E)progress).e();
		}
	}
	
	
	private String iconKeyForFile(String fileName) throws Exception
	{return "FILE_"+getExtension.t(fileName);}
	
	
	private String offset(int level)
	{
		StringBuffer b = new StringBuffer("@");
		for(int i=0;i<level;i++) b.append("@");
		return b.toString();
	}
}

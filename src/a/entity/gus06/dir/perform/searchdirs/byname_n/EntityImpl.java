package a.entity.gus06.dir.perform.searchdirs.byname_n;

import a.framework.*;
import java.util.List;
import java.io.File;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160413";}
	
	private Service normalize;

	public EntityImpl() throws Exception
	{normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");}
	
	private String normalize(String s) throws Exception
	{return (String) normalize.t(s);}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File input =(File) o[0];
		String name = normalize((String) o[1]);
		
		List output = new ArrayList();
		handle(name,output,input);
		return output;
	}
	
	private void handle(String name, List output, File input) throws Exception
	{
		if(input.isDirectory())
		{
			File[] ff = input.listFiles();
			for(int i=0;i<ff.length;i++)
			handle(name,output,ff[i]);
			
			if(filter(input,name)) output.add(input);
		}
	}
	
	private boolean filter(File dir, String name) throws Exception
	{
		return normalize(dir.getName()).equals(name);
	}
}

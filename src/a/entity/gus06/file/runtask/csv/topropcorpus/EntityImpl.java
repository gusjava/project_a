package a.entity.gus06.file.runtask.csv.topropcorpus;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190322";}


	private Service readFile;
	private Service writeProp;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string.csv.autodetect");
		writeProp = Outside.service(this,"gus06.dir.access.write.properties.randomid");
	}


	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File dir = new File(file.getAbsolutePath()+"_propcorpus");
		dir.mkdirs();
		
		String[][] data = (String[][]) readFile.t(file);
		if(data.length==0) return;
		
		String[] header = data[0];
		if(progress!=null) ((V)progress).v("size",""+(data.length-1));
		
		for(int i=1;i<data.length;i++)
		{
			String[] row = data[i];
			
			Map prop = buildProp(header,row);
			writeProp.p(new Object[]{dir,prop});
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
	
	
	private Map buildProp(String[] header, String[] row)
	{
		Map map = new HashMap();
		for(int i=0;i<header.length;i++)
		map.put(header[i],row[i]);
		return map;
	}
}
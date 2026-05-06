package a.entity.gus06.dir.runtask.corpus.properties.apply.script1;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150926";}


	private Service listing;
	private Service applyScript;
	private Service readString;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.properties");
		applyScript = Outside.service(this,"gus06.file.properties.perform.apply.script1");
		readString = Outside.service(this,"gus.x.file.string.read.v1");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File parent = dir.getParentFile();
		File scriptFile = new File(parent,"script.txt");
		String script = readFromFile(scriptFile);
		
		
		File[] ff = (File[]) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+ff.length);
		
		for(File f:ff)
		{
			applyScript.p(new Object[]{f,script});
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
	
	
	private String readFromFile(File f) throws Exception
	{
		if(!f.exists()) f.createNewFile();
		return (String) readString.t(f);
	}
}

package a.entity.gus06.dir.runtask.corpus.properties.search.vco_i;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.Iterator;
import java.io.PrintStream;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190702";}


	private Service listing;
	private Service buildFilter;
	private Service getInput;
	private Service readProp;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.properties");
		buildFilter = Outside.service(this,"gus06.filter.string.build.contains_i");
		getInput = Outside.service(this,"gus06.input.text.dialog");
		readProp = Outside.service(this,"gus06.file.read.properties");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		String input = (String) getInput.g();
		F filter = (F) buildFilter.t(input);
		
		File[] ff = (File[]) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+ff.length);
		
		File resultFile = new File(dir+"_result.txt");
		PrintStream p = new PrintStream(resultFile);
		
		for(File f:ff)
		{
			Map prop = (Map) readProp.t(f);
			Iterator it = prop.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				String value = (String) prop.get(key);
				
				if(filter.f(value))
				p.println(f.getName()+"\t"+key+"="+value);
			}
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		p.close();
	}
}

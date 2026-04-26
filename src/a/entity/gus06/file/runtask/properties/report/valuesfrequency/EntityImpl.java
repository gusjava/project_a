package a.entity.gus06.file.runtask.properties.report.valuesfrequency;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.io.PrintStream;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150923";}


	private Service read;
	private Service buildFreqMap;
	private Service buildSortedKeys;

	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus.x.file.prop.read");
		buildFreqMap = Outside.service(this,"gus06.map.intmap.build.freqmap");
		buildSortedKeys = Outside.service(this,"gus06.map.build.sortedkeys.byvalue");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		Map prop = (Map) read.t(file);
		Map freqMap = (Map) buildFreqMap.t(prop);
		List keys = (List) buildSortedKeys.t(freqMap);
		int number = keys.size();
		
		File reportFile = new File(file.getAbsolutePath()+"_valuesfrequency.txt");
		PrintStream p = new PrintStream(reportFile);
		
		if(progress!=null) ((V)progress).v("size",""+number);
		
		for(int i=0;i<number;i++)
		{
			String key = (String) keys.get(number-i-1);
			Integer value = (Integer) freqMap.get(key);
			
			p.println(value+"\t"+key);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		
		p.close();
	}
}

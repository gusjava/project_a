package a.entity.gus06.dir.runtask.gitrepo.commitreport.csv;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201126";}


	private Service gitBuilder;
	private Service formatter;
	private Service formatDate;
	private Service formatMessage;
	
	public EntityImpl() throws Exception
	{
		gitBuilder = Outside.service(this,"gus06.sys.git1.builder");
		formatter = Outside.service(this,"gus06.io.printstream.formatter.csv1");
		formatDate = Outside.service(this,"gus06.time.date.yyyymmdd_hhmmss");
		formatMessage = Outside.service(this,"gus06.string.transform.format.uniline");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File csvFile = new File(dir.getAbsolutePath()+".csv");
		PrintStream p = (PrintStream) formatter.t(csvFile);
		
		R gitHolder = (R) gitBuilder.t(dir);
		List commits = (List) gitHolder.r("commits");
		((P)gitHolder).p("close");
		
		String[] header = buildHeader();
		p.println(header);
		
		int size = commits.size();
		if(progress!=null) ((V)progress).v("size",""+size);
		
		for(int i=0;i<size;i++)
		{
			if(interrupt!=null && !interrupt.isEmpty())
			{p.close();return;}
			
			Map commitMap = (Map) commits.get(i);
			String[] row = buildRow(commitMap);
			p.println(row);
			
			if(progress!=null) ((E)progress).e();
		}
		
		p.close();
	}
	
	private String[] buildHeader()
	{
		return new String[]{"name","time","author","email","message"};
	}
	
	private String[] buildRow(Map map) throws Exception
	{
		String name = getString(map,"name");
		String time = getTimeStamp(map,"time");
		String author = getString(map,"author");
		String email = getString(map,"email");
		String message = getMessage(map,"message");
		
		return new String[]{name,time,author,email,message};
	}
	
	
	private String getString(Map map, String key)
	{return map.containsKey(key) ? (String) map.get(key) : "";}
	
	private String getMessage(Map map, String key) throws Exception
	{return map.containsKey(key) ? (String) formatMessage.t(map.get(key)) : "";}
	
	private String getTimeStamp(Map map, String key) throws Exception
	{return map.containsKey(key) ? (String) formatDate.t(map.get(key)) : "";}
}

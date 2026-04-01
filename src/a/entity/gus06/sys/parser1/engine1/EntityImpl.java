package a.entity.gus06.sys.parser1.engine1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150829";}

	public final static String ESCAPE = "\\";



	private Service splitEngine;
	private Service regroupEngine;
	

	public EntityImpl() throws Exception
	{
		splitEngine = Outside.service(this,"gus06.sys.parser1.process.split");
		regroupEngine = Outside.service(this,"gus06.sys.parser1.process.regroup");
	}
	
	
	public Object t(Object obj) throws Exception
	{return new T1((String) obj);}
	
	
	
	private class T1 implements T
	{
		private T splitter;
		private T regrouper;
		
		public T1(String info) throws Exception
		{
			if(info.length()==1) info = ESCAPE+info+info;
			if(info.length()==2) info = ESCAPE+info;
			if(info.length()!=3) throw new Exception("Invalid info: "+info);
			
			splitter = (T) splitEngine.t(info);
			regrouper = (T) regroupEngine.t(info.substring(1,3));
		}
		
		public Object t(Object obj) throws Exception
		{
			String s = (String) obj;
			Object list = splitter.t(s);
			return regrouper.t(list);
		}
	}
}
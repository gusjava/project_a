package a.entity.gus06.string.parser.builder;

import a.framework.*;
import java.util.Map;
import java.io.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140818";}

	public static final String INITRULE = "main";
	

	private Service findReader;
	private Service compute;
	
	public EntityImpl() throws Exception
	{
		findReader = Outside.service(this,"gus06.find.reader");
		compute = Outside.service(this,"gus06.string.parser.builder.compute");
	}
	
	
	public Object t(Object obj) throws Exception
	{return new Parser((Map) obj);}
	
	
	
	private class Parser implements T
	{
		private Map map;
		public Parser(Map map) {this.map = map;}
		
		public Object t(Object obj) throws Exception
		{
			PushbackReader pr = null;
			Object result = null;
			
			try
			{
				Reader reader = (Reader) findReader.t(obj);
				pr = new PushbackReader(reader,100);
				result = compute.t(new Object[]{pr,map,INITRULE});
			}
			finally {if(pr!=null) pr.close();}
			
			return result;
		}
	}
}

package a.entity.gus06.appli.gusclient1.project.config.entities.listing0.f2;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150312";}
	
	public static final String KEY = "deploy.entities";
	
	
	public static final F FALSEFILTER = new F(){
		public boolean f(Object obj) throws Exception
		{return false;}
	};



	private Service getInfo;
	
	public EntityImpl() throws Exception
	{getInfo = Outside.service(this,"gus06.appli.gusclient1.project.config.load1.info");}
	
	
	
	public Object g() throws Exception
	{
		Map info = (Map) getInfo.g();
		
		if(info==null) return FALSEFILTER;
		if(!info.containsKey(KEY)) return FALSEFILTER;
		
		String value = (String) info.get(KEY);
		String[] nn = value.split(";");
		
		return new Filter(nn);
	}
	
	
	
	
	private class Filter implements F
	{
		private String[] nn;
		public Filter(String[] nn) {this.nn = nn;}
		
		public boolean f(Object obj) throws Exception
		{
			String value = (String) obj;
			for(String n:nn) if(value.startsWith(n)) return true;
			return false;
		}
	}
}

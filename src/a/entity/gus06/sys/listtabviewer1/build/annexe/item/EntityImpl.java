package a.entity.gus06.sys.listtabviewer1.build.annexe.item;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200405";}
	
	public static final String KEY_VIEWER = "viewer";
	public static final String KEY_FORMATTER = "formatter";


	private Service factory;

	public EntityImpl() throws Exception
	{
		factory = Outside.service(this,"factory#gus06.data.viewer.object");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return new Holder((Map) obj);
	}
	
	
	
	private class Holder implements I, P
	{
		private Map conf;
		private T formatter;
		private Object viewer;
		
		public Holder(Map conf) throws Exception
		{
			this.conf = conf;
			
			viewer = conf.containsKey(KEY_VIEWER) ? conf.get(KEY_VIEWER) : factory.g();
			formatter = conf.containsKey(KEY_FORMATTER) ? (T) conf.get(KEY_FORMATTER) : null;
		}
		
		public Object i() throws Exception
		{return ((I) viewer).i();}
		
		
		public void p(Object obj) throws Exception
		{
			if(formatter!=null) obj = formatter.t(obj);
			((P) viewer).p(obj);
		}
	}
}

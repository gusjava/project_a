package a.entity.gus06.maincust.ref;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251019";}


	private Service ref;
	private Service wrap;
	
	public EntityImpl() throws Exception
	{
		ref = Outside.service(this,"gus06.sys.cache1");
		wrap = Outside.service(this,"gus06.service.wrapper3");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String buildInfo = (String) o[0];
		Object src = o[1];
		
		return wrap.t(new Holder(buildInfo));
	}
	
	private class Holder implements G
	{
		private String buildInfo;
		public Holder(String buildInfo) {this.buildInfo = buildInfo;}
		
		public Object g() throws Exception
		{
			Object result = ref.r(buildInfo);
			if(result==null) throw new Exception("Failed to build result with buildInfo="+buildInfo);
			return result;
		}
	}
}
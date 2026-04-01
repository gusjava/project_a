package a.entity.gus06.sys.expression1.apply.op._extract_in_htmlblock_k;

import java.util.regex.Pattern;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220216";}


	private Service build;
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus06.string.extract.html.block.buildpattern");
		perform = Outside.service(this,"gus06.string.extract.match.full.g2");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return new T1((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private String text;
		public T1(String text) throws Exception
		{this.text = text;}
		
		public Object t(Object obj) throws Exception
		{
			Pattern p = (Pattern) build.t(obj);
			return perform.t(new Object[]{text,p});
		}
	}
}
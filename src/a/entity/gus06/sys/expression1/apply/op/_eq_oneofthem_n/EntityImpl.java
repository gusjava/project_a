package a.entity.gus06.sys.expression1.apply.op._eq_oneofthem_n;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160503";}


	private Service cuttingMethod;
	private Service normalize;
	private Service readText;
	
	public EntityImpl() throws Exception
	{
		cuttingMethod = Outside.service(this,"gus06.string.split.method1");
		normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");
		readText = Outside.service(this,"gus06.file.read.string.generic");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return new F1((String) obj);
		if(obj instanceof File) return new F1((String) readText.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class F1 implements F
	{
		private String value;
		
		public F1(String value) throws Exception
		{this.value = (String) normalize.t(value);}
		
		public boolean f(Object obj) throws Exception
		{
			String s0 = (String) normalize.t(obj);
			String[] words = (String[]) cuttingMethod.t(s0);
			for(int i=0;i<words.length;i++)
				if(value.equals(words[i])) return true;
			return false;
		}
	}
}

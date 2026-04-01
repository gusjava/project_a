package a.entity.gus06.sys.expression1.apply.op._nco_allofthem;

import a.framework.*;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180408";}
	
	public static final boolean RESULT_FOR_NULL = true;

	
	private Service cuttingMethod;
	private Service readText;
	private Service listToArray;
	
	public EntityImpl() throws Exception
	{
		cuttingMethod = Outside.service(this,"gus06.string.split.method1");
		readText = Outside.service(this,"gus06.file.read.string.generic");
		listToArray = Outside.service(this,"gus06.convert.listtostringarray");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return new F1(null);
		if(obj instanceof String) return new F1((String) obj);
		if(obj instanceof File) return new F1((String) readText.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class F1 implements F
	{
		private String value;
		
		public F1(String value) throws Exception
		{this.value = value;}
		
		public boolean f(Object obj) throws Exception
		{
			if(value==null) return RESULT_FOR_NULL;
			String[] words = toWords(obj);
			for(int i=0;i<words.length;i++)
				if(!value.contains(words[i])) return true;
			return false;
		}
	}
	
	private String[] toWords(Object input) throws Exception
	{
		if(input instanceof String[]) return (String[]) input;
		if(input instanceof List) return (String[]) listToArray.t(input);
		if(input instanceof String) return (String[]) cuttingMethod.t(input);
		
		throw new Exception("Invalid input type: "+input.getClass().getName());
	}
}

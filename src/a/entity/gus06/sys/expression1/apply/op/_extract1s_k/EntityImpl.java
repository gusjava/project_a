package a.entity.gus06.sys.expression1.apply.op._extract1s_k;

import a.framework.*;
import java.io.File;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190405";}


	private Service readText;
	private Service perform;

	public EntityImpl() throws Exception
	{
		readText = Outside.service(this,"gus06.file.read.string.generic");
		perform = Outside.service(this,"gus06.string.extract.extract1.find.s.k");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return new T1((String) obj);
		if(obj instanceof File) return new T1((String) readText.t(obj));
		if(obj instanceof JTextComponent) return new T1(((JTextComponent) obj).getText());
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private String data;
		public T1(String data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{
			String rule = (String) obj;
			return perform.t(new String[]{data,rule});
		}
	}
}

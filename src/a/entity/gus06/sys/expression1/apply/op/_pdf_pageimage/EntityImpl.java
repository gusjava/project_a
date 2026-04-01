package a.entity.gus06.sys.expression1.apply.op._pdf_pageimage;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191116";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.file.pdf.pdfbox.page.asimage");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof File) return new T1((File) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private File file;
		public T1(File file)
		{this.file = file;}
		
		public Object t(Object obj) throws Exception
		{
			Integer n = toInt(obj);
			return perform.t(new Object[]{file,n});
		}
	}
	
	
	private Integer toInt(Object obj) throws Exception
	{
		if(obj instanceof Integer) return (Integer) obj;
		if(obj instanceof String) return Integer.valueOf((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}

package a.entity.gus06.data.perform.add0;

import a.framework.*;
import java.util.List;
import java.io.File;
import javax.swing.text.JTextComponent;
import javax.swing.text.Document;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20160130";}
	
	
	private Service performFile;
	private Service performList;
	
	public EntityImpl() throws Exception
	{
		performFile = Outside.service(this,"gus06.file.write.string.append0.autodetect");
		performList = Outside.service(this,"gus06.list.add0");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof File)		{performFile.p(o);return;}
		if(input instanceof List)		{performList.p(o);return;}
		if(input instanceof StringBuffer)	{performSb(o);return;}
		if(input instanceof JTextComponent)	{performTextComp(o);return;}
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof List)
			return performList.t(obj);
		
		if(input instanceof String)
		{
			String s = (String) o[1];
			return s+input;
		}
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	
	
	private void performSb(Object[] o)
	{
		StringBuffer sb = (StringBuffer) o[0];
		String s = (String) o[1];
		
		sb.insert(0,s);
	}
	
	
	private void performTextComp(Object[] o) throws Exception
	{
		JTextComponent comp = (JTextComponent) o[0];
		String s = (String) o[1];
		
		Document doc = comp.getDocument();
		doc.insertString(0,s,null);
	}
}

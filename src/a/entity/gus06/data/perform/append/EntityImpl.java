package a.entity.gus06.data.perform.append;

import a.framework.*;
import java.util.Collection;
import java.util.Map;
import java.io.File;
import javax.swing.text.JTextComponent;
import javax.swing.text.Document;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151204";}


	private Service appendToFileTxt;

	public EntityImpl() throws Exception
	{
		appendToFileTxt = Outside.service(this,"gus06.file.write.string.append.autodetect");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		append(o[0],o[1]);
	}
	
	
	private void append(Object data, Object value) throws Exception
	{
		if(data instanceof StringBuffer)
		{append((StringBuffer) data,value);return;}
		
		if(data instanceof StringBuilder)
		{append((StringBuilder) data,value);return;}
		
		if(data instanceof Collection)
		{append((Collection) data,value);return;}
		
		if(data instanceof Map) 
		{append((Map) data,value);return;}
		
		if(data instanceof File)
		{append((File) data,value);return;}
		
		if(data instanceof JTextComponent)
		{append((JTextComponent) data,(String) value);return;}
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	
	private void append(StringBuffer b, Object value)
	{
		if(value!=null)
		b.append(value.toString());
	}
	
	private void append(StringBuilder b, Object value)
	{
		if(value!=null)
		b.append(value.toString());
	}
	
	private void append(Collection c, Object value)
	{
		if(value!=null)
		c.addAll((Collection) value);
	}
	
	private void append(Map m, Object value)
	{
		if(value!=null)
		m.putAll((Map) value);
	}
	
	private void append(File f, Object value) throws Exception
	{
		if(f.isDirectory()) throw new Exception("Directory not supported yet: "+f);
		if(f.isFile()) appendToFile(f,value);
	}
	
	private void append(JTextComponent comp, String value) throws Exception
	{
		if(value==null) return;
		
		Document doc = comp.getDocument();
		int len = doc.getLength();
		doc.insertString(len,value,null);
	}
	
	
	
	private void appendToFile(File f, Object value) throws Exception
	{
		if(value==null) return;
		
		if(value instanceof String) {appendToFileTxt.p(new Object[]{f,value});return;}
		if(value instanceof Map) {throw new Exception("Not supported yet");}
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
}

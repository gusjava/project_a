package a.entity.gus06.data.perform.change;

import a.framework.*;
import java.util.Collection;
import java.util.Map;
import java.io.File;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151204";}


	private Service writeFile;


	public EntityImpl() throws Exception
	{
		writeFile = Outside.service(this,"gus06.file.write.generic");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		change(o[0],o[1]);
	}
	
	
	private void change(Object data, Object value) throws Exception
	{
		if(data instanceof StringBuffer)
		{change((StringBuffer) data,value);return;}
		
		if(data instanceof StringBuilder)
		{change((StringBuilder) data,value);return;}
		
		if(data instanceof Collection)
		{change((Collection) data,value);return;}
		
		if(data instanceof Map) 
		{change((Map) data,value);return;}
		
		if(data instanceof File)
		{change((File) data,value);return;}
		
		if(data instanceof JTextComponent)
		{change((JTextComponent) data,value);return;}
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	
	private void change(StringBuffer b, Object value)
	{
		b.setLength(0);
		b.append(value.toString());
	}
	
	private void change(StringBuilder b, Object value)
	{
		b.setLength(0);
		b.append(value.toString());
	}
	
	private void change(Collection c, Object value)
	{
		c.clear();
		c.addAll((Collection) value);
	}
	
	private void change(Map m, Object value)
	{
		m.clear();
		m.putAll((Map) value);
	}
	
	private void change(File f, Object value) throws Exception
	{
		if(f.isDirectory()) throw new Exception("Directory not supported yet: "+f);
		writeFile.p(new Object[]{f,value});
	}
	
	private void change(JTextComponent c, Object value) throws Exception
	{
		c.setText((String) value);
	}
}

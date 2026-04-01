package a.entity.gus06.data.perform.append0;

import a.framework.*;
import java.util.Collection;
import java.util.Map;
import java.io.File;
import java.util.Iterator;
import javax.swing.text.JTextComponent;
import javax.swing.text.Document;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160312";}


	private Service appendToFileTxt;

	public EntityImpl() throws Exception
	{
		appendToFileTxt = Outside.service(this,"gus06.file.write.string.append0.autodetect");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		appendObj(o[0],o[1]);
	}
	
	
	private void appendObj(Object data, Object value) throws Exception
	{
		if(data instanceof StringBuffer)
		{
			StringBuffer sb = (StringBuffer) data;
			append0(sb,value);
			return;
		}
		if(data instanceof StringBuilder)
		{
			StringBuilder sb = (StringBuilder) data;
			append0(sb,value);
			return;
		}
		if(data instanceof Collection)
		{
			Collection c = (Collection) data;
			Collection c0 = (Collection) value;
			append0(c,c0);
			return;
		}
		if(data instanceof Map) 
		{
			Map m = (Map) data;
			Map m0 = (Map) value;
			append0(m,m0);
			return;
		}
		if(data instanceof File) 
		{
			File f = (File) data;
			append0(f,value);
			return;
		}
		if(data instanceof JTextComponent) 
		{
			JTextComponent c = (JTextComponent) data;
			append0(c,(String) value);
			return;
		}
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	
	private void append0(StringBuffer sb, Object value)
	{
		if(value==null) return;
		if(sb.length()>0)
		sb.append(value.toString());
	}
	
	private void append0(StringBuilder sb, Object value)
	{
		if(value==null) return;
		if(sb.length()>0) 
		sb.append(value.toString());
	}
	
	private void append0(Collection c, Collection c0)
	{
		if(c0==null) return;
		Iterator it = c0.iterator();
		while(it.hasNext())
		{
			Object element = it.next();
			if(!c.contains(element)) c.add(element);
		}
	}
	
	private void append0(Map m, Map m0)
	{
		if(m0==null) return;
		Iterator it = m0.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			if(!m.containsKey(key)) m.put(key,m0.get(key));
		}
	}
	
	private void append0(File f, Object value) throws Exception
	{
		if(f.isDirectory()) throw new Exception("Directory not supported yet: "+f);
		if(f.isFile()) appendToFile0(f,value);
	}
	
	private void append0(JTextComponent comp, String value) throws Exception
	{
		if(value==null) return;
		Document doc = comp.getDocument();
		doc.insertString(0,value,null);
	}
	
	
	
	private void appendToFile0(File f, Object value) throws Exception
	{
		if(value==null) return;
		if(value instanceof String) {appendToFileTxt.p(new Object[]{f,value});return;}
		if(value instanceof Map) {throw new Exception("Not supported yet");}
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
}

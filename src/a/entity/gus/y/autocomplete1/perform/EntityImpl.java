package a.entity.gus.y.autocomplete1.perform;

import a.framework.*;
import java.util.Set;
import java.util.Iterator;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240713";}
	
	public static final String CARET = "<CARET>";
	
	private Service addImport;
	
	public EntityImpl() throws Exception
	{
		addImport = Outside.service(this,"gus.y.addjavaimport1.handle");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		String query = (String) o[1];
		
		if(query.equals("o"))
		{
			return "Object[] o = (Object[]) obj;\n\t\tif(o.length!=2) throw new Exception(\"Wrong data number: \"+o.length);";
		}
		if(query.equals("c"))
		{
			return "["+CARET+"]";
		}
		if(query.equals("v"))
		{
			return "<"+CARET+">";
		}
		if(query.equals("sysout"))
		{
			return "System.out.println(\""+CARET+"\");";
		}
		if(query.equals("milli"))
		{
			return "System.currentTimeMillis();";
		}
		if(query.equals("th"))
		{
			return "throws Exception";
		}
		if(query.equals("thn"))
		{
			return "throw new Exception(\""+CARET+"\");";
		}
		if(query.equals("ser"))
		{
			return "Outside.service(this,\""+CARET+"\");";
		}
		if(query.equals("res"))
		{
			return "Outside.resource(this,\""+CARET+"\");";
		}
		if(query.equals("send"))
		{
			return "send(this,\""+CARET+"\");";
		}
		if(query.equals("err"))
		{
			return "Outside.err(this,\""+CARET+"\",e);";
		}
		if(query.equals("bp"))
		{
			addImport.v("javax.swing.JPanel", comp);
			addImport.v("java.awt.BorderLayout", comp);
			return "new JPanel(new BorderLayout());";
		}
		if(query.equals("gp"))
		{
			addImport.v("javax.swing.JPanel", comp);
			addImport.v("java.awt.GridLayout", comp);
			return "new JPanel(new GridLayout(1,1));";
		}
		if(query.equals("ar"))
		{
			addImport.v("java.util.ArrayList", comp);
			return "new ArrayList("+CARET+");";
		}
		if(query.equals("al"))
		{
			return "addActionListener(e->"+CARET+");";
		}
		
		return query;
	}
}

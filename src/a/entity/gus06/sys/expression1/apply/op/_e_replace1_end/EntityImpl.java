package a.entity.gus06.sys.expression1.apply.op._e_replace1_end;

import a.framework.*;
import java.io.File;
import javax.swing.text.JTextComponent;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230121";}


	private Service perform;
	private Service fileAccess;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.string.replace1.end");
		fileAccess = Outside.service(this,"gus06.file.access.string.autodetect");
	}


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof JTextComponent) return new T1(obj);
		if(obj instanceof File) return new T1(obj);
		if(obj instanceof StringBuffer) return new T1(obj);
		if(obj instanceof StringBuilder) return new T1(obj);
		if(obj instanceof List) return new T1(obj);
		if(obj instanceof String[]) return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object base;
		
		public T1(Object base)
		{this.base = base;}
		
		public Object t(Object obj) throws Exception
		{return new T2(base, (String) obj);}
	}
	
	private class T2 implements T
	{
		private Object base;
		private String search;
		
		public T2(Object base, String search)
		{
			this.base = base;
			this.search = search;
		}
		
		public Object t(Object obj) throws Exception
		{return new E1(base, search, (String) obj);}
	}
	
	private class E1 implements E
	{
		private Object base;
		private String search;
		private String replace;
		
		public E1(Object base, String search, String replace)
		{
			this.base = base;
			this.search = search;
			this.replace = replace;
		}
		
		public void e() throws Exception
		{
			if(base instanceof JTextComponent)
				handleJTextComponent((JTextComponent) base, search, replace);
			else if(base instanceof File)
				handleFile((File) base, search, replace);
			else if(base instanceof StringBuffer)
				handleStringBuffer((StringBuffer) base, search, replace);
			else if(base instanceof StringBuilder)
				handleStringBuilder((StringBuilder) base, search, replace);
			else if(base instanceof List)
				handleList((List) base, search, replace);
			else if(base instanceof String[])
				handleStringArray((String[]) base, search, replace);
			else throw new Exception("Invalid base type: "+base.getClass().getName());
		}
	}
	
	
	
		
	private void handleJTextComponent(JTextComponent comp, String search, String replace) throws Exception
	{
		String input = comp.getText();
		int p = comp.getCaretPosition();
		
		String output = (String) perform.t(new String[]{input, search, replace});
		
		comp.setText(output);
		comp.setCaretPosition(Math.min(p,output.length()));
	}
	
	private void handleFile(File file, String search, String replace) throws Exception
	{
		Object access = fileAccess.t(file);
		String input = (String) ((G)access).g();
		
		String output = (String) perform.t(new String[]{input, search, replace});
		if(!output.equals(input)) ((P)access).p(output);
	}
	
	private void handleStringBuffer(StringBuffer sb, String search, String replace) throws Exception
	{
		String input = sb.toString();
		String output = (String) perform.t(new String[]{input, search, replace});
		
		sb.delete(0, sb.length());
		sb.append(output);
	}
	
	private void handleStringBuilder(StringBuilder sb, String search, String replace) throws Exception
	{
		String input = sb.toString();
		String output = (String) perform.t(new String[]{input, search, replace});
		
		sb.delete(0, sb.length());
		sb.append(output);
	}
	
	private void handleList(List list, String search, String replace) throws Exception
	{
		List output = new ArrayList();
		for(int i=0;i<list.size();i++)
		{
			String s1 = (String) list.get(i);
			String s2 = (String) perform.t(new String[]{s1, search, replace});
			output.add(s2);
		}
		list.clear();
		list.addAll(output);
	}
	
	private void handleStringArray(String[] array, String search, String replace) throws Exception
	{
		for(int i=0;i<array.length;i++)
		array[i] = (String) perform.t(new String[]{array[i], search, replace});
	}
}
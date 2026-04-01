package a.entity.gus06.sys.expression1.apply.op._selection_trans;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220501";}

	
	private Service builder;
	
	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.sys.expression1.builder2.t");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof JTextComponent) return new T1((JTextComponent) value, opMap);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private JTextComponent comp;
		private Map opMap;
		
		public T1(JTextComponent comp, Map opMap)
		{
			this.comp = comp;
			this.opMap = opMap;
		}
		
		public Object t(Object obj) throws Exception
		{return new E1(comp,toT(obj));}
		
		private T toT(Object obj) throws Exception
		{return (T) builder.t(new Object[]{obj,opMap});}
	}
	
	
	private class E1 implements E
	{
		private JTextComponent comp;
		private T trans;
		
		public E1(JTextComponent comp, T trans)
		{
			this.comp = comp;
			this.trans = trans;
		}
		
		public void e() throws Exception
		{
			String selected = comp.getSelectedText();
			if(selected==null) return;
			
			String s = (String) trans.t(selected);
			
			int start = comp.getSelectionStart();
			int end = start+s.length();
			comp.replaceSelection(s);
			comp.select(start,end);
		}
	}
}

package a.entity.gus06.data.perform.insert;

import a.framework.*;
import java.util.List;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20160131";}
	
	
	private Service performList;
	private Service performTextComponent;
	private Service ruleToIndex;
	
	public EntityImpl() throws Exception
	{
		performList = Outside.service(this,"gus06.list.insert");
		performTextComponent = Outside.service(this,"gus06.swing.textcomp.perform3.insert");
		ruleToIndex = Outside.service(this,"gus06.list.ruletoindex");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof List)
			performList.p(o);
			
		else if(input instanceof StringBuffer)
			performStringBuffer(o);
			
		else if(input instanceof StringBuilder)
			performStringBuilder(o);
			
		else if(input instanceof JTextComponent)
			performTextComponent.p(o);
		
		else throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof List)
			return performList.t(o);
			
		if(input instanceof String)
			return performString(o);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	
	
	private String performString(Object[] o) throws Exception
	{
		String s = (String) o[0];
		Object index = o[1];
		String data = (String) o[2];
		
		Integer index1 = (Integer) ruleToIndex.t(new Object[]{s,index});
		return s.substring(0, index1) + data + s.substring(index1);
	}
	
	
	private void performStringBuffer(Object[] o) throws Exception
	{
		StringBuffer s = (StringBuffer) o[0];
		Object index = o[1];
		String data = (String) o[2];
		
		Integer index1 = (Integer) ruleToIndex.t(new Object[]{s,index});
		s.insert(index1,data);
	}
	
	
	private void performStringBuilder(Object[] o) throws Exception
	{
		StringBuilder s = (StringBuilder) o[0];
		Object index = o[1];
		String data = (String) o[2];
		
		Integer index1 = (Integer) ruleToIndex.t(new Object[]{s,index});
		s.insert(index1,data);
	}
}
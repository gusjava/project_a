package a.entity.gus06.sys.script1.executor.type.el.r.switch1.content.analyze;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160305";}

	public static final String T_ELEMENT = "element";
	
	public static final String CASES = "cases";
	public static final String CASE = "case";
	public static final String ELSE = "else";
	public static final String ELSEIF = "elseif";
	
	
	
	private Service getName;
	private Service getType;
	private Service getParams;
	private Service evaluate;
	

	public EntityImpl() throws Exception
	{
		getName = Outside.service(this,"gus06.sys.script1.access.tag.name1");
		getType = Outside.service(this,"gus06.sys.script1.access.tag.type1");
		getParams = Outside.service(this,"gus06.sys.script1.access.tag.params1");
		evaluate = Outside.service(this,"gus06.sys.script1.context.evaluate");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map context = (Map) o[0];
		List content = (List) o[1];
	
		List cases = new ArrayList();
		List elseC = null;
		List caseC = null;
		F filter = null;
		
		for(int i=0;i<content.size();i++)
		{
			Map child = (Map) content.get(i);
			
			if(isTextTag(child))
			{
				if(elseC!=null) elseC.add(child);
				else if(caseC!=null) caseC.add(child);
			}
			else if(isElseTag(child))
			{
				if(elseC==null) elseC = new ArrayList();
				else throw new Exception("Invalid switch tag content: many else found");
			}
			else if(isElseIfTag(child))
			{
				throw new Exception("Invalid switch tag content: elseif found");
			}
			else if(isCaseTag(child))
			{
				if(caseC!=null && filter!=null)
				cases.add(new Object[]{filter,caseC});
				
				filter = tagToFilter(context,child);
				caseC = new ArrayList();
			}
			else
			{
				if(elseC!=null) elseC.add(child);
				else if(caseC!=null) caseC.add(child);
				else throw new Exception("Invalid switch tag content: starting with non-case tag");
			}
		}
		
		if(caseC!=null && filter!=null)
		cases.add(new Object[]{filter,caseC});
		
		Map result = new HashMap();
		result.put(CASES,cases);
		if(elseC!=null) result.put(ELSE,elseC);
		
		return result;
	}
	
	
	
	private F tagToFilter(Map context, Map tag) throws Exception
	{
		String params = tagParams(tag);
		Object data = evaluate.t(new Object[]{context,params});
		if(data instanceof F) return (F) data;
		return new F1(data);
	}
	
	
	private boolean isTextTag(Map child) throws Exception
	{
		return !tagType(child).equals(T_ELEMENT);
	}
	
	private boolean isCaseTag(Map child) throws Exception
	{
		if(!tagType(child).equals(T_ELEMENT)) return false;
		return tagName(child).equals(CASE);
	}
	
	private boolean isElseTag(Map child) throws Exception
	{
		if(!tagType(child).equals(T_ELEMENT)) return false;
		return tagName(child).equals(ELSE);
	}
	
	private boolean isElseIfTag(Map child) throws Exception
	{
		if(!tagType(child).equals(T_ELEMENT)) return false;
		return tagName(child).equals(ELSEIF);
	}
	
	
	private String tagType(Map child) throws Exception
	{return (String) getType.t(child);}
	
	private String tagName(Map child) throws Exception
	{return (String) getName.t(child);}
	
	private String tagParams(Map child) throws Exception
	{return (String) getParams.t(child);}
	
	
	
	private class F1 implements F
	{
		private Object data;
		public F1(Object data) {this.data = data;}
		
		public boolean f(Object obj)
		{return equals_(data,obj);}
	}
	
	private boolean equals_(Object o1, Object o2)
	{
		if(o1==null && o2==null) return true;
		if(o1==null || o2==null) return false;
		return o1.equals(o2);
	}
}

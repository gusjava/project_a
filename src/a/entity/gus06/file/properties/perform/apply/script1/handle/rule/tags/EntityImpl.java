package a.entity.gus06.file.properties.perform.apply.script1.handle.rule.tags;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150926";}

	
	
	private Service buildTrans;
	private Service parser;

	public EntityImpl() throws Exception
	{
		buildTrans = Outside.service(this,"gus06.string.transformfinder.fromsequence");
		parser = Outside.service(this,"gus06.sys.parser1.engine1.impl.brackets.curly");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String rule = (String) o[0];
		Map values = (Map) o[1];
		
		List seq = (List) parser.t(rule);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<seq.size();i++)
		{
			String value = (String) seq.get(i);
			if(i%2==1) value = handleTag1(value,values);
			b.append(value);
		}
		return b.toString();
	}
	
	
	
	private String handleTag1(String tag, Map values) throws Exception
	{
		String[] n = tag.split("#",2);
		String trans = n.length==2?n[0]:null;
		String tag2 = n.length==2?n[1]:tag;
		
		String value = handleTag2(tag2,values);
		if(trans==null) return value;
		
		T t = (T) buildTrans.t(trans);
		return (String) t.t(value);
	}
	
	
	
	private String handleTag2(String tag, Map values) throws Exception
	{
		String strVal = extractStrValue(tag);
		if(strVal!=null) return strVal;
		
		String[] n = tag.split(":",2);
		String key = n.length==2?n[0]:tag;
		String defaultTag = n.length==2?n[1]:null;
		
		if(values.containsKey(key)) return (String) values.get(key);
		
		if(defaultTag==null) return "";
		if(!defaultTag.contains(":")) return defaultTag;
		
		return handleTag2(defaultTag,values);
	}
	
	
	
	private String extractStrValue(String s) throws Exception
	{
		if(s.startsWith("\"") && s.endsWith("\""))
			return s.substring(1,s.length()-1).replace("\\\"","\"").replace("\\\\","\\");
		if(s.startsWith("'") && s.endsWith("'"))
			return s.substring(1,s.length()-1).replace("\\'","'").replace("\\\\","\\");
		return null;
	}
}

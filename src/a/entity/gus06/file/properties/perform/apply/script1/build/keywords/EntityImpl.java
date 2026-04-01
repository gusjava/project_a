package a.entity.gus06.file.properties.perform.apply.script1.build.keywords;

import java.io.File;
import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150925";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		return buildKeywords((File) o[0], (Map) o[1], (Map) o[2], (Map) o[3]);
	}
	
	
	
	private Map buildKeywords(File file, Map prop, Map rules, Map other)
	{
		Map keywords = new HashMap();
		
		String fPath = file.getAbsolutePath();
		String fSize = ""+file.length();
		String fName = file.getName();
		
		String[] n = fName.split("\\.");
		String fExt = n.length>1?n[n.length-1]:"";
		String fId = fExt.equals("")? fName : fName.substring(0,fName.length()-fExt.length()-1);
		
		
		keywords.put("$filepath",fPath);
		keywords.put("$filesize",fSize);
		keywords.put("$filename",fName);
		keywords.put("$fileid",fId);
		keywords.put("$fileext",fExt);
		
		keywords.put("$fieldsequence",toSequence(prop.keySet()));
		keywords.put("$valuesequence",toSequence(prop.values()));
		keywords.put("$fieldnumber",""+prop.size());
		
		keywords.put("$o_fieldsequence",toSequence(other.keySet()));
		keywords.put("$o_valuesequence",toSequence(other.values()));
		keywords.put("$o_fieldnumber",""+other.size());
		
		return keywords;
	}
	
	
	
	
	private String toSequence(Collection c)
	{
		if(c.isEmpty()) return "";
		
		ArrayList l = new ArrayList(c);
		Collections.sort(l);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<l.size();i++)
		b.append(l.get(i)+";");
		b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}

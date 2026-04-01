package a.entity.gus06.string.extract.extractor1.handlematcher;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160424";}


	private Service handleMatch;

	public EntityImpl() throws Exception
	{
		handleMatch = Outside.service(this,"gus06.string.extract.extractor1.handlematch");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Matcher m = (Matcher) o[0];
		String index1 = (String) o[1];
		String index2 = (String) o[2];
	
		if(index1.equals("a")) return extractAll(m,index2);
		if(index1.equals("l")) return extractLast(m,index2);
		return extractAt(m,index1,index2);
	}
	
	
	
	private List extractAll(Matcher m, String index2) throws Exception
	{
		List list = new ArrayList();
		while(m.find()) list.add(match(m,index2));
		return list;
	}
	
	private Object extractLast(Matcher m, String index2) throws Exception
	{
		Object r = null;
		while(m.find()) r = match(m,index2);
		return r;
	}
	
	private Object extractAt(Matcher m, String index1, String index2) throws Exception
	{
		int v = Integer.parseInt(index1);
		int k = 0;
		Object r = null;
		while(m.find() && k<v) {r = match(m,index2);k++;}
		return r;
	}
	
	
	private Object match(Matcher m, String index2) throws Exception
	{return handleMatch.t(new Object[]{m,index2});}
}

package a.entity.gus06.string.transform.split.sentences.tolines;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250507";}

	private Service split;

	public EntityImpl() throws Exception
	{
		split = Outside.service(this,"gus06.string.split.sentence.list");
	}
	
	public Object t(Object obj) throws Exception
	{
		List list = (List) split.t(obj);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<list.size();i++)
		b.append(list.get(i)+"\n");
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}

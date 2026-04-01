package a.entity.gus06.filter.string.build.element.oneofthem;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150526";}


	private Service parseSequence;
	private Service toString;
	
	public EntityImpl() throws Exception
	{
		parseSequence = Outside.service(this,"gus06.data.transform.string.sequence.parser.semicolon");
		toString = Outside.service(this,"gus06.tostring.tostring1");
	}

	
	private List parse(String s) throws Exception
	{return (List) parseSequence.t(s);}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		List l = parse(s);
		return new Filter(l);
	}
	
	
	private class Filter implements F
	{
		private List elements;
		public Filter(List elements)
		{this.elements = elements;}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			List l = parse((String) toString.t(obj));
			for(int i=0;i<elements.size();i++)
				if(l.contains(elements.get(i))) return true;
			return false;
		}
	}
}

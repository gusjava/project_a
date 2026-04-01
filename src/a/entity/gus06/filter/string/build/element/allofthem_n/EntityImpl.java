package a.entity.gus06.filter.string.build.element.allofthem_n;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150526";}


	private Service normalize;
	private Service parseSequence;
	private Service toString;
	
	public EntityImpl() throws Exception
	{
		normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");
		parseSequence = Outside.service(this,"gus06.data.transform.string.sequence.parser.semicolon");
		toString = Outside.service(this,"gus06.tostring.tostring1");
	}


	private String normalize(String s) throws Exception
	{return (String) normalize.t(s);}
	
	private List parse(String s) throws Exception
	{return (List) parseSequence.t(s);}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		List l = parse(normalize(s));
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
			List l = parse(normalize((String) toString.t(obj)));
			for(int i=0;i<elements.size();i++)
				if(!l.contains(elements.get(i))) return false;
			return true;
		}
	}
}

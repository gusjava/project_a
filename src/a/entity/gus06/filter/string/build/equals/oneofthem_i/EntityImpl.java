package a.entity.gus06.filter.string.build.equals.oneofthem_i;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150526";}


	private Service parseSequence;
	private Service toString;
	
	public EntityImpl() throws Exception
	{
		parseSequence = Outside.service(this,"gus06.data.transform.string.sequence.parser.semicolon");
	}

	
	private List parse(String s) throws Exception
	{return (List) parseSequence.t(s);}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		List l = parse(s.toLowerCase());
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
			String str = ((String) toString.t(obj)).toLowerCase();
			return elements.contains(str);
		}
	}
}

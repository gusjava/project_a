package a.entity.gus06.filter.string.transform.sequence.allofthem;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150529";}

	
	private Service parseSequence;
	
	public EntityImpl() throws Exception
	{
		parseSequence = Outside.service(this,"gus06.data.transform.string.sequence.parser.semicolon");
	}

	
	private List parse(String s) throws Exception
	{return (List) parseSequence.t(s);}
	
	
	
	public Object t(Object obj) throws Exception
	{return new F_allOfThem((F)obj);}
	
	
	
	private class F_allOfThem implements F
	{
		private F filter;
		public F_allOfThem(F filter)
		{this.filter = filter;}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			List seq = parse((String)obj);
			for(int i=0;i<seq.size();i++)
				if(!filter.f(seq.get(i))) return false;
			return true;
		}
	}
}

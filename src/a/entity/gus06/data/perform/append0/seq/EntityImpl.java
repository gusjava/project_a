package a.entity.gus06.data.perform.append0.seq;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190717";}

	
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String input = o[0];
		String glue = o[1];
		String element = o[2];
		
		if(empty(input)) return element;
		return element+glue+input;
	}
	
	
	private boolean empty(Object s)
	{return s==null || s.equals("");}
}

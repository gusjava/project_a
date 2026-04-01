package a.entity.gus06.sys.parser3.cut.symbol.b2;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151029";}


	private Service cut;
	
	public EntityImpl() throws Exception
	{cut = Outside.service(this,"gus06.sys.parser3.cut.symbol.a2");}

	
	public Object t(Object obj) throws Exception
	{
		List kk = (List) cut.t(obj);
		if(kk==null) return null;
		
		if(kk.size()!=2) throw new Exception("Parsing failed");
		return kk;
	}
}

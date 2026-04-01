package a.entity.gus06.swing.textcomp.cust.action.f2.gusscript.autocomplete.op.perform.chooser.names;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20180203";}
	
	
	private Service getOpMap;
	
	private List names;
	

	public EntityImpl() throws Exception
	{
		getOpMap = Outside.service(this,"gus06.sys.expression1.apply.opmap");
		Map map = (Map) getOpMap.g();
		
		names = new ArrayList(map.keySet());
		Collections.sort(names);
	}
	
	public Object g() throws Exception
	{return names;}
}

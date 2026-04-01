package a.entity.gus06.sys.clustering1.engine.kmedoids;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180504";}


	/*
	* NOT IMPLEMENTED YET ....
	*/
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list1 = (List) o[0];
		int target = Integer.parseInt(""+o[1]);
		
		return null;
	}
}

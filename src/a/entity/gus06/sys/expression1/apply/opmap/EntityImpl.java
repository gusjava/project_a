package a.entity.gus06.sys.expression1.apply.opmap;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20151109";}

	private Map map;

	public synchronized Object g() throws Exception
	{
		if(map==null) initMap();
		return new HashMap(map);
	}
	
	private void initMap() throws Exception
	{
		map = new HashMap();
		map.putAll((Map) Outside.service(this,"gus06.sys.expression1.apply.opmap.a_d").g());
		map.putAll((Map) Outside.service(this,"gus06.sys.expression1.apply.opmap.e_e").g());
		map.putAll((Map) Outside.service(this,"gus06.sys.expression1.apply.opmap.f_i").g());
		map.putAll((Map) Outside.service(this,"gus06.sys.expression1.apply.opmap.j_m").g());
		map.putAll((Map) Outside.service(this,"gus06.sys.expression1.apply.opmap.n_r").g());
		map.putAll((Map) Outside.service(this,"gus06.sys.expression1.apply.opmap.s_z").g());
	}
}
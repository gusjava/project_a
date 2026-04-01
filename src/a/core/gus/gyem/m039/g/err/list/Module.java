package a.core.gus.gyem.m039.g.err.list;

import java.util.List;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;

public class Module extends GyemSystem implements G {
	
	private List list;

	public Object g() throws Exception {
		if(list==null) list = (List) moduleR(M055_R_SLIST).r("errors");
		return list;
	}
}

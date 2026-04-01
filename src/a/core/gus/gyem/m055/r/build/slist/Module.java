package a.core.gus.gyem.m055.r.build.slist;

import a.core.gus.gyem.GyemSystem;
import a.framework.R;

public class Module extends GyemSystem implements R {
	
	public Object r(String key) throws Exception
	{
		return new SList(key);
	}
}

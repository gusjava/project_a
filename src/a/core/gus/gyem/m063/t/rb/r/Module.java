package a.core.gus.gyem.m063.t.rb.r;

import a.core.gus.gyem.GyemSystem;
import a.framework.R;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		Object[] data = (Object[]) obj;
		Object[] call = (Object[]) data[0];
		String rule = (String) data[1];
		String[] n = rule.split(" ",2);
		String entityName = n[0];
		String key = n[1];

		R r = (R) moduleT(M045_T_RESOURCE_PROVIDE).t(new Object[]{call, entityName});
		if(r==null) throw new Exception("Enable to call r on null");
		return r.r(key);
	}
}

package a.entity.gus06.sys.jsparser1.prepare;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221012";}


	private Service engine1;
	private Service engine2;
	private Service engine3;
	private Service engine4;
	
	public EntityImpl() throws Exception
	{
		engine1 = Outside.service(this,"gus06.sys.jsparser1.prepare.step1");
		engine2 = Outside.service(this,"gus06.sys.jsparser1.prepare.step2");
		engine3 = Outside.service(this,"gus06.sys.jsparser1.prepare.step3");
		engine4 = Outside.service(this,"gus06.sys.jsparser1.prepare.step4");
	}
	
	public Object t(Object obj) throws Exception
	{
		List list1 = (List) engine1.t(obj);
		List list2 = (List) engine2.t(list1);
		List list3 = (List) engine3.t(list2);
		return engine4.t(list3);
	}
}
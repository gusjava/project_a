package a.entity.gus06.file.runtask.properties.transform.keyvalue;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250814";}


	private Service op;
	private Service chooseTrans;

	public EntityImpl() throws Exception
	{
		op = Outside.service(this,"gus06.file.properties.perform.each.keyvalue.transform");
		chooseTrans = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_h.tool.perform.chooser");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		T t = (T) chooseTrans.g();
		if(t==null) return;
		
		if(progress!=null) ((V)progress).v("size","1");
		op.p(new Object[]{file,t});
		if(progress!=null) ((E)progress).e();
	}
}
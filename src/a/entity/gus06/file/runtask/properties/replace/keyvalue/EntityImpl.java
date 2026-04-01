package a.entity.gus06.file.runtask.properties.replace.keyvalue;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250813";}


	private Service op;
	private Service getInput;

	public EntityImpl() throws Exception
	{
		op = Outside.service(this,"gus06.file.properties.perform.each.keyvalue.replace");
		getInput = Outside.service(this,"gus06.input.text.dialog");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		String search = (String) getInput.t("Enter search sequence:");
		if(search==null || search.equals("")) return;
		
		String replace = (String) getInput.t("Enter replacement sequence:");
		if(replace==null) return;
		
		if(progress!=null) ((V)progress).v("size","1");
		op.p(new Object[]{file,search,replace});
		if(progress!=null) ((E)progress).e();
	}
}
package a.entity.gus06.dir.runtask.corpus.properties.field.add;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151019";}
	
	public static final String MESSAGE = "Add field: ";


	private Service listing;
	private Service op;
	private Service input;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.properties");
		op = Outside.service(this,"gus06.file.properties.perform.field.put");
		input = Outside.service(this,"gus06.input.text.dialog");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		String field = input("enter field's name");
		if(field==null || field.equals("")) return;
		
		String value = input("enter field's value");
		if(value==null || value.equals("")) return;
		
		
		File[] ff = (File[]) listing.t(dir);
		
		int size = ff.length;
		if(progress!=null) ((V)progress).v("size",""+size);
		
		for(File f:ff)
		{
			op.p(new Object[]{f,field,value});
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
	
	
	private String input(String info) throws Exception
	{
		String message = MESSAGE+info;
		return (String) input.t(message);
	}
}

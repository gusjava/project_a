package a.entity.gus06.dir.runtask.corpus.properties.field.change;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150926";}
	
	public static final String MESSAGE = "Change field: ";


	private Service listing;
	private Service readKeySet;
	private Service op;
	private Service chooseField;
	private Service chooseName;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.properties");
		readKeySet = Outside.service(this,"gus06.file.read.properties.keyset");
		op = Outside.service(this,"gus06.file.properties.perform.field.change");
		chooseField = Outside.service(this,"gus06.input.choose.dialog.field.fromset");
		chooseName = Outside.service(this,"gus06.input.text.dialog");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File[] ff = (File[]) listing.t(dir);
		Set fields = new HashSet();
		
		int size = ff.length*2;
		if(progress!=null) ((V)progress).v("size",""+size);
		
		
		for(File f:ff)
		{
			fields.addAll((Set) readKeySet.t(f));
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		
		String field = chooseField(fields);
		if(field==null) {interrupt.add("*");return;}
		
		String value = chooseValue(field);
		if(value==null) {interrupt.add("*");return;}
		
		for(File f:ff)
		{
			op.p(new Object[]{f,field,value});
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
	
	
	
	private String chooseField(Set fields) throws Exception
	{
		return (String) chooseField.t(fields);
	}
	
	
	private String chooseValue(String field) throws Exception
	{
		String message = MESSAGE+field;
		return (String) chooseName.t(new String[]{message,field});
	}
}

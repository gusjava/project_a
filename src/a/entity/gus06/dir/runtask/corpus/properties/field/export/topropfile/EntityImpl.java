package a.entity.gus06.dir.runtask.corpus.properties.field.export.topropfile;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.util.Properties;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151018";}


	private Service listing;
	private Service readKeySet;
	private Service op;
	private Service chooseField;
	private Service writeProp;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.properties");
		readKeySet = Outside.service(this,"gus06.file.read.properties.keyset");
		op = Outside.service(this,"gus06.file.properties.perform.field.export.toprop");
		chooseField = Outside.service(this,"gus06.input.choose.dialog.field.fromset");
		writeProp = Outside.service(this,"gus06.file.write.properties");
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
		
		String field = (String) chooseField.t(fields);
		if(field==null) {interrupt.clear();return;}
		
		Properties prop = new Properties();
				
		for(File f:ff)
		{
			op.p(new Object[]{f,field,prop});
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		
		File resultFile = new File(dir.getAbsolutePath()+"_field.properties");
		writeProp.p(new Object[]{resultFile,prop});
	}
}

package a.entity.gus06.dir.runtask.corpus.properties.extend;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150619";}


	private Service listing;
	private Service extendOp;
	private Service readKeySet;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.properties");
		extendOp = Outside.service(this,"gus06.file.properties.perform.extend");
		readKeySet = Outside.service(this,"gus06.file.read.properties.keyset");
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
		
		for(File f:ff)
		{
			extendOp.p(new Object[]{f,fields});
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
}

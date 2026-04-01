package a.entity.gus06.dir.runtask.rename.extension.fix;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150629";}


	private Service listing;
	private Service rename;
	private Service getInput;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		rename = Outside.service(this,"gus06.file.perform.rename.changeext");
		getInput = Outside.service(this,"gus06.input.text.dialog");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		List l = (List) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+l.size());
		
		String ext = (String) getInput.t("Enter new extension for all files:");
		if(ext==null || ext.equals("")) return;
		
		for(int i=0;i<l.size();i++)
		{
			File f = (File) l.get(i);
			rename.p(new Object[]{f,ext});
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
}

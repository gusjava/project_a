package a.entity.gus06.dir.runtask.filestoclipboard.byname;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20260220";}

	private Service listing;
	private Service toClipboard;
	private Service getInput;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		toClipboard = Outside.service(this,"gus06.clipboard.access.listfiles");
		getInput = Outside.service(this,"gus06.input.text.dialog");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		String fileName = (String) getInput.t("Enter target file name for clipboard:");
		if(fileName==null || fileName.equals("")) return;
		
		List l = (List) listing.t(dir);
		
		List list = new ArrayList();
		for(int i=0;i<l.size();i++)
		{
			File f = (File) l.get(i);
			if(f.getName().equals(fileName)) list.add(f);
		}
		if(list.isEmpty()) return;
		
		if(progress!=null) ((V)progress).v("size","1");
		toClipboard.p(list);
		if(progress!=null) ((E)progress).e();
	}
}

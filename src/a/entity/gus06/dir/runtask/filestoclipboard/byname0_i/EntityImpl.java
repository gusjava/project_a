package a.entity.gus06.dir.runtask.filestoclipboard.byname0_i;

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
	private Service getName0;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		toClipboard = Outside.service(this,"gus.y.clipboard1.files");
		getInput = Outside.service(this,"gus06.input.text.dialog");
		getName0 = Outside.service(this,"gus.x.file.getname0");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		String targetName0 = (String) getInput.t("Enter target file name0 for clipboard:");
		if(targetName0==null || targetName0.equals("")) return;
		
		targetName0 = targetName0.toLowerCase();
		List l = (List) listing.t(dir);
		
		List list = new ArrayList();
		for(int i=0;i<l.size();i++)
		{
			File f = (File) l.get(i);
			String name0 = (String) getName0.t(f);
			if(name0.toLowerCase().equals(targetName0)) list.add(f);
		}
		if(list.isEmpty()) return;
		
		if(progress!=null) ((V)progress).v("size","1");
		toClipboard.p(list);
		if(progress!=null) ((E)progress).e();
	}
}

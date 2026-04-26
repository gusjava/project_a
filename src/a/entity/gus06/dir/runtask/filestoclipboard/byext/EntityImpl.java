package a.entity.gus06.dir.runtask.filestoclipboard.byext;

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
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles.forext");
		toClipboard = Outside.service(this,"gus.y.clipboard1.files");
		getInput = Outside.service(this,"gus06.input.text.dialog");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		String input = (String) getInput.t("Enter target file extension for clipboard:");
		if(input==null || input.equals("")) return;
		String[] exts = input.toLowerCase().split(";");
		
		List list = new ArrayList();
		for(String ext : exts)
		list.addAll((List) listing.t(new Object[]{dir, ext}));
		if(list.isEmpty()) return;
		
		if(progress!=null) ((V)progress).v("size","1");
		toClipboard.p(list);
		if(progress!=null) ((E)progress).e();
	}
}

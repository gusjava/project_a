package a.entity.gus06.dir.runtask.rename.extension.change;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150629";}


	private Service listing;
	private Service rename;
	private Service getInput;
	private Service getChooser;
	private Service dirToExt;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles.forext");
		rename = Outside.service(this,"gus06.file.perform.rename.changeext");
		dirToExt = Outside.service(this,"gus06.dir.listing.dirtofiles.set.extension");
		getInput = Outside.service(this,"gus06.input.text.dialog");
		getChooser = Outside.service(this,"gus06.input.choose.dialog");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		String oldExt = chooseTargetExt(dir);
		if(oldExt==null || oldExt.equals("")) return;
		
		String newExt = chooseNewExt();
		if(newExt==null) return;
		if(oldExt.equals(newExt)) return;
		
		
		List l = (List) listing.t(new Object[]{dir,oldExt});
		if(progress!=null) ((V)progress).v("size",""+l.size());
		
		for(int i=0;i<l.size();i++)
		{
			File f = (File) l.get(i);
			rename.p(new Object[]{f,newExt});
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
	
	
	
	private String chooseTargetExt(File dir) throws Exception
	{
		Set set = (Set) dirToExt.t(dir);
		
		if(set.isEmpty()) return null;
		if(set.size()==1) return (String) set.iterator().next();
		
		ArrayList list = new ArrayList(set);
		Collections.sort(list);
		
		String[] options = new String[list.size()];
		list.toArray(options);
		
		String message = "Choose target extension";
		String title = "Extension chooser";
		
		return (String) getChooser.t(new Object[]{message,title,options});
	}
	
	
	private String chooseNewExt() throws Exception
	{
		String message = "Enter new extension:";
		return (String) getInput.t(message);
	}
}

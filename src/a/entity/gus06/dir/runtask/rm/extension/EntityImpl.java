package a.entity.gus06.dir.runtask.rm.extension;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150702";}


	private Service listing;
	private Service remove;
	private Service inputDialog;
	private Service showMessage;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		remove = Outside.service(this,"gus.x.file.op.delete");
		inputDialog = Outside.service(this,"gus06.input.text.dialog");
		showMessage = Outside.service(this,"gus06.swing.optionpane.showmessage.info");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		
		String ext = (String) inputDialog.t("Enter target extension:");
		if(ext==null || ext.equals("")) return;
		
		List l = (List) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+l.size());
		
		int deletedNb = 0;
		long deletedSize = 0;
		
		for(int i=0;i<l.size();i++)
		{
			File f = (File) l.get(i);
			if(isType(f,ext))
			{
				deletedSize += f.length();
				deletedNb++;
				remove.p(f);
			}
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		
		showMessage.p("Deleted file nb: "+deletedNb+"\nTotal size: "+deletedSize);
	}
	
	
	private boolean isType(File f, String ext)
	{return f.getName().toLowerCase().endsWith("."+ext);}
}

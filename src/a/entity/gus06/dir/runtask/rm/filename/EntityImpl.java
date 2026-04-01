package a.entity.gus06.dir.runtask.rm.filename;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250602";}


	private Service listing;
	private Service remove;
	private Service inputDialog;
	private Service showMessage;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		remove = Outside.service(this,"gus06.file.op.delete");
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
		
		
		String fileName = (String) inputDialog.t("Enter target file name:");
		if(fileName==null || fileName.equals("")) return;
		
		List l = (List) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+l.size());
		
		int deletedNb = 0;
		long deletedSize = 0;
		
		for(int i=0;i<l.size();i++)
		{
			File f = (File) l.get(i);
			if(isFile(f,fileName))
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
	
	
	private boolean isFile(File f, String fileName)
	{return f.getName().equals(fileName);}
}
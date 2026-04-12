package a.entity.gus06.dir.runtask.deletefiles.byname;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251220";}

	private Service listing;
	private Service delete;
	private Service getInput;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		delete = Outside.service(this,"gus06.file.op.delete");
		getInput = Outside.service(this,"gus06.input.text.dialog");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		String fileName = (String) getInput.t("Enter target file name for deletion:");
		if(fileName==null || fileName.equals("")) return;
		
		List l = (List) listing.t(dir);
		
		List toDelete = new ArrayList();
		StringBuilder sb = new StringBuilder("Are you sure to delete files?:\n\n");
		
		for(int i=0;i<l.size();i++)
		{
			File f = (File) l.get(i);
			if(f.getName().equals(fileName))
			{
				toDelete.add(f);
				sb.append(f+"\n"); 
			}
		}
		if(toDelete.isEmpty()) return;
		
		int r1 = JOptionPane.showConfirmDialog(
			null,
			sb.toString(),
			"Confirm deletion",
			JOptionPane.YES_NO_OPTION
		);
		if(r1!=JOptionPane.YES_OPTION) return;
		
		if(progress!=null) ((V)progress).v("size",""+toDelete.size());
		for(int i=0;i<toDelete.size();i++)
		{
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
			
			File f = (File) toDelete.get(i);
			delete.p(f);
		}
	}
}

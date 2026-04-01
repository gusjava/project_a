package a.entity.gus06.appli.convertisseurgus05.execute.selected.convert;

import a.framework.*;
import java.io.File;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20150525";}
	

	private Service getSelected;
	private Service nameToDirGus05;
	private Service nameToDirGus06;
	private Service convertor;
	private Service input;
	private Service hasFiles;


	public EntityImpl() throws Exception
	{
		getSelected = Outside.service(this,"gus06.appli.convertisseurgus05.holder.selected");
		nameToDirGus05 = Outside.service(this,"gus06.appli.convertisseurgus05.data.gus05.nametodir");
		nameToDirGus06 = Outside.service(this,"gus06.appli.convertisseurgus05.data.gus06.nametodir");
		convertor = Outside.service(this,"gus06.appli.convertisseurgus05.convertor");
		input = Outside.service(this,"gus06.input.text.dialog");
		hasFiles = Outside.service(this,"gus06.java.packagedir.hasfiles");
	}
	
	
	public void e() throws Exception
	{
		String name = (String) getSelected.g();
		if(name==null) return;
		
		File packageGus05 = (File) nameToDirGus05.t(name);
		if(packageGus05==null || !packageGus05.isDirectory()) return;
		
		String newName = (String) input.t(new String[]{"Enter new name:",name});
		if(newName==null || newName.equals("")) return;
		
		File packageGus06 = (File) nameToDirGus06.t(newName);
		if(hasFiles.f(packageGus06))
		{
			JOptionPane.showMessageDialog(null,"Gus06 Entity already exists: "+newName);
			return;
		}
		
		convertor.p(new Object[]{packageGus05,packageGus06,newName});
		JOptionPane.showMessageDialog(null,"Gus05 entity converted inside: "+packageGus06);
	}
}

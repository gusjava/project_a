package a.entity.gus06.appli.convertisseurgus05.execute.selected.checkgus06;

import a.framework.*;
import java.io.File;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20150525";}


	private Service getSelected;
	private Service check;


	public EntityImpl() throws Exception
	{
		getSelected = Outside.service(this,"gus06.appli.convertisseurgus05.holder.selected");
		check = Outside.service(this,"gus06.appli.convertisseurgus05.data.gus06.check");
	}
	
	
	public void e() throws Exception
	{
		String name = (String) getSelected.g();
		if(name==null) return;
		boolean found = check.f(name);
		
		if(found) JOptionPane.showMessageDialog(null,"Gus06 Entity found: "+name);
		else JOptionPane.showMessageDialog(null,"Gus06 Entity NOT found: "+name);
	}
}

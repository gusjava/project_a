package a.entity.gus06.swing.tree.perform.file.display.infos1;

import a.framework.*;
import javax.swing.JTree;
import java.io.File;
import javax.swing.tree.TreeModel;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151004";}


	private Service display;


	public EntityImpl() throws Exception
	{
		display = Outside.service(this,"gus06.dirfile.perform.display.infos1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		File file = (File) tree.getLastSelectedPathComponent();
		
		if(file==null) return;
		if(!file.exists()) return;
		
		display.p(file);
	}
}

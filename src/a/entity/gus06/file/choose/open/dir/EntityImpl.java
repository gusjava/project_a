package a.entity.gus06.file.choose.open.dir;

import a.framework.*;
import javax.swing.JFileChooser;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150723";}


	private Service fcHolder;
	private JFileChooser fc;
	
	
	public EntityImpl() throws Exception
	{
		fcHolder = Outside.service(this,"*gus06.swing.filechooser.holder");
		fc = (JFileChooser) fcHolder.i();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	}
	
	
	public Object g() throws Exception
	{
		int val = fc.showOpenDialog(null);
		if(val==JFileChooser.APPROVE_OPTION)
			return fc.getSelectedFile();
		return null;
	}
}

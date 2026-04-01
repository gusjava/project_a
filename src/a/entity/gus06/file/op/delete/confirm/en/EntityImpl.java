package a.entity.gus06.file.op.delete.confirm.en;

import a.framework.*;
import java.io.File;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150615";}


	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		if(!file.exists()) return;
		
		int r1 = JOptionPane.showConfirmDialog(null,"Are you sure to delete this file?:\n"+file);
		if(r1!=JOptionPane.YES_OPTION) return;
		
		boolean r = file.delete();
		if(!r) throw new Exception("Failed to delete file: "+file);
	}
}

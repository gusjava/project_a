package a.entity.gus06.file.op.delete.confirm.fr;

import a.framework.*;
import java.io.File;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191110";}


	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		if(!file.exists()) return;
		
		int r1 = JOptionPane.showConfirmDialog(null,"Etes-vous certain de vouloir supprimer ce fichier?:\n"+file);
		if(r1!=JOptionPane.YES_OPTION) return;
		
		boolean r = file.delete();
		if(!r) throw new Exception("Failed to delete file: "+file);
	}
}

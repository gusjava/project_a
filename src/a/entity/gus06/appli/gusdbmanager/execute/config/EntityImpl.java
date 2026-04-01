package a.entity.gus06.appli.gusdbmanager.execute.config;

import java.io.File;
import javax.swing.JOptionPane;
import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20150613";}

	private Service dataHolder;
	private Service getFile;
	
	
	public EntityImpl() throws Exception
	{
		dataHolder = Outside.service(this,"gus06.appli.gusdbmanager.data.holder");
		getFile = Outside.service(this,"gus06.appli.gusdbmanager.execute.config.choosefile");
	}
	

	public void e() throws Exception
	{
		File f = (File) getFile.g();
		if(f==null || !f.exists()) return;
		
		boolean loaded = dataHolder.f(f);
		if(loaded) return;

		JOptionPane.showMessageDialog(null,"Le chargement du fichier a �chou�:\n"+f,
				"Configuration des serveurs en �chec",
				JOptionPane.WARNING_MESSAGE);
	}
}

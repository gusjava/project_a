package a.entity.gus06.appli.gusclient1.project.release;

import a.framework.*;
import java.io.File;
import javax.swing.JOptionPane;
import java.util.Properties;

public class EntityImpl extends S1 implements Entity, E {

	public String creationDate() {return "20141014";}

	public static final String FILENAME_INFO = "info.properties";

	private Service manager;
	private Service idToFile;
	private Service chooseVersion;
	private Service updateBuild;
	private Service buildJar;
	private Service writeProp;
	private Service getNow;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
		idToFile = Outside.service(this,"gus06.appli.gusclient1.project.idtodir.release");
		chooseVersion = Outside.service(this,"gus06.appli.gusclient1.project.release.chooseversion");
		updateBuild = Outside.service(this,"gus06.appli.gusclient1.project.config.updatebuild");
		buildJar = Outside.service(this,"gus06.appli.gusclient1.project.release.buildjar");
		writeProp = Outside.service(this,"gus06.file.write.properties");
		getNow = Outside.service(this,"gus06.time.now");
	}
	
	
	public void e() throws Exception
	{
		String id = (String) manager.g();
		if(id==null) return;
		File dir = (File) idToFile.t(id);
		
		String version = (String) chooseVersion.t(dir);
		if(version==null) return;
		
		File dir1 = new File(dir,version);
		dir1.mkdirs();
		
		File fileInfo = new File(dir1,FILENAME_INFO);
		handleFileInfo(fileInfo);
		
		updateBuild.p(version);
		buildJar.p(dir1);
		updateBuild.p(version+"*");
		
		JOptionPane.showMessageDialog(null,"Release is complete");
		complete();
	}
	
	
	private void complete()
	{send(this,"complete()");}
	
	
	
	
	
	
	
	
	
	private void handleFileInfo(File f) throws Exception
	{
		Properties p = new Properties();
		
		String now = (String) getNow.g();
		
		p.put("description","Put a description here");
		p.put("creationtime",now);
		
		writeProp.p(new Object[]{f,p});
	}
}

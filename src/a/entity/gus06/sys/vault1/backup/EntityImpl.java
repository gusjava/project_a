package a.entity.gus06.sys.vault1.backup;

import a.framework.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200416";}
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");


	private Service copy;

	public EntityImpl() throws Exception
	{
		copy = Outside.service(this,"gus.x.file.op.copy");
	}
	
	public void p(Object obj) throws Exception
	{
		File vaultFile = (File) obj;
		if(!vaultFile.isFile()) return;
		
		File dir = vaultFile.getParentFile();
		File backupDir = new File(dir,"backup");
		backupDir.mkdirs();
		
		String name = vaultFile.getName();
		File backupFile = new File(backupDir,now()+"_"+name);
		
		copy.p(new File[]{vaultFile,backupFile});
	}
	
	private String now() {return sdf.format(new Date());}
}

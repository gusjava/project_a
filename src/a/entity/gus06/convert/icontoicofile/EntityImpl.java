package a.entity.gus06.convert.icontoicofile;

import a.framework.*;
import javax.swing.Icon;
import java.util.HashMap;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150607";}


	private Service randomName;
	private Service writeIco;
	
	private File storeDir;
	private HashMap icoMap;
	
	
	public EntityImpl() throws Exception
	{
		randomName = Outside.service(this,"gus06.data.generate.string.random.number10");
		writeIco = Outside.service(this,"gus06.file.write.ico");
		
		storeDir = (File) Outside.resource(this,"defaultdir");
		icoMap = new HashMap();
	}
	
	
	public Object t(Object obj) throws Exception
	{return getIcoFile((Icon) obj);}
	
	
	
	private File getIcoFile(Icon icon) throws Exception
	{
		if(!icoMap.containsKey(icon))
			icoMap.put(icon,buildIcoFile(icon));
		return (File) icoMap.get(icon);
	}
	
	
	
	private File buildIcoFile(Icon icon) throws Exception
	{
		File ico = new File(storeDir,randomName()+".ico");
		writeIco(ico,icon);
		return ico;
	}
	
	
	private String randomName() throws Exception
	{return (String) randomName.g();}
	
	
	private void writeIco(File icoFile, Icon icon) throws Exception
	{writeIco.p(new Object[]{icoFile,icon});}
}

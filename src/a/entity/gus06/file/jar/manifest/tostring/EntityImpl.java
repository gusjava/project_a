package a.entity.gus06.file.jar.manifest.tostring;

import java.util.Iterator;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import a.framework.*;
import java.util.jar.JarFile;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170223";}

	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		File file = (File) obj;
		if(!file.isFile()) return null;
		
		JarFile jar = new JarFile(file,true,JarFile.OPEN_READ);
		Manifest mf = jar.getManifest();
		String s = manifestToString(mf);
		jar.close();
		return s;
	}
	
	private String manifestToString(Manifest mf) throws Exception
	{
		if(mf==null) return "";
		
		StringBuffer b = new StringBuffer();
		Attributes mainAttr = mf.getMainAttributes();
		b.append("Main attributes:\n");
		extractAttributes(b,mainAttr);
		
		Map attrMap = mf.getEntries();
		Iterator it = attrMap.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			Attributes attr = (Attributes) attrMap.get(key);
			b.append("\nAttributes for key="+key+":\n");
			extractAttributes(b,attr);
		}
		return b.toString();
	}

	
	private void extractAttributes(StringBuffer b, Attributes attr)
	{
		Iterator it = attr.keySet().iterator();
		while(it.hasNext())
		{
			Attributes.Name key = (Attributes.Name) it.next();
			String value = (String) attr.get(key);
			b.append(key+" = "+value+"\n");
		}
	}
}

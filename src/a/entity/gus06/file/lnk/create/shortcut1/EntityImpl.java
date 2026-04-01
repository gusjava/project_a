package a.entity.gus06.file.lnk.create.shortcut1;

import java.io.File;
import a.framework.*;
import net.jimmc.jshortcut.JShellLink;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150607";}


	public static final String EXTENSION = "lnk";
	


	public void p(Object obj) throws Exception
	{
		File[] f = (File[]) obj;
		if(f.length==2) {createShortcut(f[0],f[1],null);return;}
		if(f.length==3) {createShortcut(f[0],f[1],f[2]);return;}
		
		throw new Exception("Wrong data number: "+f.length);
	}
	
	
	private void createShortcut(File target, File lnk, File ico) throws Exception
	{
		if(!target.exists()) throw new Exception("Target file does not exist: "+target);
		File lnkFile = buildLnkFile(target,lnk);
		
		JShellLink jshelllink = new JShellLink(lnkFile);
		jshelllink.save(target,target.getParentFile(),ico);
	}
	
	
	private File buildLnkFile(File target, File lnk)
	{
		if(lnk.isDirectory()) return new File(lnk,name(target)+"."+EXTENSION);
		if(lnk.getName().toLowerCase().endsWith("."+EXTENSION)) return lnk;
		return new File(lnk.getAbsolutePath()+"."+EXTENSION);
	}
	
	
	private String name(File f)
	{
		String name = f.getName();
		if(!name.contains(".")) return name;
		String[] n = name.split("\\.");
		int l = n[n.length-1].length();
		return name.substring(0,name.length()-1-l);
	}
}

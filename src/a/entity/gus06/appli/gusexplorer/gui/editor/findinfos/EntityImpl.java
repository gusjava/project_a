package a.entity.gus06.appli.gusexplorer.gui.editor.findinfos;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class EntityImpl implements Entity, G, T {

	public String creationDate() {return "20190401";}


	private Service getExtension;
	private Service readFile;
	private Service encodePath;

	private File rootDir;

	public EntityImpl() throws Exception
	{
		getExtension = Outside.service(this,"gus06.file.getextension.lowercase");
		readFile = Outside.service(this,"gus06.file.read.string.array.autodetect");
		encodePath = Outside.service(this,"gus06.string.transform.encoding.filepath.encode");
		
		File defaultDir = (File) Outside.resource(this,"defaultdir");
		
		rootDir = new File(defaultDir,"editors");
		rootDir.mkdirs();
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		File configDir = findConfigDir(file);
		String key = file.isDirectory() ? "_dir" : (String) getExtension.t(file);
		
		List list = new ArrayList();
		load(list, configDir, key);
		return list;
	}
	
	
	
	private File findConfigDir(File file) throws Exception
	{
		String path = file.getAbsolutePath();
		String path_ = (String) encodePath.t(path);
		
		File d = new File(rootDir, path_);
		if(d.isDirectory()) return d;
		
		File parent = file.getParentFile();
		if(parent==null) return rootDir;
		return findConfigDir(parent);
	}
	
	
	
	
	private void load(List list, File configDir, String key) throws Exception
	{
		File configFile = new File(configDir,key+".txt");
		if(!configFile.isFile()) return;
		
		String[] lines = (String[]) readFile.t(configFile);
		for(String line : lines)
		{
			String[] kk = line.split("\t+");
			if(kk[0].equals("load")) load(list, configDir, kk[1]);
			else list.add(kk);
		}
	}
	
	
	public Object g() throws Exception
	{return rootDir;}
}

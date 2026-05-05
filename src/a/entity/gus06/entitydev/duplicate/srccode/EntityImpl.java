package a.entity.gus06.entitydev.duplicate.srccode;

import java.io.File;
import java.io.FileFilter;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140716";}


	public static final String PACKAGE_START = "a.entity.";
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private String today() {return sdf.format(new Date());}
	
	private FileFilter fileFilter = new FileFilter() {
		public boolean accept(File f) {return f.isFile();}
	};

	
	private Service hasFile;
	private Service readFile;
	private Service copyFile;
	
	public EntityImpl() throws Exception
	{
		hasFile = Outside.service(this,"gus06.dir.children.hasfile");
		readFile = Outside.service(this,"gus06.file.read.string");
		copyFile = Outside.service(this,"gus.x.file.op.copy");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String name1 = get1(map,REFACTOR.KEY_ENTITYNAME1);
		String name2 = get1(map,REFACTOR.KEY_ENTITYNAME2);
		File rootDir = new File(get1(map,REFACTOR.KEY_ROOTDIR));
		
		duplicateEntity(rootDir,name1,name2);
	}
	
	
	
	
	private void duplicateEntity(File rootDir, String name1, String name2) throws Exception
	{
		try
		{
			File dir1 = findPackageDir(rootDir,name1);
			File dir2 = findPackageDir(rootDir,name2);

			if(!hasFile.f(dir1)) throw new Exception("Directory should contain files: "+dir1);
			if(hasFile.f(dir2)) throw new Exception("Directory should not contain files: "+dir2);

			dir2.mkdirs();

			File[] ff = dir1.listFiles(fileFilter);
			for(File f:ff)
			{
				if(isJavaFile(f)) duplicateJavaFile(f,dir2,name1,name2);
				else copyFile(f,dir2);
			}
		}
		catch(Exception e)
		{
			String message = "Failed to duplicate entity "+name1+" into "+name2;
			throw new Exception(message,e);
		}
	}
	
	
	
	
	
	
	
	private boolean isJavaFile(File f)
	{return f.getName().toLowerCase().endsWith(".java");}
	
	
	
	
	private void duplicateJavaFile(File file, File dir, String name1, String name2) throws Exception
	{
		String src = (String) readFile.t(file);
		
		String p1 = "package "+PACKAGE_START+name1+";";
		String p2 = "package "+PACKAGE_START+name2+";";
		src = src.replaceFirst(Pattern.quote(p1),p2);
		
		String r1 = "return \"[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]\";";
		String r2 = "return \""+today()+"\";";
		src = src.replaceFirst(r1,r2);
		
		File file2 = new File(dir,file.getName());
		PrintStream p = new PrintStream(file2);
		p.print(src);
		p.close();
	}
	
	
	
	
	private void copyFile(File file, File dir) throws Exception
	{
		File file2 = new File(dir,file.getName());
		copyFile.p(new File[]{file,file2});
	}
	
	
	
	
	private File findPackageDir(File rootDir, String name) throws Exception
	{
		String path = (PACKAGE_START+name).replace(".",File.separator);
		return new File(rootDir,path);
	}
	
	
	
	
	private String get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key))
			throw new Exception("Key not found inside map: "+key);
		return (String) map.get(key);
	}
}
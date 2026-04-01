package a.entity.gus06.app.path.build.fromprop;

import java.io.File;
import java.io.FileReader;

public class Tool_File {

	
	public static String text(File file) throws Exception
    {
    	FileReader fr = new FileReader(file);
        char[] a = new char[(int)file.length()];
        fr.read(a,0,(int)file.length());
        fr.close();
        return new String(a);
    }
	
	
	/*
     * formattage du file path pour correspondre � l'OS
     */
    public static String pathOS(String path)
    {
		if(path.startsWith("<user.home>"))
		path = path.replace("<user.home>", System.getProperty("user.home"));
		
		if(path.startsWith("<java.home>"))
		path = path.replace("<java.home>", System.getProperty("java.home"));
	
    	String s = File.separator;
    	path = path.replace("\\",s).replace("/",s).replace(s+s,s);
    	if(path.startsWith(s)) path = path.substring(1);
    	if(path.endsWith(s)) path = path.substring(0,path.length()-1);

    	return path;
    }
    
    
	public static File newFileOS(String path)
    {
		if(path.startsWith("?:")) return inferFileOS(path);
		return new File(pathOS(path)).getAbsoluteFile();
	}
	
    public static File newFileOS(File dir, String path)
    {return new File(dir,pathOS(path)).getAbsoluteFile();}
	
	
	public static File inferFileOS(String path)
	{
		for(int i=0;i<10;i++)
		{
			char s = (char) ('A'+i);
			String replPath = s+path.substring(1,path.length());
			File f = new File(pathOS(replPath)).getAbsoluteFile();
			if(f.exists()) return f;
		}
		return null;
	}
}

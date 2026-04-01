package a.entity.gus06.sys.filetool.ext.scriptlauncher1.handle.create;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161119";}
	
	public static final String MESSAGE = "Please, enter script name:";


	private Service askInput;
	private Service findFile;

	public EntityImpl() throws Exception
	{
		askInput = Outside.service(this,"gus06.input.text.dialog");
		findFile = Outside.service(this,"gus06.sys.filetool.ext.scriptlauncher1.find.scriptfile");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		File root = (File) o[0];
		String scriptName = (String) o[1];
		String initScript = (String) o[2];
		String initKey = (String) o[3];
		
		String newKey = (String) askInput.t(new String[]{MESSAGE, initKey});
		if(newKey==null) return null;
		
		initScript(root, newKey, scriptName, initScript);
		return newKey;
	}
	
	
	
	private void initScript(File root, String newKey, String scriptName, String initScript) throws Exception
	{
		File file = (File) findFile.t(new Object[]{root,newKey,scriptName});
		if(file.exists()) throw new Exception("File already exists: "+file);
		
		if(initScript==null) initScript = "@code\n\n\n";
		
		file.getParentFile().mkdirs();
		PrintStream p = new PrintStream(file);
		p.print(initScript);
		p.close();
	}
}
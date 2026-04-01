package a.entity.gus06.sys.filemanagement1.perform.root.add;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20191110";}
	
	public static final String MESSAGE = "Please, enter name";


	private Service dirChooser;
	private Service dirToMap;
	private Service getInput;
	private Service showErr;
	private Service writeProp;


	public EntityImpl() throws Exception
	{
		dirChooser = Outside.service(this,"gus06.file.choose.open.dir");
		dirToMap = Outside.service(this,"gus06.sys.filemanagement1.tool.rootmap.dirtomap");
		getInput = Outside.service(this,"gus06.input.text.dialog");
		showErr = Outside.service(this,"gus06.swing.optionpane.showmessage.error");
		writeProp = Outside.service(this,"gus06.file.write.properties");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		File dir = (File) obj;
		
		File r = (File) dirChooser.g();
		if(r==null) return false;
		
		String name0 = r.getName();
		String name = (String) getInput.t(new String[]{MESSAGE,name0});
		if(name==null || name.equals("")) return false;
		
		File file = new File(dir,name+".properties");
		if(file.exists())
		{
			showErr.p("Name already used: "+name);
			return false;
		}
		
		Map prop = (Map) dirToMap.t(r);
		writeProp.p(new Object[]{file,prop});
		return true;
	}
}

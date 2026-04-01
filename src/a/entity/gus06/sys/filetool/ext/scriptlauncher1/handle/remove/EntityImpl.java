package a.entity.gus06.sys.filetool.ext.scriptlauncher1.handle.remove;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20161119";}


	private Service confirm;
	private Service perform;
	private Service findDepMap;

	public EntityImpl() throws Exception
	{
		findDepMap = Outside.service(this,"gus06.sys.filetool.ext.scriptlauncher1.find.dependency.map");
		confirm = Outside.service(this,"gus06.input.confirm.dialog");
		perform = Outside.service(this,"gus06.dir.perform.removefiles0.clear");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File root = (File) o[0];
		String scriptName = (String) o[1];
		String path = (String) o[2];
		
		Map depMap = (Map) findDepMap.t(new Object[]{root, scriptName, path});
		
		if(depMap.isEmpty())
		{
			boolean ok = confirm.f("Please, confirm script delete:\n"+path);
			if(!ok) return false;
		}
		else
		{
			boolean ok = confirm.f("Please, confirm script delete:\n"+path+"\nFound dependencies: "+depMap.size());
			if(!ok) return false;
		}
		File dir = new File(root,path.replace(".",File.separator));
		perform.p(dir);
		
		return true;
	}
}
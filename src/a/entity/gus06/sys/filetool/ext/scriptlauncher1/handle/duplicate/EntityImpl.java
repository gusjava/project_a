package a.entity.gus06.sys.filetool.ext.scriptlauncher1.handle.duplicate;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161119";}


	private Service askInput;
	private Service listing;
	private Service copy;
	
	public EntityImpl() throws Exception
	{
		askInput = Outside.service(this,"gus06.input.text.dialog");
		listing = Outside.service(this,"gus06.dir.listing0.files");
		copy = Outside.service(this,"gus06.dir.perform.copyfile");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File root = (File) o[0];
		String key = (String) o[1];
		
		String newKey = (String) askInput.t(new String[]{"Please, enter script new name:",key});
		if(newKey==null || newKey.equals(key)) return null;
		
		boolean done = duplicateScript(root,key,newKey);
		return done ? newKey : null;
	}
	
	
	
	private boolean duplicateScript(File root, String name1, String name2) throws Exception
	{
		File dir1 = new File(root,name1.replace(".",File.separator));
		File dir2 = new File(root,name2.replace(".",File.separator));
		
		if(!dir1.isDirectory()) return false;
		
		File[] ff1 = (File[]) listing.t(dir1);
		File[] ff2 = (File[]) listing.t(dir2);
		
		if(ff1==null || ff1.length==0) return false;
		if(ff2!=null && ff2.length>0) return false;
		
		dir2.mkdirs();
		for(File f1:ff1) copy.p(new File[]{f1,dir2});
		
		return true;
	}
}
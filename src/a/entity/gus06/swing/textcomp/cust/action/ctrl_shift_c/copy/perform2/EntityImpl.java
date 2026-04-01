package a.entity.gus06.swing.textcomp.cust.action.ctrl_shift_c.copy.perform2;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220603";}


	private Service copyFile;
	private Service copyURL;
	private Service replaceTag;

	public EntityImpl() throws Exception
	{
		copyFile = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_shift_c.copy.perform2.copyfile");
		copyURL = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_shift_c.copy.perform2.copyurl");
		replaceTag = Outside.service(this,"gus06.string.transform.replace.tag.now");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String text = (String) obj;
		String[] lines = text.split("\n");
		for(String line:lines) perform(line);
	}
	
	
	private void perform(String line) throws Exception
	{
		line = line.trim();
		if(line.equals("") || line.equals(".")) return;
		line = (String) replaceTag.t(line);
		
		if(copyFile(line)) return;
		if(copyURL(line)) return;
	}
	
	
	private boolean copyFile(String line) throws Exception
	{return copyFile.f(line);}
	
	private boolean copyURL(String line) throws Exception
	{return copyURL.f(line);}
}
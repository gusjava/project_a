package a.entity.gus06.appli.gusclient1.project.release.gui.change;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20200208";}

	public static final String JARNAME = "app.jar";

	private Service gui;
	
	public EntityImpl() throws Exception
	{gui = Outside.service(this,"*gus06.file.jar.gusapp.entity.comparator.gui1");}
	
	
	public Object i() throws Exception
	{return gui.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		File dir2 = (File) obj;
		if(dir2==null) {reset();return;}
		
		double version2 = Double.parseDouble(dir2.getName());
		double version1 = -1;
		
		File parent = dir2.getParentFile();
		File[] dd = parent.listFiles();
		
		int index = -1;
		for(int i=0;i<dd.length;i++)
		{
			double v = Double.parseDouble(dd[i].getName());
			if(v>version1 && v<version2)
			{
				version1 = v;
				index = i;
			}
		}
		
		File jar1 = index==-1 ? null : new File(dd[index],JARNAME);
		File jar2 = new File(dir2,JARNAME);
		
		gui.p(new File[]{jar1,jar2});
	}
	
	
	private void reset() throws Exception
	{
		gui.p(null);
	}
}

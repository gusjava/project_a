package a.entity.gus06.dir.runtask.javasrc.viewer1;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191230";}


	private Service newViewer;
	private Service show;
	
	public EntityImpl() throws Exception
	{
		newViewer = Outside.service(this,"factory#gus06.sys.javaprojectviewer1.gui");
		show = Outside.service(this,"gus06.swing.frame.show");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		if(progress!=null) ((V)progress).v("size","1");
		
		String title = dir.getAbsolutePath();
		Object comp = comp(dir);
		show.v(title,comp);
		
		if(progress!=null) ((E)progress).e();
	}
	
	
	private Object comp(File file) throws Exception
	{
		Object viewer = newViewer.g();
		((P)viewer).p(file);
		return ((I)viewer).i();
	}
}

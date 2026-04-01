package a.entity.gus06.file.runtask.string.linecomparator.withclipboard;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210717";}
	
	public static final String TITLE = "Line comparator";


	private Service fromClipboard;
	private Service comparatorGui;
	private Service show;


	public EntityImpl() throws Exception
	{
		fromClipboard = Outside.service(this,"gus06.clipboard.access.string.or.file");
		comparatorGui = Outside.service(this,"*gus06.sys.linecomparator1.gui2");
		show = Outside.service(this,"gus06.swing.frame.show");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		
		if(progress!=null) ((V)progress).v("size","1");
		
		Object previous = fromClipboard.g();
		comparatorGui.p(new Object[]{previous,file});
		show.v(TITLE,comparatorGui);
		
		if(progress!=null) ((E)progress).e();
	}
}
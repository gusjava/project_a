package a.entity.gus06.data.viewer.filearray.file2;

import a.framework.*;
import java.io.File;
import javax.swing.JSplitPane;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20221023";}


	private Service viewer1;
	private Service viewer2;
	private Service splitCust;

	private JSplitPane split;
	private File[] data;

	public EntityImpl() throws Exception
	{
		viewer1 = Outside.service(this,"*gus06.data.viewer.file-1");
		viewer2 = Outside.service(this,"*gus06.data.viewer.file-2");
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
        
		split = new JSplitPane();
		split.setLeftComponent((JComponent) viewer1.i());
		split.setRightComponent((JComponent) viewer2.i());
		
		splitCust.p(split);
	}
	
	public Object i() throws Exception
	{return split;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (File[]) obj;
		if(data==null) resetGui();
		else updateGui();
	}
	
	private void resetGui() throws Exception
	{
		viewer1.p(null);
		viewer2.p(null);
	}
	
	private void updateGui() throws Exception
	{
		if(data.length<2) throw new Exception("Invalid file number: "+data.length);
		
		viewer1.p(data[0]);
		viewer2.p(data[1]);
	}
}
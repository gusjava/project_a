package a.entity.gus06.data.viewer.file;

import a.framework.*;
import javax.swing.JComponent;
import java.io.File;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20140731";}


	private Service shiftPanel;
	private Service dirPanel;
	private Service filePanel;

	private File data;
	
	public EntityImpl() throws Exception
	{
		shiftPanel = Outside.service(this,"*gus06.swing.panel.shiftpanel");
		dirPanel = Outside.service(this,"*gus06.dir.explorer.simple");
		filePanel = Outside.service(this,"*gus06.file.editor.main");
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
	public void p(Object obj) throws Exception
	{
		data = (File) obj;
		updateGui();
	}
	
	
	
	private void updateGui() throws Exception
	{
		if(data==null || !data.exists())
		{shiftPanel.p(null);return;}
	
		if(data.isDirectory())
		{
			dirPanel.p(data);
			shiftPanel.p(dirPanel.i());
		}
		else
		{
			filePanel.p(data);
			shiftPanel.p(filePanel.i());
		}
	}
}

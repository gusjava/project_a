package a.entity.gus06.sys.filetool.main.settingsgui;

import a.framework.*;
import javax.swing.JComponent;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20220501";}


	private Service shift;
	private Service mapToComp;
	private Service exceptionViewer;
	
	private Map map;



	public EntityImpl() throws Exception
	{
		shift = Outside.service(this,"*gus06.swing.panel.shiftpanel");
		mapToComp = Outside.service(this,"gus06.sys.filetool.main.settingsgui.maptocomp");
		exceptionViewer = Outside.service(this,"*gus06.data.viewer.exception");
	}
	
	
	public Object i() throws Exception
	{return shift.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		
		try
		{
			shift.p(comp());
		}
		catch(Exception e)
		{
			exceptionViewer.p(e);
			shift.p(exceptionViewer.i());
		}
	}
	
	
	
	private JComponent comp() throws Exception
	{return (JComponent) mapToComp.t(map);}
}
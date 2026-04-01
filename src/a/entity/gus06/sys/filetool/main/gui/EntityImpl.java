package a.entity.gus06.sys.filetool.main.gui;

import a.framework.*;
import javax.swing.JComponent;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20141229";}


	private Service shift;
	private Service mapToName;
	private Service uniqueEntity;
	private Service exceptionViewer;
	
	private Map map;



	public EntityImpl() throws Exception
	{
		shift = Outside.service(this,"*gus06.swing.panel.shiftpanel");
		mapToName = Outside.service(this,"gus06.sys.filetool.main.maptoname");
		uniqueEntity = Outside.service(this,"entityunique");
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
	{
		if(map==null) return null;
		
		String name = (String) mapToName.t(map);
		if(name==null) return null;
		
		T trans = (T) uniqueEntity.t(name);
		return (JComponent) trans.t(map);
	}
}
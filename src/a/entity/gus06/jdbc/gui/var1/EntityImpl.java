package a.entity.gus06.jdbc.gui.var1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.sql.Connection;
import java.awt.BorderLayout;
import java.util.Map;

public class EntityImpl implements Entity, E, I, P {

	public String creationDate() {return "20150625";}


	private Service findVariableMap;
	private Service mapViewer;
	private Service executeF5;
	
	private G holder;
	


	public EntityImpl() throws Exception
	{
		findVariableMap = Outside.service(this,"gus06.jdbc.generic.perform.find.variablemap");
		mapViewer = Outside.service(this,"*gus06.data.viewer.map.stringmap");
		executeF5 = Outside.service(this,"gus.x.swing.comp.cust3.execute.f5");
		
		executeF5.p(new Object[]{mapViewer.i(),this});
	}
	
	
	
	public Object i() throws Exception
	{return mapViewer.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		holder = (G) obj;
		updateGui();
	}


	public void e() throws Exception
	{updateGui();}
	
	
	
	private void updateGui()
	{
		try
		{
			if(holder==null) return;
			Connection cx = (Connection) holder.g();
			Map map = (Map) findVariableMap.t(cx);
			mapViewer.p(map);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
}

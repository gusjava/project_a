package a.entity.gus06.map.string.editor1;

import a.framework.*;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20141229";}

	
	private Service buildSupport;
	private Service shiftPanel;
	private Service gui1;
	private Service gui2;
	

	public EntityImpl() throws Exception
	{
		buildSupport = Outside.service(this,"gus06.map.build.supportmap");
		shiftPanel = Outside.service(this,"*gus06.swing.panel.shiftpanel");
		gui1 = Outside.service(this,"*gus06.map.string.editor1.gui1");
		gui2 = Outside.service(this,"*gus06.map.string.editor1.gui2");
		
		gui1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{gui1Performed();}
		});
		gui2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{gui2Performed();}
		});
		
		shiftPanel.p(gui1);
	}
	
	

	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
	public void p(Object obj) throws Exception
	{
		Map map = toSupportMap((Map) obj);
		
		gui1.p(map);
		gui2.p(map);
		shiftPanel.p(gui1);
	}
	
	
	private Map toSupportMap(Map m) throws Exception
	{
		if(m==null) return null;
		if(m instanceof S) return m;
		return (Map) buildSupport.t(m);
	}


	private void gui1Performed()
	{
		try
		{
			String key = (String) gui1.r("editedKey");
			gui2.v("editedKey",key);
			shiftPanel.p(gui2);
			
			SwingUtilities.invokeLater(new Runnable(){
				public void run()
				{focusArea();}
			});
		}
		catch(Exception e)
		{Outside.err(this,"gui1Performed()",e);}
	}
	
	
	private void gui2Performed()
	{
		try
		{
			shiftPanel.p(gui1);
		}
		catch(Exception e)
		{Outside.err(this,"gui2Performed()",e);}
	}
	
	
	private void focusArea()
	{
		try
		{
			JComponent comp = (JComponent) gui2.r("comp");
			comp.requestFocusInWindow();
		}
		catch(Exception e)
		{Outside.err(this,"focusArea()",e);}
	}
}
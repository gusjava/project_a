package a.entity.gus06.sys.mapediting1.gui.label;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EntityImpl implements Entity, I, P, ActionListener {

	public String creationDate() {return "20161209";}


	private Map map;
	
	private JLabel label;

	

	public EntityImpl() throws Exception
	{
		label = new JLabel(" ");
	}
	
	
	public Object i() throws Exception
	{return label;}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		if(map!=null && map instanceof S)
		((S) map).removeActionListener(this);
		
		map = (Map) obj;
		
		if(map!=null && map instanceof S)
		((S) map).addActionListener(this);
		
		updateGui();
	}
	
	
	
	public void actionPerformed(ActionEvent evt)
	{updateGui();}
	
	
	private void updateGui()
	{
		String s = map!=null ? ""+map.size() : "";
		label.setText(" Number: "+s);
	}
}

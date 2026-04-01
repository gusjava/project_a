package a.entity.gus06.sys.filemanagement1.gui.detailpanel.titlelabel;

import a.framework.*;
import javax.swing.JLabel;
import java.util.Map;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.BorderFactory;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20191214";}

	public static final String KEY_NAME = "name";
	
	
	private Service findForeground;
	private Service findIcon;
	private Service formatSize;


	private JLabel label;
	
	public EntityImpl() throws Exception
	{
		findForeground = Outside.service(this,"gus06.sys.filemanagement1.explore.treerenderer1.findforeground");
		findIcon = Outside.service(this,"gus06.sys.filemanagement1.explore.treerenderer1.findicon");
		formatSize = Outside.service(this,"gus06.string.transform.format.datasize.en");
		
		label = new JLabel(" ");
		label.setOpaque(true);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createRaisedBevelBorder());
	}
	
	
	public Object i() throws Exception
	{return label;}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		Map map = (Map) obj;
		
		Color color = (Color) findForeground.t(map);
		Icon icon = (Icon) findIcon.t(map);
		String name = (String) map.get(KEY_NAME);
		
		label.setIcon(icon);
		label.setForeground(color);
		label.setText(name);
	}
	
	
	private void reset()
	{
		label.setIcon(null);
		label.setText(" ");
	}
}

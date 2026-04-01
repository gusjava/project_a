package a.entity.gus06.dir.explorer.resource2.icon;

import a.framework.*;
import javax.swing.JComponent;
import java.io.File;
import java.util.Map;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntityImpl implements Entity, P, I {

	public String creationDate() {return "20150614";}
	
	
	private Service explorer;
	private Service fieldHolder;
	private Service dirToMap;
	private Service mapFilter;
	
	private File dir;
	
	private JPanel panel;
	
	
	
	public EntityImpl() throws Exception
	{
		explorer = Outside.service(this,"*gus06.dir.explorer.resource.icon");
		fieldHolder = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		dirToMap = Outside.service(this,"gus06.icon.gui.icondir.dirtomap");
		mapFilter = Outside.service(this,"gus06.map.key.filter2");
		
		fieldHolder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {refresh();}
		});
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) fieldHolder.i(),BorderLayout.NORTH);
		panel.add((JComponent) explorer.i(),BorderLayout.CENTER);
		
		G mapBuilder = new G(){
			public Object g() throws Exception {return buildMap();}
		};
		explorer.v("mapBuilder",mapBuilder);
	}
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		dir = (File) obj;
		explorer.p(dir);
	}
	
	
	private Map buildMap() throws Exception
	{
		String input = (String) fieldHolder.g();
		Map map = (Map) dirToMap.t(dir);
		return (Map) mapFilter.t(new Object[]{map,input});
	}
	
	
	private void refresh()
	{
		try
		{explorer.e();}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
}

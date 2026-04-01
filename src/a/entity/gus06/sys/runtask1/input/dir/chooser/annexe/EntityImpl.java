package a.entity.gus06.sys.runtask1.input.dir.chooser.annexe;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;
import java.util.Map;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20160913";}
	
	public static final Dimension DIM = new Dimension(800,400);


	private Service findEntityMap;
	private Service srcPanel;
	private Service docPanel;
	private Service tab;
	private Service persist;
	
	private JPanel panel;
	private JLabel label;
	
	private Map map;
	private String key;
	
	

	public EntityImpl() throws Exception
	{
		findEntityMap = Outside.service(this,"gus06.sys.runtask1.input.dir.chooser.names");
		srcPanel = Outside.service(this,"*gus06.data.viewer.class1.panel.src");
		docPanel = Outside.service(this,"*gus06.data.viewer.class1.panel.doc");
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		persist = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
		
		map = (Map) findEntityMap.g();
		
		label = new JLabel(" ");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createEtchedBorder());
		
		tab.v("Presentation",docPanel.i());
		tab.v("Source code",srcPanel.i());
		
		persist.v(getClass().getName()+"_tab",tab.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		panel.add((JComponent) tab.i(),BorderLayout.CENTER);
		
		panel.setPreferredSize(DIM);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		key = (String) obj;
		Object data = map.get(key);
		Class c = data!=null ? data.getClass() : null;
		
		label.setText(key);
		srcPanel.p(c);
		docPanel.p(c);
	}
}

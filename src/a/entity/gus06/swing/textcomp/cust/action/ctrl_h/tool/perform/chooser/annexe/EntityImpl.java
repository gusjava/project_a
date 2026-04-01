package a.entity.gus06.swing.textcomp.cust.action.ctrl_h.tool.perform.chooser.annexe;

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

	public String creationDate() {return "20160507";}
	
	public static final Dimension DIM = new Dimension(800,400);


	private Service findEntityMap;
	private Service testerPanel;
	private Service srcPanel;
	private Service docPanel;
	private Service tab;
	private Service persist;
	
	private JPanel panel;
	private JLabel label;
	
	private Map map;
	private String key;
	private T t;
	private Class c;
	
	

	public EntityImpl() throws Exception
	{
		findEntityMap = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_h.tool.perform.chooser.names");
		testerPanel = Outside.service(this,"*gus06.data.viewer.t.stringtransform.tester1");
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
		tab.v("Tester",testerPanel.i());
		
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
		t = (T) map.get(key);
		c = t!=null?t.getClass():null;
		
		label.setText(key);
		testerPanel.p(t);
		srcPanel.p(c);
		docPanel.p(c);
	}
}

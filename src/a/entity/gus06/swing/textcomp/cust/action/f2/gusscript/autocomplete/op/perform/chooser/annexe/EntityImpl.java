package a.entity.gus06.swing.textcomp.cust.action.f2.gusscript.autocomplete.op.perform.chooser.annexe;

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

	public String creationDate() {return "20180203";}
	
	public static final Dimension DIM = new Dimension(800,400);


	private Service srcPanel;
	private Service docPanel;
	private Service opToClass;
	private Service tab;
	
	private JPanel panel;
	private JLabel label;
	
	private String op;
	private Class c;
	

	public EntityImpl() throws Exception
	{
		srcPanel = Outside.service(this,"*gus06.data.viewer.class1.panel.src");
		docPanel = Outside.service(this,"*gus06.data.viewer.class1.panel.doc");
		opToClass = Outside.service(this,"gus06.sys.expression1.apply.opdata.class1");
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		
		
		label = new JLabel(" ");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createEtchedBorder());
		
		tab.v("Presentation",docPanel.i());
		tab.v("Source code",srcPanel.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		panel.add((JComponent) tab.i(),BorderLayout.CENTER);
		
		panel.setPreferredSize(DIM);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		op = (String) obj;
		c = (Class) opToClass.t(op);
		
		label.setText(op);
		srcPanel.p(c);
		docPanel.p(c);
	}
}

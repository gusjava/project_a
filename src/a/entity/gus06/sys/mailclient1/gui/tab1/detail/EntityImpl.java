package a.entity.gus06.sys.mailclient1.gui.tab1.detail;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.mail.Message;
import javax.swing.JTextArea;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JComponent;
import java.io.File;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20201117";}


	private Service holderToTitle;
	private Service control;

	private JPanel panel;
	private JLabel label;
	private JTextArea area;
	
	private Object holder;

	public EntityImpl() throws Exception
	{
		holderToTitle = Outside.service(this,"gus06.sys.mailclient1.tool.message.holdertotitle");
		control = Outside.service(this,"*gus06.sys.mailclient1.gui.tab1.control");
		
		JComponent controlComp = (JComponent) control.i();
		
		label = new JLabel(" ");
		
		area = new JTextArea();
		area.setEditable(false);
		area.setMargin(new Insets(3,3,3,3));
		
		JPanel panelTop = new JPanel(new BorderLayout());
		panelTop.setBorder(BorderFactory.createRaisedBevelBorder());
		panelTop.add(label,BorderLayout.CENTER);
		panelTop.add(controlComp,BorderLayout.EAST);
		
		panel = new JPanel(new BorderLayout());
		panel.add(panelTop,BorderLayout.NORTH);
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		holder = obj;
		control.p(holder);
		
		if(holder==null) {reset();return;}
		
		String title = (String) holderToTitle.t(holder);
		Icon icon = (Icon) ((R) holder).r("icon");
		String content = (String) ((R)holder).r("msgAsText");
		
		label.setText(title);
		label.setIcon(icon);
		area.setText(content.trim());
		area.setCaretPosition(0);
	}
	
	
	private void reset()
	{
		label.setText(" ");
		label.setIcon(null);
		area.setText("");
	}
}
package a.entity.gus06.sys.mailclient1.gui.tab1.messages;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.mail.Folder;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class EntityImpl extends S1 implements Entity, ActionListener, I, P, G, V {

	public String creationDate() {return "20201114";}


	private Service tableHolder;
	private Service shiftPanel;

	private JPanel panel;
	private JLabel label;
	
	private Object holder;
	private Folder folder;
	private File root;


	public EntityImpl() throws Exception
	{
		tableHolder = Outside.service(this,"*gus06.sys.mailclient1.gui.tab1.messages.table");
		shiftPanel = Outside.service(this,"*gus06.swing.panel.shiftpanel");
		
		label = new JLabel(" ");
		label.setBorder(BorderFactory.createRaisedBevelBorder());
		
		panel = new JPanel(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		panel.add((JComponent) shiftPanel.i(),BorderLayout.CENTER);
		
		tableHolder.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return tableHolder.g();}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(folder!=null) folder.close();
		
		holder = obj;
		if(holder==null) {reset();return;}
		
		folder = (Folder) ((R) holder).r("folder");
		if(folder.getName().equals("")) {reset();return;}
		
		((E)holder).e();
		
		Icon icon = (Icon) ((R) holder).r("icon");
		String fullTitle = (String) ((R) holder).r("fullTitle");
		
		label.setIcon(icon);
		label.setText(fullTitle);
		
		folder.open(Folder.READ_ONLY);
		
		shiftPanel.p(tableHolder.i());
		tableHolder.p(folder);
	}
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("root")) {setRoot((File) obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void setRoot(File root) throws Exception
	{
		this.root = root;
		tableHolder.v("root", root);
	}
	
	
	
	
	private void reset() throws Exception
	{
		holder = null;
		folder = null;
		shiftPanel.p(null);
		
		label.setIcon(null);
		label.setText(" ");
		tableHolder.p(null);
	}


	public void actionPerformed(ActionEvent e)
	{selected();}
	
	private void selected()
	{send(this,"selected()");}
}
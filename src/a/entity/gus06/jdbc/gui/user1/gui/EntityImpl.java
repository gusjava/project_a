package a.entity.gus06.jdbc.gui.user1.gui;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class EntityImpl implements Entity, P, I, ActionListener {

	public String creationDate() {return "20150625";}



	
	private JPanel panel;
	private JLabel titleLabel;
	
	private G holder;
	private String name;
	private String user;
	private String host;
	
	

	public EntityImpl() throws Exception
	{
		titleLabel = new JLabel(" ");
        	titleLabel.setHorizontalAlignment(JLabel.CENTER);
        	titleLabel.setBorder(BorderFactory.createRaisedBevelBorder());

		panel = new JPanel(new BorderLayout());
		panel.add(titleLabel,BorderLayout.NORTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null)
		{
			setHolder(null);
			name = null;
			user = null;
			host = null;
			
			resetGui();
			return;
		}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		setHolder(o[0]);
		name = (String) o[1];
		user = name.split("@")[0];
		host = name.split("@")[1];
		
		updateGui();
	}
	
	
	
	private void setHolder(Object obj) throws Exception
	{
		if(holder!=null) ((S) holder).removeActionListener(this);
		holder = (G) obj;
		if(holder!=null) ((S) holder).addActionListener(this);
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{updateGui();}
	
	
	
	
	private void updateGui()
	{
		try
		{
			titleLabel.setText(name);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
	
	
	
	private void resetGui()
	{
		try
		{
			titleLabel.setText(" ");
		}
		catch(Exception e)
		{Outside.err(this,"resetGui()",e);}
	}
}

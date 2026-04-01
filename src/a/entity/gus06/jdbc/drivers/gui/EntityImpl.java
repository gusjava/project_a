package a.entity.gus06.jdbc.drivers.gui;

import a.framework.*;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20191112";}


	private JPanel panel;
	private JTextArea area;
	private JButton button_update;
	
	private void println(String m)
	{area.append(m+"\n");}
	


	public EntityImpl() throws Exception
	{
		area = new JTextArea();
		area.setEditable(false);
		
		button_update = new JButton("update");
		button_update.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
		panel.add(button_update,BorderLayout.SOUTH);
		
		update();
	}
	
	
	public Object i() throws Exception
	{return panel;}

	
	
	public void actionPerformed(ActionEvent e)
	{update();}
	
	
	
	private void update()
	{
		area.setText("");
		Enumeration en = DriverManager.getDrivers();
		while(en.hasMoreElements())
		{
			Driver driver = (Driver) en.nextElement();
			println("class = "+driver.getClass().getName());
			println("major version = "+driver.getMajorVersion());
			println("minor version = "+driver.getMinorVersion());
			println("jdbc compliant = "+driver.jdbcCompliant());
			println("");
		}
		println("login timeout = "+DriverManager.getLoginTimeout());
	}
}
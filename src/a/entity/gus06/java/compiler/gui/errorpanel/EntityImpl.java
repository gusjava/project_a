package a.entity.gus06.java.compiler.gui.errorpanel;

import a.framework.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.util.*;


public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20140726";}

	private Service errorHolder;

	private JPanel panel;
	private JLabel label;
	private JTextArea area;


	public EntityImpl() throws Exception
	{
		errorHolder = Outside.service(this,"gus06.java.compiler.errorholder");

		label = new JLabel(" ");

		area = new JTextArea();
		area.setEditable(false);

		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);

		errorHolder.addActionListener(this);
		updateGui();
	}
	
	
	public Object i() throws Exception
	{return panel;}


	public void actionPerformed(ActionEvent e)
	{updateGui();}


	private void updateGui()
	{
		try
		{
			List errors = (List) errorHolder.g();
			int number = errors.size();

			label.setText(" "+number);
			area.setText("");

			for(int i=0;i<number;i++)
			{
				Map info = (Map) errors.get(i);

				println("file: "+get(info,"file"));
				println("lineNb: "+get(info,"lineNb"));
				println("line: "+get(info,"line"));
				println("pos: "+get(info,"pos"));
				println("description: "+get(info,"description"));
				println("");
			}
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}


	private void println(String line)
	{area.append(line+"\n");}


	private String get(Map info, String key) throws Exception
	{
		if(!info.containsKey(key)) throw new Exception("Key not found inside info map: "+key);
		return info.get(key).toString();
	}
}
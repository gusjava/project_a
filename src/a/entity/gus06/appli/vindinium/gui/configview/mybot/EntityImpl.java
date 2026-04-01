package a.entity.gus06.appli.vindinium.gui.configview.mybot;

import a.framework.*;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20170923";}

	public static final String TITLE = "My Bot";
	
	public static final int EDGE = 10;
	

	
	private Service botProvider;
	private Service botComboBox;
	private Service persistence1;
	private Service titledBorder;
	
	private JPanel panel;
	private JComboBox combo;




	public EntityImpl() throws Exception
	{
		botProvider = Outside.service(this,"gus06.appli.vindinium.bot.provider");
		botComboBox = Outside.service(this,"gus06.appli.vindinium.bot.builder.combobox");
		persistence1 = Outside.service(this,"gus06.app.persister1.manager.swing");
		titledBorder = Outside.service(this,"gus06.swing.comp.cust2.border.titledborder1.m10");
		
		combo = (JComboBox) botComboBox.g();
		persistence1.v(getClass().getName()+"_combo",combo);
		
		panel = new JPanel(new BorderLayout());
		panel.add(left(combo),BorderLayout.NORTH);
		
		panel.setBorder(BorderFactory.createEmptyBorder(EDGE,EDGE,EDGE,EDGE));
		titledBorder.v(TITLE,panel);
		
		combo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {updateBotProvider();}
		});
		updateBotProvider();
	}



	public Object i() throws Exception
	{return panel;}


	
	
	

	
	
	
	private void updateBotProvider()
	{
		try
		{
			String botname = (String) combo.getSelectedItem();
			botProvider.v("botname",botname);
		}
		catch(Exception e)
		{Outside.err(this,"updateBotProvider()",e);}
	}
	
	
	
	
	private JComponent left(JComponent c)
	{
		JPanel p = new JPanel(new BorderLayout());
		p.add(c,BorderLayout.WEST);
		return p;
	}
}
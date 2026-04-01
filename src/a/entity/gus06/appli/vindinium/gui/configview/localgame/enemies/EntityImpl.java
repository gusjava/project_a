package a.entity.gus06.appli.vindinium.gui.configview.localgame.enemies;

import a.framework.*;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20170923";}

	public static final String TITLE = "Enemy bots";
	
	
	private Service enemyProvider;
	private Service botComboBox;
	private Service persistence1;
	private Service form;
	private Service titledBorder;
	
	private JPanel panel;

	private JComboBox combo_bot1;
	private JComboBox combo_bot2;
	private JComboBox combo_bot3;


	public EntityImpl() throws Exception
	{
		enemyProvider = Outside.service(this,"gus06.appli.vindinium.engine.enemyprovider");
		botComboBox = Outside.service(this,"gus06.appli.vindinium.bot.builder.combobox");
		persistence1 = Outside.service(this,"gus06.app.persister1.manager.swing");
		form = Outside.service(this,"*gus06.swing.panel.formpanel");
		titledBorder = Outside.service(this,"gus06.swing.comp.cust2.border.titledborder1.m10");
		
		
		combo_bot1 = (JComboBox) botComboBox.g();
		combo_bot2 = (JComboBox) botComboBox.g();
		combo_bot3 = (JComboBox) botComboBox.g();

		persistence1.v(getClass().getName()+"combo_bot1",combo_bot1);
		persistence1.v(getClass().getName()+"combo_bot2",combo_bot2);
		persistence1.v(getClass().getName()+"combo_bot3",combo_bot3);
		
		form.v("Bot 1",combo_bot1);
		form.v("Bot 2",combo_bot2);
		form.v("Bot 3",combo_bot3);
		
		panel = left((JComponent) form.i());
		titledBorder.v(TITLE,panel);
		
		ItemListener listener = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {updateBots();}
		};
		
		combo_bot1.addItemListener(listener);
		combo_bot2.addItemListener(listener);
		combo_bot3.addItemListener(listener);
		updateBots();
	}


	public Object i() throws Exception
	{return panel;}



	private void updateBots()
	{
		try
		{
			String botname1 = (String) combo_bot1.getSelectedItem();
			String botname2 = (String) combo_bot2.getSelectedItem();
			String botname3 = (String) combo_bot3.getSelectedItem();
			
			enemyProvider.v("botnames",new String[]{botname1,botname2,botname3});
		}
		catch(Exception e)
		{Outside.err(this,"updateBots()",e);}
	}
	
	
	private JPanel left(JComponent c)
	{
		JPanel p = new JPanel(new BorderLayout());
		p.add(c,BorderLayout.WEST);
		return p;
	}
}

package a.entity.gus06.appli.vindinium.gui.configview.localgame.mapchooser;

import a.framework.*;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20170923";}

	public static final String TITLE = "Map chooser";
	
	
	private Service mapHolder;
	private Service mapsComboBox;
	private Service persistence1;
	private Service titledBorder;
	
	private JPanel panel;
	private JCheckBox check_randomMap;
	private JComboBox combo_maps;
	


	public EntityImpl() throws Exception
	{
		mapHolder = Outside.service(this,"gus06.appli.vindinium.engine.getinitial.findfile");
		mapsComboBox = Outside.service(this,"gus06.appli.vindinium.map.combobox");
		persistence1 = Outside.service(this,"gus06.app.persister1.manager.swing");
		titledBorder = Outside.service(this,"gus06.swing.comp.cust2.border.titledborder1.mp10");
		
		combo_maps = (JComboBox) mapsComboBox.g();
		check_randomMap = new JCheckBox("Random map");
		
		persistence1.v(getClass().getName()+"_maps",combo_maps);
		persistence1.v(getClass().getName()+"_randomMap",check_randomMap);
		
		panel = new JPanel(new GridLayout(2,1,5,5));
		panel.add(left(combo_maps));
		panel.add(left(check_randomMap));
		
		titledBorder.v(TITLE,panel);
		
		check_randomMap.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {update();}
		});
		combo_maps.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {update();}
		});
		update();
	}


	public Object i() throws Exception
	{return panel;}

	
	
	private void update()
	{
		try
		{mapHolder.p(newName());}
		catch(Exception e)
		{Outside.err(this,"update()",e);}
	}

	
	
	private String newName()
	{
		boolean isRandom = check_randomMap.isSelected();
		combo_maps.setEnabled(!isRandom);
		if(isRandom) return null;
		return (String) combo_maps.getSelectedItem();
	}
	
	
	
	private JPanel left(JComponent c)
	{
		JPanel p = new JPanel(new BorderLayout());
		p.add(c,BorderLayout.WEST);
		return p;
	}
}

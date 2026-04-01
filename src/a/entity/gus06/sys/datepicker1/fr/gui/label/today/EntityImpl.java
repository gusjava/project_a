package a.entity.gus06.sys.datepicker1.fr.gui.label.today;

import a.framework.*;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl extends S1 implements Entity, ActionListener, I, G {

	public String creationDate() {return "20160617";}


	private Service getToday;
	private Service buildDisplay;
	private Service colorManager;
	private Service onClick;


	private JLabel label;
	private int[] today;
	private String display;
	private Color color;
	

	public EntityImpl() throws Exception
	{
		getToday = Outside.service(this,"gus06.time.date.int3.today");
		buildDisplay = Outside.service(this,"gus06.time.date.int3.display1.fr");
		colorManager = Outside.service(this,"gus06.sys.datepicker1.fr.color.manager");
		onClick = Outside.service(this,"gus06.swing.label.support.onclick");
		
		today = (int[]) getToday.g();
		display = (String) buildDisplay.t(today);
		color = (Color) colorManager.r("today");
		
		label = new JLabel();
		label.setText("Aujourd'hui: "+display);
		label.setForeground(color);
		
		S sup = (S) onClick.t(label);
		sup.addActionListener(this);
	}
	
	
	public Object g() throws Exception
	{return today;}
	
	
	public Object i() throws Exception
	{return label;}


	public void actionPerformed(ActionEvent e)
	{clicked();}
	
	
	private void clicked()
	{send(this,"clicked()");}

}

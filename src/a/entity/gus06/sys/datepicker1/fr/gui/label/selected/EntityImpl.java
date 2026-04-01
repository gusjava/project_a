package a.entity.gus06.sys.datepicker1.fr.gui.label.selected;

import a.framework.*;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl extends S1 implements Entity, ActionListener, I, G, P {

	public String creationDate() {return "20160618";}


	private Service buildDisplay;
	private Service colorManager;
	private Service onClick;


	private JLabel label;
	private int[] data;
	
	public EntityImpl() throws Exception
	{
		buildDisplay = Outside.service(this,"gus06.time.date.int3.display1.fr");
		colorManager = Outside.service(this,"gus06.sys.datepicker1.fr.color.manager");
		onClick = Outside.service(this,"gus06.swing.label.support.onclick");
		
		label = new JLabel();
		label.setForeground((Color) colorManager.r("selected"));
		
		S sup = (S) onClick.t(label);
		sup.addActionListener(this);
	}
	
	
	public void p(Object obj) throws Exception
	{data = (int[]) obj; updateGui();}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return label;}


	public void actionPerformed(ActionEvent e)
	{clicked();}
	
	
	private void clicked()
	{send(this,"clicked()");}



	private void updateGui() throws Exception
	{
		String display = (String) buildDisplay.t(data);
		label.setText("S�lectionn�: "+display);
	}
}

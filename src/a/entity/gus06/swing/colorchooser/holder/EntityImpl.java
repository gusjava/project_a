package a.entity.gus06.swing.colorchooser.holder;

import java.awt.Color;
import a.framework.*;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EntityImpl extends S1 implements Entity, I, G, P, ChangeListener {

	public String creationDate() {return "20160427";}
	
	private JColorChooser chooser;
	
	
	
	public EntityImpl()
	{
		chooser = new JColorChooser();
		chooser.getSelectionModel().addChangeListener(this);
	}
	
	

	public Object i() throws Exception
	{return chooser;}
	
	
	public void stateChanged(ChangeEvent e)
	{colorEdited();}
	
	

	private void colorEdited()
	{send(this,"colorEdited()");}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		Color c = (Color) obj;
		if(c==null) return;
		
		chooser.getSelectionModel().removeChangeListener(this);
		chooser.setColor(c);
		chooser.getSelectionModel().addChangeListener(this);
	}
	
	
	
	public Object g() throws Exception
	{return chooser.getColor();}
}

package a.entity.gus06.sys.filemanagement1.gui.gui1_4.analyze.stats;

import a.framework.*;
import java.io.PrintStream;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20201014";}


	private Service factoryGui;
	private Service comboHolder;
	
	private JPanel panel;
	private Object engine;
	

	public EntityImpl() throws Exception
	{
		factoryGui = Outside.service(this,"*gus06.data.factory.gui2.console");
		comboHolder = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui1_4.analyze.stats.combo");
		
		P factory = obj->build((PrintStream) obj);
		
		factoryGui.v("buttonText","Build stats");
		factoryGui.p(factory);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) comboHolder.i(),BorderLayout.NORTH);
		panel.add((JComponent) factoryGui.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{engine = obj;}
	
	
	
	private void build(PrintStream p) throws Exception
	{
		if(engine==null) return;
		
		P handler = (P) comboHolder.g();
		if(handler==null) return;
		
		handler.p(new Object[]{engine,p});
	}
}
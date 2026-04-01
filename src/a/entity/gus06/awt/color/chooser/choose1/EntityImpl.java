package a.entity.gus06.awt.color.chooser.choose1;

import a.framework.*;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250309";}


	private Service dialog;
	private Service buildTitledPanel;

	public EntityImpl() throws Exception
	{
		dialog = Outside.service(this,"*gus06.swing.dialog.blocked1.cancel");
		buildTitledPanel = Outside.service(this,"gus06.swing.comp.build.titledpanel");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Color[] colors = (Color[]) obj;
		int nb = colors.length;
		
		int x = ((int) Math.sqrt(nb));
		int y = nb==x*x ? x : x+1;
		
		E execute = this::performOk;
		
		JPanel0 panel0 = new JPanel0(x, y, execute, colors);
		JPanel panel1 = (JPanel) buildTitledPanel.t(new Object[]{panel0, "COLOR CHOOSER"});
		boolean ok = dialog.f(panel1);
		
		return ok ? panel0.getSelected() : null;
	}
	
	
	
	private void performOk()
	{
		try{dialog.v("do","ok");}
		catch(Exception e)
		{Outside.err(this,"performOk()",e);}
	}
	
	
	
	
	private class JPanel0 extends JPanel implements ActionListener
	{
		private E execute;
		private Color[] colors;
		private Color selected;
		
		public JPanel0(int x, int y, E execute, Color[] colors)
		{
			super(new GridLayout(x, y, 5, 5));
			this.execute = execute;
			this.colors = colors;
			
			for(int i=0;i<colors.length;i++)
			{
				JButton button = new JButton();
				button.addActionListener(this);
				button.setOpaque(true);
				button.setBackground(colors[i]);
				add(button);
			}
		}
		
		public void actionPerformed(ActionEvent e)
		{
			JButton b = (JButton) e.getSource();
			selected = b.getBackground();
			execute(execute);
		}
		
		public Color getSelected()
		{return selected;}
	}
	
	
	
		
	private void execute(E execute)
	{
		try{execute.e();}
		catch(Exception e)
		{Outside.err(this,"execute()",e);}
	}
}
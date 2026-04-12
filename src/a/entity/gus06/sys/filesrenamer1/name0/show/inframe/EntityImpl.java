package a.entity.gus06.sys.filesrenamer1.name0.show.inframe;

import a.framework.*;
import java.io.File;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250210";}
	
	public static Dimension DIM = new Dimension(1200,600);

	private Service show;
	private Service newGui;

	public EntityImpl() throws Exception
	{
		show = Outside.service(this,"gus06.swing.frame.show");
		newGui = Outside.service(this,"factory#gus06.sys.filesrenamer1.name0.gui.main");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File dir = (File) obj;
		Object gui = newGui.g();
		((P)gui).p(dir);
		
		JComponent comp = (JComponent) ((I)gui).i();
		
		JButton buttonOk = new JButton("Ok");
		JButton buttonCancel = new JButton("Cancel");
		
		JPanel panelButtons = new JPanel(new GridLayout(1,2));
		panelButtons.add(buttonCancel);
		panelButtons.add(buttonOk);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(comp, BorderLayout.CENTER);
		panel.add(panelButtons, BorderLayout.SOUTH);
		
		JFrame frame = (JFrame) show.t(panel);
		
		frame.setSize(DIM);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Filename0 renamer: "+dir.getAbsolutePath());
		
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{frame.setVisible(false);}
		});
		buttonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{execute((E)gui);}
		});
		((S)gui).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{frame.setVisible(false);}
		});
		
		JTextComponent area2 = (JTextComponent) ((R)gui).r("area2");
		area2.requestFocusInWindow();
	}
	
	
	private void execute(E execute)
	{
		try{execute.e();}
		catch(Exception e)
		{Outside.err(this,"execute(E)",e);}
	}
}
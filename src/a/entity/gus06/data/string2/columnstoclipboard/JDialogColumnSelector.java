package a.entity.gus06.data.string2.columnstoclipboard;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;



public class JDialogColumnSelector extends JDialog implements ActionListener {

	
	private JButton button;
	private JPanel panel;
	private JRadioButton[] radio;
	
	
	public JDialogColumnSelector(JFrame parent)
	{
		super(parent,"Please select ",true);
		setSize(400,250);
		setLocationRelativeTo(null);
		
		button = new JButton("OK");
		button.addActionListener(this);
		
		panel = new JPanel(new GridLayout(0,1));
		radio = new JRadioButton[0];
		
		getContentPane().add(panel,BorderLayout.CENTER);
		getContentPane().add(button,BorderLayout.SOUTH);
	}
	

	public void actionPerformed(ActionEvent e)
	{dispose();}
	

	
	public void updateGui(int number)
	{
		panel.removeAll();
		radio = new JRadioButton[number];
		for(int i=0;i<number;i++)
		{
			radio[i] = new JRadioButton(""+(i+1));
			panel.add(radio[i]);
		}
	}
	
	
	
	public void updateGui(int number, String[] names)
	{
		panel.removeAll();
		radio = new JRadioButton[number];
		for(int i=0;i<number;i++)
		{
			radio[i] = new JRadioButton(names[i]);
			panel.add(radio[i]);
		}
	}
	
	
	
	
	
	
	
	public boolean[] getSelected()
	{
		if(!hasSelected()) return null;
		
		boolean[] b = new boolean[radio.length];
		for(int i=0;i<radio.length;i++) b[i] = radio[i].isSelected();
		return b;
	}
	
	
	
	
	private boolean hasSelected()
	{
		for(int i=0;i<radio.length;i++)
			if(radio[i].isSelected()) return true;
		return false;
	}
	
}

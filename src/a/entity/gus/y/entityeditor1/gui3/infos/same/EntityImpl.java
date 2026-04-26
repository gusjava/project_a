package a.entity.gus.y.entityeditor1.gui3.infos.same;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.util.List;
import javax.swing.JButton;
import a.framework.*;

public class EntityImpl implements Entity, P, I, ActionListener {
	public String creationDate() {return "20260425";}

	private Service toString;

	private JPanel panel;
	private JTextArea area;
	private JButton button;

	private Object data;

	public EntityImpl() throws Exception
	{
		toString = Outside.service(this, "gus.x.tostring.list");

		area = new JTextArea();
		area.setMargin(new Insets(3, 3, 3, 3));
		area.setEditable(false);
		
		button = new JButton("Replace all by current");
		button.addActionListener(e->replaceAllByCurrent());

		panel = new JPanel(new BorderLayout());
		panel.add(titled(area, "Same Hash"), BorderLayout.CENTER);
		panel.add(button, BorderLayout.SOUTH);
	}

	private JPanel titled(JComponent comp, String title)
	{
		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel(title);
		label.setHorizontalAlignment(JLabel.CENTER);
		panel.add(label, BorderLayout.NORTH);
		panel.add(new JScrollPane(comp), BorderLayout.CENTER);
		return panel;
	}

	public void p(Object obj) throws Exception
	{
		if (data != null) ((S) data).removeActionListener(this);
		if (obj == null) {reset(); return;}
		
		data = obj;
		refresh();
		((S) data).addActionListener(this);
	}

	public Object i() throws Exception
	{return panel;}

	private void reset() throws Exception
	{
		data = null;
		area.setText("");
	}

	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if (s.equals("srcModified()"))
		{
			srcModified();
			return;
		}
	}

	private void srcModified()
	{
		try {refresh();}
		catch(Exception e)
		{Outside.err(this, "srcModified()", e);}
	}

	private void refresh() throws Exception
	{
		List same = (List) ((R) data).r("same");
		String s = (String) toString.t(same);
		area.setText(s);
	}
	
	private void replaceAllByCurrent()
	{
		try
		{
			
		}
		catch(Exception e)
		{Outside.err(this,"replaceAllByCurrent()",e);}
	}

}

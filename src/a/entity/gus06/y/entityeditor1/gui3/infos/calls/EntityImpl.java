package a.entity.gus06.y.entityeditor1.gui3.infos.calls;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import a.framework.*;

public class EntityImpl implements Entity, P, I, ActionListener {
	public String creationDate() {return "20251121";}

	private Service toString;

	private JPanel panel;
	private JTextArea area1;
	private JTextArea area2;

	private Object data;

	public EntityImpl() throws Exception
	{
		toString = Outside.service(this, "gus.x.tostring.set");

		area1 = new JTextArea();
		area1.setMargin(new Insets(3, 3, 3, 3));
		area1.setEditable(false);

		area2 = new JTextArea();
		area2.setMargin(new Insets(3, 3, 3, 3));
		area2.setEditable(false);

		panel = new JPanel(new GridLayout(2, 1));
		panel.add(titled(area1, "Services"));
		panel.add(titled(area2, "Resources"));
	}

	public Object i() throws Exception
	{return panel;}

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
		if (obj == null) {reset();return;}
		
		data = obj;
		refresh();
		((S) data).addActionListener(this);
	}

	private void reset() throws Exception
	{
		data = null;
		area1.setText("");
		area2.setText("");
	}

	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if (s.equals("srcModified()"))
		{srcModified();return;}
	}

	private void srcModified()
	{
		try{refresh();}
		catch(Exception e)
		{Outside.err(this, "srcModified()", e);}
	}

	private void refresh() throws Exception
	{
		Set services = (Set) ((R) data).r("services");
		String s1 = (String) toString.t(services);
		area1.setText(s1);

		Set resources = (Set) ((R) data).r("resources");
		String s2 = (String) toString.t(resources);
		area2.setText(s2);
	}
}

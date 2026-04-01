package a.entity.gus06.y.swing1.textcomp.cust.autoedit1.tab.perform;

import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251116";}

	public void p(Object obj) throws Exception
	{
		final JTextComponent comp = (JTextComponent) obj;
		String selection = comp.getSelectedText();
		if (selection == null || !selection.contains("\n")) return;
		final String s0 = moveRight(selection);
		SwingUtilities.invokeLater(() -> replaceText(comp, s0));
	}

	private String moveRight(String s)
	{
		StringBuffer b = new StringBuffer();
		String[] line = s.split("\n");
		for (int i = 0; i < line.length; i++)
		{
			if (i > 0) b.append("\t");
			b.append(line[i] + "\n");
		}

		if (b.length() > 0) b.deleteCharAt(b.length() - 1);
		return b.toString();
	}

	private void replaceText(JTextComponent comp, String s0)
	{
		try
		{
			int start = comp.getSelectionStart();
			comp.replaceSelection(s0);
			comp.select(start, start + s0.length());
		}
		catch (Exception e)
		{Outside.err(this, "replaceText(JTextComponent,String)", e);}
	}
}

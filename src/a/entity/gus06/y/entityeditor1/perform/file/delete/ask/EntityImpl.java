package a.entity.gus06.y.entityeditor1.perform.file.delete.ask;

import java.awt.Component;
import java.awt.Window;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import a.framework.*;

public class EntityImpl implements Entity, F, P {

	public String creationDate() {return "20251115";}

	public static final String TITLE = "File deletion";
	public static final String MESSAGE = "Please, confirm deletion for file: ";

	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this, "gus.y.entityeditor1.perform.file.delete");
	}

	public void p(Object obj) throws Exception
	{f(obj);}

	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 4) throw new Exception("Wrong data number: " + o.length);

		Object engine = o[0];
		String entityName = (String) o[1];
		String className = (String) o[2];
		Object anchor = o[3];

		Window window = SwingUtilities.getWindowAncestor((Component) anchor);

		String message = MESSAGE + "\n" + className;
		int r = JOptionPane.showConfirmDialog(window, message, TITLE, JOptionPane.YES_NO_OPTION);
		if (r != JOptionPane.YES_OPTION) return false;

		return perform.f(new Object[] { engine, entityName, className });
	}
}

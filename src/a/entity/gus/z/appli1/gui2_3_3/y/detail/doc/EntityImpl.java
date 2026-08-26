package a.entity.gus.z.appli1.gui2_3_3.y.detail.doc;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import a.framework.*;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20260506";}

	private Service findDocFile;
	private Service rootDir;
	private Service buildEditor;
	private Service undoRedo;

	private JTextArea area;
	private JPanel panel;

	private P editor;

	public EntityImpl() throws Exception
	{
		findDocFile = Outside.service(this, "gus.x.unit_y.doc1.fr.find.file");
		rootDir = Outside.service(this, "gus.y.srcroot1");
		buildEditor = Outside.service(this, "gus.x.swing.textcomp.build.fileditor");
		undoRedo = Outside.service(this, "gus.y.swing1.textcomp.cust.action.ctrl_zy.undoredo");

		area = new JTextArea();
		area.setMargin(new Insets(3, 3, 3, 3));
		undoRedo.p(area);

		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area), BorderLayout.CENTER);

		editor = (P) buildEditor.t(area);
	}

	public Object i() throws Exception {
		return panel;
	}

	public void p(Object obj) throws Exception {
		if (obj == null) {
			editor.p(null);
			return;
		}
		String yName = (String) obj;
		File docFile = (File) findDocFile.t(new Object[] { rootDir.g(), yName });
		editor.p(docFile);
	}
}
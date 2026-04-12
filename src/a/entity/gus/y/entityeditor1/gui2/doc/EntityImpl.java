package a.entity.gus.y.entityeditor1.gui2.doc;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import a.framework.*;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20240114";}
	
	private Service buildEditor;
	private Service undoRedo;

	private JTextArea area;
	private JPanel panel;

	private Object data;
	private P editor;
	private File docFile;

	public EntityImpl() throws Exception {
		buildEditor = Outside.service(this, "gus.x.swing.textcomp.build.fileditor");
		undoRedo = Outside.service(this,"gus.y.swing1.textcomp.cust.action.ctrl_zy.undoredo");

		area = new JTextArea();
		area.setMargin(new Insets(3, 3, 3, 3));
		undoRedo.p(area);

		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area), BorderLayout.CENTER);

		editor = (P) buildEditor.t(area);
	}

	public void p(Object obj) throws Exception {
		if (obj == null) {
			reset();
			return;
		}
		data = obj;
		docFile = (File) ((R) data).r("docFile");
		editor.p(docFile);
	}

	public Object i() throws Exception {
		return panel;
	}

	private void reset() throws Exception {
		data = null;
		docFile = null;
		editor.p(null);
	}
}

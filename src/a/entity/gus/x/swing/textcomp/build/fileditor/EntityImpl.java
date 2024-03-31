package a.entity.gus.x.swing.textcomp.build.fileditor;

import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import a.framework.Entity;
import a.framework.G;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.S1;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240118";}
	
	public Object t(Object obj) throws Exception {
		return new Holder((JTextComponent) obj);
	}

	private class Holder extends S1 implements DocumentListener, I, P, G {
		private JTextComponent comp;
		private File file;

		public Holder(JTextComponent comp) {
			this.comp = comp;
			comp.getDocument().addDocumentListener(this);
		}

		public void insertUpdate(DocumentEvent e) {
			save(file, comp.getText());
		}

		public void removeUpdate(DocumentEvent e) {
			save(file, comp.getText());
			saved();
		}

		public void changedUpdate(DocumentEvent e) {
		}

		public Object g() throws Exception {
			return file;
		}

		public void p(Object obj) throws Exception {
			file = (File) obj;
			comp.setEditable(file != null);
			setText(read(file));
		}

		public Object i() throws Exception {
			return comp;
		}

		private void setText(String s) {
			comp.getDocument().removeDocumentListener(this);
			comp.setText(s);
			comp.getDocument().addDocumentListener(this);
		}
		
		private void saved() {
			send(this, "saved()");
		}
	}

	private String read(File file) throws Exception {
		if (file == null || !file.isFile())
			return "";
		FileReader fr = new FileReader(file);
		char[] a = new char[(int) file.length()];
		fr.read(a, 0, (int) file.length());
		fr.close();
		return new String(a);
	}

	private void save(File file, String s) {
		try {
			PrintStream p = new PrintStream(file);
			p.print(s);
			p.close();
		} catch (Exception e) {
			Outside.err(this, "save(File,String)", e);
		}
	}
}

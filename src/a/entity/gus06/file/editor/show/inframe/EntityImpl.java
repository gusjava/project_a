package a.entity.gus06.file.editor.show.inframe;

import a.framework.*;
import javax.swing.JFrame;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180213";}


	private Service show;
	private Service newEditor;

	public EntityImpl() throws Exception
	{
		show = Outside.service(this,"gus06.swing.frame.show");
		newEditor = Outside.service(this,"factory#gus06.file.editor.main2");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		Object editor = newEditor.g();
		((P)editor).p(file);
		
		JFrame frame = (JFrame) show.t(((I)editor).i());
		frame.setTitle(file.getName());
		frame.setAlwaysOnTop(true);
		
		new Holder(editor,frame);
	}
	
	
	
		
	private void refresh(Object editor, JFrame frame)
	{
		try
		{
			File file = (File) ((G) editor).g();
			frame.setTitle(file.getName());
		}
		catch(Exception e)
		{Outside.err(this,"refresh(Object,JFrame)",e);}
	}
	
	
	
	private class Holder implements ActionListener
	{
		private Object editor;
		private JFrame frame;
		
		public Holder(Object editor, JFrame frame) throws Exception
		{
			this.editor = editor;
			this.frame = frame;
			((S) editor).addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e)
		{refresh(editor,frame);}
	}
}
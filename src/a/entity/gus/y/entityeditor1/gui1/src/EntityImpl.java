package a.entity.gus.y.entityeditor1.gui1.src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, P, I, ActionListener {
	public String creationDate() {return "20240113";}
	

	private Service shiftPanel;
	private Service panelSingle;
	private Service panelMany;
	
	private Object data;

	public EntityImpl() throws Exception
	{
		shiftPanel = Outside.service(this,"*gus.x.swing.panel.shiftpanel");
		panelSingle = Outside.service(this,"*gus.y.entityeditor1.gui1.src.single");
		panelMany = Outside.service(this,"*gus.y.entityeditor1.gui1.src.many");
	}

	public void p(Object obj) throws Exception
	{
		if (data != null) ((S) data).removeActionListener(this);
		if (obj == null){reset();return;}
		data = obj;
		refresh();
		((S) data).addActionListener(this);
	}

	public Object i() throws Exception
	{return shiftPanel.i();}

	private void reset() throws Exception
	{
		data = null;
		panelSingle.p(null);
		panelMany.p(null);
		shiftPanel.p(null);
	}
	
	private void refresh() throws Exception
	{
		File[] javaFiles = (File[]) ((R) data).r("javaFiles");
		if(javaFiles.length==1)
		{
			panelSingle.p(data);
			shiftPanel.p(panelSingle);
		}
		else
		{
			panelMany.p(data);
			shiftPanel.p(panelMany);
		}
	}

	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if (s.equals("fileAdded()")) {
			fileAdded();
			return;
		}
		if (s.equals("fileDeleted()")) {
			fileDeleted();
			return;
		}
		if (s.equals("fileRenamed()")) {
			fileRenamed();
			return;
		}
		if (s.equals("fileDuplicated()")) {
			fileDuplicated();
			return;
		}
	}
	
	private void fileAdded() {
		try {
			refresh();
		}
		catch(Exception e) {
			Outside.err(this, "fileAdded()", e);
		}
	}
	
	private void fileDeleted() {
		try {
			refresh();
		}
		catch(Exception e) {
			Outside.err(this, "fileDeleted()", e);
		}
	}
	
	private void fileRenamed() {
		try {
			refresh();
		}
		catch(Exception e) {
			Outside.err(this, "fileRenamed()", e);
		}
	}
	
	private void fileDuplicated() {
		try {
			refresh();
		}
		catch(Exception e) {
			Outside.err(this, "fileDuplicated()", e);
		}
	}
}

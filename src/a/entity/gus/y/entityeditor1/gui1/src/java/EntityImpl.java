package a.entity.gus.y.entityeditor1.gui1.src.java;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Objects;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import java.sql.Connection;
import javax.swing.event.DocumentListener;

import a.framework.*;

public class EntityImpl implements Entity, P, I, R, DocumentListener {
	public String creationDate() {return "20240113";}
	
	public static final String DISPLAY_SAVE = "ACTION_save#Save [Ctrl S]";
	public static final String DISPLAY_RELOAD = "ACTION_reload#Reload [Ctrl L]";

	private Service read;
	private Service write;
	private Service actionBuilder;
	private Service buildArea;
	private Service buildScroll;
	private Service buildHighSup;
	private Service buildHighScroll;
	private Service buildHighCount;
	private Service buildCaretPos;
	private Service barFactory;
	private Service custArea;
	private Service handleErr;
	private Service persistSrc;
	private Service restoreSrc;
	private Service clearSrc;
	
	private JPanel panel;
	private JTextArea area;
	private JScrollPane scroll;
	private JComponent countComp;
	private JComponent caretPosComp;
	private JToolBar bar2;

	private Action actionSave;
	private Action actionReload;

	private Object data;
	private File javaFile;
	private String entityName;
	private String fileName;
	private String text0;
	private boolean canModify = true;

	public EntityImpl() throws Exception {
		read = Outside.service(this,"gus.x.file.string.read.n");
		write = Outside.service(this,"gus.x.file.string.write.n");
		actionBuilder = Outside.service(this,"gus.y.swing1.action.builder1");
		buildArea = Outside.service(this,"gus.y.swing1.textarea1.factory");
		buildScroll = Outside.service(this,"gus.y.swing1.textarea.buildscrollpane.linenb");
		buildHighSup = Outside.service(this,"gus.y.swinghigh1.support");
		buildHighScroll = Outside.service(this,"gus.y.swinghigh1.scrollpaint");
		buildHighCount = Outside.service(this,"gus.y.swinghigh1.countbar");
		buildCaretPos = Outside.service(this,"gus.x.swing.textcomp.build.caretpositionlabel");
		barFactory = Outside.service(this,"gus.x.swing.toolbar.factory1");
		custArea = Outside.service(this,"gus.y.entityeditor1.gui1.src.java.custcomp");
		handleErr = Outside.service(this,"gus.y.entityeditor1.gui1.src.java.handle.err");
		persistSrc = Outside.service(this,"gus.y.entitydb1.entity_src_save.persist");
		restoreSrc = Outside.service(this,"gus.y.entitydb1.entity_src_save.find");
		clearSrc = Outside.service(this,"gus.y.entitydb1.entity_src_save.delete");
		
		actionSave = (Action) actionBuilder.t(new Object[] {DISPLAY_SAVE, (E) this::save_});
		actionReload = (Action) actionBuilder.t(new Object[] {DISPLAY_RELOAD, (E) this::reload_});
		
		area = (JTextArea) buildArea.i();
		scroll = (JScrollPane) buildScroll.t(area);
		custArea.p(area);
		
		S highSup = (S) buildHighSup.t(area);
		
		countComp = (JComponent) buildHighCount.t(area);
		caretPosComp = (JComponent) buildCaretPos.t(area);
		
		Object highScroll = buildHighScroll.t(scroll);
		highSup.addActionListener((ActionListener) countComp);
		highSup.addActionListener((ActionListener) highScroll);
		
		panel = new JPanel(new BorderLayout());
		panel.add(scroll, BorderLayout.CENTER);
		panel.add(bottomPanel(),BorderLayout.SOUTH);
		
		canModify = false;
		text0 = null;
		
		area.setEditable(false);
		actionSave.setEnabled(false);
		actionReload.setEnabled(false);
		
		area.getDocument().addDocumentListener(this);
		
		area.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if(e.isControlDown()){
					if(code==KeyEvent.VK_S) save();
					else if(code==KeyEvent.VK_L) reload();
					else if(code==KeyEvent.VK_Q) select();
				}
			}
		});
	}
	
	public void p(Object obj) throws Exception {
		if(data!=null) persistOrClearSrc();
		
		if(obj==null) {
			reset();
			return;
		}
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Object data1 = o[0];
		File javaFile1 = (File) o[1];
		
		if(Objects.equals(javaFile1, javaFile)) return;
		
		data = data1;
		javaFile = javaFile1;
		entityName = (String) ((R) data).r("entityName");
		fileName = javaFile.getName();

		canModify = canModifyEntity();
		text0 = readFile();

		area.getDocument().removeDocumentListener(this);
		area.setText(restoreOrLoadSrc());
		area.getDocument().addDocumentListener(this);
		
		displayCompilingErrors();

		area.setEditable(canModify);
		refreshActions();
		area.setCaretPosition(0);
	}
	
	private void reset() {
		data = null;
		javaFile = null;
		entityName = null;
		fileName = null;
		text0 = null;

		canModify = false;
		area.setEditable(false);
		actionSave.setEnabled(false);
		actionReload.setEnabled(false);
		
		area.getDocument().removeDocumentListener(this);
		area.setText("");
		area.getDocument().addDocumentListener(this);
	}

	public Object r(String key) throws Exception
	{
		if (key.equals("actionSave")) return actionSave;
		if (key.equals("actionReload")) return actionReload;
		if (key.equals("keys")) return new String[] { "actionSave", "actionReload" };
		throw new Exception("Unknown key: " + key);
	}

	public Object i() throws Exception {
		return panel;
	}

	private String readFile() throws Exception
	{return (String) read.t(javaFile);}

	public void insertUpdate(DocumentEvent e)
	{changed();}

	public void removeUpdate(DocumentEvent e)
	{changed();}

	public void changedUpdate(DocumentEvent e) {}

	private void changed() {
		try {
			if (!canModify) throw new Exception("canModify=false");
			if (text0 == null) throw new Exception("text0==null");
			refreshActions();
		} catch (Exception e) {
			Outside.err(this, "changed()", e);
		}
	}

	private JComponent bottomPanel() throws Exception {
		bar2 = (JToolBar) barFactory.i();
		bar2.add(countComp);
		bar2.addSeparator();
		bar2.add(caretPosComp);
		bar2.addSeparator();
		return bar2;
	}

	/*
	 * SAVE
	 */

	private void save()
	{
		try {save_();}
		catch (Exception e)
		{Outside.err(this, "save()", e);}
	}

	private void save_() throws Exception {
		if (!canModify)
			throw new Exception("canModify=false");
		if (text0 == null)
			throw new Exception("text0==null");

		text0 = area.getText();
		write.p(new Object[]{javaFile, text0});
		
		persistOrClearSrc();
		refreshActions();
		((V) data).v("srcModified", javaFile);
		
		displayCompilingErrors();
		SwingUtilities.invokeLater(() -> area.requestFocusInWindow());
	}

	/*
	 * RELOAD
	 */

	private void reload() {
		try {
			reload_();
		} catch (Exception e) {
			Outside.err(this, "reload()", e);
		}
	}

	private void reload_() throws Exception {
		if (!canModify)
			throw new Exception("canModify=false");
		if (text0 == null)
			throw new Exception("text0==null");

		text0 = readFile();

		int caretPosition = area.getCaretPosition();
		area.getDocument().removeDocumentListener(this);
		area.setText(text0);
		area.getDocument().addDocumentListener(this);
		setCaretPosition(caretPosition);

		persistOrClearSrc();
		refreshActions();
		displayCompilingErrors();
		SwingUtilities.invokeLater(() -> area.requestFocusInWindow());
	}
	
	/*
	 * SELECT
	 */
	
	private void select()
	{
		try
		{
			String selection = area.getSelectedText();
			if(selection==null || selection.equals("")) return;
			((V) data).v("select", selection);
		}
		catch(Exception e)
		{Outside.err(this,"select()",e);}
	}

	/*
	 * CARET POSITION
	 */

	private void setCaretPosition(int caretPosition) {
		int len = area.getText().length();
		if (caretPosition > len)
			caretPosition = len;
		area.setCaretPosition(caretPosition);
	}

	/*
	 * PERMISSIONS
	 */

	private boolean canModifyEntity() throws Exception {
		return permission("canModifyEntity");
	}

	private boolean permission(String permission) throws Exception {
		return data != null && ((F) data).f(new Object[] { permission, entityName });
	}
	
	/*
	 * DISPLAY COMPILING ERRORS
	 */
	
	private void displayCompilingErrors() throws Exception
	{handleErr.p(new Object[] {data, fileName, area});}
	
	/*
	 * SYNCHRONIZED WITH FILE
	 */
	
	private boolean synchWithFile()
	{return area.getText().equals(text0);}
	
	/*
	 * SAVED SRC
	 */
	
	private void persistOrClearSrc() throws Exception
	{
		if(synchWithFile()) clearSrc();
		else persistSrc();
	}
	
	private String restoreOrLoadSrc() throws Exception
	{
		String src = restoreSrc();
		if(src==null) return text0;
		clearSrc();
		return src;
	}
	
	private void persistSrc() throws Exception
	{
		Connection cx = (Connection) ((R) data).r("cx");
		persistSrc.p(new Object[] {cx, entityName, fileName, area.getText()});
		((V) data).v("srcSaved", null);
	}
	
	private void clearSrc() throws Exception
	{
		Connection cx = (Connection) ((R) data).r("cx");
		boolean done = clearSrc.f(new Object[] {cx, entityName, fileName});
		if(done) ((V) data).v("srcCleared", null);
	}
	
	private String restoreSrc() throws Exception
	{
		Connection cx = (Connection) ((R) data).r("cx");
		return (String) restoreSrc.t(new Object[] {cx, entityName, fileName});
	}
	
	/*
	 * REFRESH ACTIONS
	 */
	
	private void refreshActions() {
		boolean synchWithFile = synchWithFile();
		actionSave.setEnabled(!synchWithFile);
		actionReload.setEnabled(!synchWithFile);	
	}
}

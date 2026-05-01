package a.entity.gus.y.knowledgesys1.gui.knowledge.detail1.gui4.tags;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import a.framework.*;

public class EntityImpl implements Entity, I, P, V {
	public String creationDate() {return "20260418";}

	public static final String DISPLAY_ADD    = "TAG_add#Add [F1]";
	public static final String DISPLAY_REMOVE = "TAG_delete#Remove [DEL]";

	private Service buildAction;
	private Service toolbarFactory;
	private Service findTags;
	private Service insertTag;

	private Service engine;
	private Long currentId;

	private Action actionAdd;
	private Action actionRemove;

	private JPanel panel;
	private DefaultListModel model;
	private JList list;

	public EntityImpl() throws Exception {
		buildAction    = Outside.service(this, "gus.y.swing1.action.builder1");
		toolbarFactory = Outside.service(this, "gus.x.swing.toolbar.factory1");
		findTags  = Outside.service(this, "gus.y.knowledgedb1.knowledge_tag.find1");
		insertTag = Outside.service(this, "gus.y.knowledgedb1.knowledge_tag.insert");

		actionAdd    = (Action) buildAction.t(new Object[] { DISPLAY_ADD,    (E) this::actionAdd });
		actionRemove = (Action) buildAction.t(new Object[] { DISPLAY_REMOVE, (E) this::actionRemove });
		actionRemove.setEnabled(false);

		JToolBar bar = (JToolBar) toolbarFactory.i();
		bar.add(actionAdd);
		bar.add(actionRemove);

		model = new DefaultListModel();
		list = new JList(model);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) refreshActions();
			}
		});
		list.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F1) actionAdd();
				if (e.getKeyCode() == KeyEvent.VK_DELETE) actionRemove();
			}
		});

		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(list), BorderLayout.CENTER);
		panel.add(bar, BorderLayout.SOUTH);
	}

	public Object i() throws Exception {
		return panel;
	}

	public void v(String key, Object obj) throws Exception {
		if (key.equals("engine")) engine = (Service) obj;
	}

	public void p(Object obj) throws Exception {
		model.clear();
		currentId = null;
		if (obj == null) return;

		Map m = (Map) obj;
		Object idObj = m.get("id");
		if (idObj == null) return;
		currentId = ((Number) idObj).longValue();

		loadTags();
	}

	private void loadTags() throws Exception {
		model.clear();
		Connection cx = (Connection) engine.r("cx");
		Set tags = (Set) findTags.t(new Object[]{cx, currentId});

		List sorted = new ArrayList(tags);
		Collections.sort(sorted);

		for (int i = 0; i < sorted.size(); i++)
			model.addElement(sorted.get(i));
	}

	private void actionAdd() {
		try {
			if (currentId == null) return;
			String tag = JOptionPane.showInputDialog(panel, "Tag :", "Ajouter un tag", JOptionPane.PLAIN_MESSAGE);
			if (tag == null || tag.trim().isEmpty()) return;
			Connection cx = (Connection) engine.r("cx");
			insertTag.p(new Object[]{cx, currentId, tag.trim()});
			loadTags();
		} catch (Exception e) {
			Outside.err(this, "actionAdd()", e);
		}
	}

	private void actionRemove() {
		try {
			String selected = (String) list.getSelectedValue();
			if (selected == null || currentId == null) return;
			Connection cx = (Connection) engine.r("cx");
			PreparedStatement st = cx.prepareStatement("DELETE FROM knowledge_tag WHERE id_knowledge=? AND tag=?");
			st.setObject(1, currentId);
			st.setObject(2, selected);
			st.executeUpdate();
			st.close();
			loadTags();
		} catch (Exception e) {
			Outside.err(this, "actionRemove()", e);
		}
	}
	
	private void refreshActions()
	{
		try
		{
			actionRemove.setEnabled(list.getSelectedValue() != null);
		}
		catch(Exception e)
		{Outside.err(this,"refreshActions()",e);}
	}

}
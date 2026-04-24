package a.entity.gus.y.entityeditor1.gui3.infos.uplinks;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import a.framework.*;

public class EntityImpl implements Entity, P, I, MouseListener, ActionListener {
	public String creationDate() {return "20240114";}
	
	private Service findLinks;
	private Service renderer;
	private Service custUI;

	private JPanel panel;
	private TreeModel1 model;
	private JTree tree;

	private Object data;
	private String entityName;
	private Connection cx;

	public EntityImpl() throws Exception {
		findLinks = Outside.service(this, "gus.y.entitydb1.entity_link.find1.sorted");
		renderer = Outside.service(this, "gus.y.swing1.tree.cust.renderer.entityname");
		custUI = Outside.service(this, "gus.y.swing1.tree.cust.ui.expandcollapseicons2");

		model = new TreeModel1();
		tree = new JTree(model);
		renderer.p(tree);
		custUI.p(tree);

		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(tree), BorderLayout.CENTER);

		tree.addMouseListener(this);
	}

	public Object i() throws Exception {
		return panel;
	}

	public void p(Object obj) throws Exception {
		if (data != null)
			((S) data).removeActionListener(this);
		if (obj == null) {
			reset();
			return;
		}
		data = obj;
		entityName = (String) ((R) data).r("entityName");
		cx = (Connection) ((R) data).r("cx");

		model = new TreeModel1();
		tree.setModel(model);
		((S) data).addActionListener(this);
	}

	private void reset() throws Exception {
		data = null;
		entityName = null;
		cx = null;
	}

	private class TreeModel1 implements TreeModel {
		public Object getRoot() {
			return entityName;
		}

		public Object getChild(Object parent, int index) {
			return links((String) parent).get(index);
		}

		public int getChildCount(Object parent) {
			return links((String) parent).size();
		}

		public boolean isLeaf(Object node) {
			return links((String) node).isEmpty();
		}

		public int getIndexOfChild(Object parent, Object child) {
			return links((String) parent).indexOf(child);
		}

		public void valueForPathChanged(TreePath path, Object newValue) {
		}

		public void addTreeModelListener(TreeModelListener l) {
		}

		public void removeTreeModelListener(TreeModelListener l) {
		}
	}

	private List links(String name) {
		try {
			return (List) findLinks.t(new Object[] { cx, name });
		} catch (Exception e) {
			Outside.err(this, "links(String)", e);
		}
		return new ArrayList();
	}

	private void selectEntity() {
		try {
			String selectedName = (String) tree.getLastSelectedPathComponent();
			if (selectedName == null)
				return;

			((V) data).v("select", selectedName);
		} catch (Exception e) {
			Outside.err(this, "selectEntity()", e);
		}
	}

	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if (s.equals("srcModified()")) {
			srcModified();
			return;
		}
	}

	private void srcModified() {
		model = new TreeModel1();
		tree.setModel(model);
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getButton()==MouseEvent.BUTTON3)
			selectEntity();
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}

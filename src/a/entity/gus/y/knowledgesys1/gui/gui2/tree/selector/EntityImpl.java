package a.entity.gus.y.knowledgesys1.gui.gui2.tree.selector;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, G, I, V, ActionListener, TreeSelectionListener {
	public String creationDate() {return "20260414";}

	private Service findLinks;
	private Service findKnowledge;
	private Service custUI;

	private Object engine;
	private Icon icon;
	private JPanel panel;
	private JTree tree;

	public EntityImpl() throws Exception {
		findLinks = Outside.service(this, "gus.y.knowledgedb1.knowledge_link.find1");
		findKnowledge = Outside.service(this, "gus.y.knowledgedb1.knowledge.find");
		custUI = Outside.service(this, "gus.y.swing1.tree.cust.ui.expandcollapseicons2");

		icon = (Icon) Outside.resource(this, "icon#COMMENT_blue");

		tree = new JTree(new TreeModel1());
		tree.setRootVisible(false);
		tree.setCellRenderer(new TreeCellRenderer0());
		custUI.p(tree);

		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(tree), BorderLayout.CENTER);

		tree.addTreeSelectionListener(this);

		tree.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("F5"), "reload");
		tree.getActionMap().put("reload", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {reload();}
		});
	}

	public void v(String key, Object obj) throws Exception
	{
		if (key.equals("engine")) initEngine(obj);
	}

	private void initEngine(Object engine) throws Exception
	{
		if(this.engine!=null)
		((S) engine).removeActionListener(this);
		
		this.engine = engine;
		((S) engine).addActionListener(this);
	}

	private void reload() {
		try {((E) engine).e();}
		catch (Exception e) {Outside.err(this, "reload()", e);}
	}

	public Object g() throws Exception {
		Object selected = tree.getLastSelectedPathComponent();
		if (selected instanceof Map) return selected;
		return null;
	}

	public Object i() throws Exception {
		return panel;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("loaded()"))
			loaded();
	}

	private void loaded() {
		tree.setModel(new TreeModel1());
	}

	public void valueChanged(TreeSelectionEvent e) {
		selected();
	}

	private void selected() {
		send(this, "selected()");
	}

	private List roots() {
		try {
			if(engine==null) return new ArrayList();
			List r = (List) ((G) engine).g();
			return r != null ? r : new ArrayList();
		} catch (Exception e) {
			Outside.err(this, "roots()", e);
			return new ArrayList();
		}
	}

	private List children(Map node) {
		try {
			if(engine==null) return new ArrayList();
			Object cx = ((R) engine).r("cx");
			Long id = toLong(node.get("id"));
			List links = (List) findLinks.t(new Object[] {cx, id});
			List result = new ArrayList();
			for (int i = 0; i < links.size(); i++) {
				Long idLinked = toLong(((Map) links.get(i)).get("id_linked"));
				Map k = (Map) findKnowledge.t(new Object[] {cx, idLinked});
				if (k != null) result.add(k);
			}
			return result;
		} catch (Exception e) {
			Outside.err(this, "children(Map)", e);
			return new ArrayList();
		}
	}

	private Long toLong(Object obj) {
		if (obj instanceof Long) return (Long) obj;
		return ((Number) obj).longValue();
	}

	private class TreeModel1 implements TreeModel {
		private static final Object ROOT = new Object();

		public Object getRoot() {
			return ROOT;
		}

		public Object getChild(Object parent, int index) {
			if (parent == ROOT) return roots().get(index);
			return children((Map) parent).get(index);
		}

		public int getChildCount(Object parent) {
			if (parent == ROOT) return roots().size();
			return children((Map) parent).size();
		}

		public boolean isLeaf(Object node) {
			if (node == ROOT) return false;
			return children((Map) node).isEmpty();
		}

		public int getIndexOfChild(Object parent, Object child) {
			if (parent == ROOT) return roots().indexOf(child);
			return children((Map) parent).indexOf(child);
		}

		public void valueForPathChanged(TreePath path, Object newValue) {}

		public void addTreeModelListener(TreeModelListener l) {}

		public void removeTreeModelListener(TreeModelListener l) {}
	}

	private class TreeCellRenderer0 extends JLabel implements TreeCellRenderer {

		public TreeCellRenderer0() {
			super();
			setOpaque(true);
		}

		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean isSelected,
				boolean expanded, boolean leaf, int row, boolean hasFocus) {
			if (value instanceof Map) {
				Map m = (Map) value;
				setText(m.get("code") + ":" + m.get("action") + ":" + m.get("object"));
				setIcon(icon);
			} else {
				setText("");
				setIcon(null);
			}
			setBackground(isSelected ? new Color(244, 244, 244) : Color.WHITE);
			return this;
		}
	}
}


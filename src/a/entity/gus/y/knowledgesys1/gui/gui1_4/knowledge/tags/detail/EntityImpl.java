package a.entity.gus.y.knowledgesys1.gui.gui1_4.knowledge.tags.detail;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import a.framework.*;

public class EntityImpl implements Entity, I, P, V {
	public String creationDate() {return "20260429";}

	private Service findallByTag;

	private Object engine;
	private JPanel panel;
	private JList list;
	private DefaultListModel model;

	public EntityImpl() throws Exception
	{
		findallByTag = Outside.service(this, "gus.y.knowledgedb1.knowledge.findall.bytag");

		model = new DefaultListModel();
		list = new JList(model);

		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(list), BorderLayout.CENTER);
	}

	public Object i() throws Exception
	{
		return panel;
	}

	public void v(String key, Object obj) throws Exception
	{
		if (key.equals("engine")) engine = obj;
	}

	public void p(Object obj) throws Exception
	{
		model.clear();
		if (obj == null || engine == null) return;
		String tag = (String) ((Map) obj).get("tag");
		Connection cx = (Connection) ((R) engine).r("cx");
		List knowledges = (List) findallByTag.t(new Object[]{cx, tag});
		for (int i = 0; i < knowledges.size(); i++)
		{
			Map m = (Map) knowledges.get(i);
			model.addElement(m.get("display"));
		}
	}
}
package a.entity.gus.y.knowledgesys1.gui.knowledge.detail1.gui4.tags;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import a.framework.*;

public class EntityImpl implements Entity, I, P {
	public String creationDate() {return "20260418";}

	private Service cxMain;
	private Service findTags;

	private JPanel panel;
	private DefaultListModel model;
	private JList list;

	public EntityImpl() throws Exception {
		cxMain = Outside.service(this, "gus.y.knowledgedb1.cx.main");
		findTags = Outside.service(this, "gus.y.knowledgedb1.knowledge_tag.find1");

		model = new DefaultListModel();
		list = new JList(model);

		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(list), BorderLayout.CENTER);
	}

	public Object i() throws Exception {
		return panel;
	}

	public void p(Object obj) throws Exception {
		model.clear();
		if (obj == null) return;

		Map m = (Map) obj;
		Object idObj = m.get("id");
		if (idObj == null) return;
		Long id = ((Number) idObj).longValue();

		Connection cx = (Connection) cxMain.g();
		Set tags = (Set) findTags.t(new Object[]{cx, id});

		List sorted = new ArrayList(tags);
		Collections.sort(sorted);

		for (int i = 0; i < sorted.size(); i++)
			model.addElement(sorted.get(i));
	}
}

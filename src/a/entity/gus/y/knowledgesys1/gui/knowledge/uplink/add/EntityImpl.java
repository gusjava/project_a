package a.entity.gus.y.knowledgesys1.gui.knowledge.uplink.add;

import a.framework.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260501";}

	private Service engine;
	private Service dialogOkCancel;
	private Service insertLink;

	private JTextField fieldLinker = new JTextField();
	private JTextField fieldType   = new JTextField();
	private JPanel contentPanel;

	public EntityImpl() throws Exception {
		engine        = Outside.service(this, "gus.y.knowledgesys1.engine");
		dialogOkCancel = Outside.service(this, "gus06.swing.dialog.blocked1.okcancel");
		insertLink    = Outside.service(this, "gus.y.knowledgedb1.knowledge_link.insert");

		JPanel fields = new JPanel(new GridLayout(2, 2, 4, 4));
		fields.add(new JLabel("Linker code:"));
		fields.add(fieldLinker);
		fields.add(new JLabel("Type:"));
		fields.add(fieldType);

		contentPanel = new JPanel(new BorderLayout());
		contentPanel.add(fields, BorderLayout.NORTH);
	}

	public void p(Object obj) throws Exception {
		String linkedCode = (String) obj;

		fieldLinker.setText("");
		fieldType.setText("");

		dialogOkCancel.v("width", "400");
		dialogOkCancel.v("height", "180");
		boolean ok = dialogOkCancel.f(contentPanel);
		if (!ok) return;

		String linkerCode = fieldLinker.getText().trim();
		String type       = fieldType.getText().trim();
		if (linkerCode.isEmpty()) return;

		List knowledgeList = (List) engine.r("knowledgeList");
		Long idLinker = findId(knowledgeList, linkerCode);
		Long idLinked = findId(knowledgeList, linkedCode);

		if (idLinker == null) throw new Exception("Unknown linker code: " + linkerCode);
		if (idLinked == null) throw new Exception("Unknown linked code: "  + linkedCode);

		Connection cx = (Connection) engine.r("cx");
		insertLink.p(new Object[]{cx, idLinker, idLinked, type});
		engine.e();
	}

	private Long findId(List list, String code) {
		for (int i = 0; i < list.size(); i++) {
			Map m = (Map) list.get(i);
			if (code.equals(m.get("code"))) return (Long) m.get("id");
		}
		return null;
	}
}
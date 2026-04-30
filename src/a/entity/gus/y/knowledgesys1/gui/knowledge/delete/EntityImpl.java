package a.entity.gus.y.knowledgesys1.gui.knowledge.delete;

import a.framework.*;
import java.util.Map;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20260430";}

	private Service delete;

	public EntityImpl() throws Exception
	{
		delete = Outside.service(this, "gus.y.knowledgedb1.knowledge.delete");
	}

	public void p(Object obj) throws Exception
	{f(obj);}

	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Service engine = (Service) o[0];
		Map data = (Map) o[1];

		String code = valueFor(data, "code");
		String object = valueFor(data, "object");
		Long id = ((Number) data.get("id")).longValue();

		String msg = "<html>Supprimer la knowledge <b>" + code + "</b> — " + object + " ?</html>";
		int choice = JOptionPane.showConfirmDialog(null, msg, "Confirmation", JOptionPane.YES_NO_OPTION);
		if (choice != JOptionPane.YES_OPTION) return false;

		delete.p(new Object[]{engine.r("cx"), id});
		engine.e();
		return true;
	}

	private String valueFor(Map data, String key)
	{
		Object value = data.get(key);
		return value != null ? value.toString() : "";
	}
}
package a.entity.gus06.appli.gusdbmanager.gui.gui1.editor.form;

import java.util.Map;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class EntityImpl implements Entity, P, I {

	public String creationDate() {return "20150613";}


	private Service form;

	private JLabel label_url;
	private JLabel label_login;
	private JLabel label_where;
	
	private Map map;
	

	public EntityImpl() throws Exception
	{
		form = Outside.service(this,"*gus06.swing.panel.formpanel");
		
		label_url = new JLabel(" ");
		label_login = new JLabel(" ");
		label_where = new JLabel(" ");
		
		form.v(CONNECTOR.KEY_URL,label_url);
		form.v(CONNECTOR.KEY_LOGIN,label_login);
		form.v(CONNECTOR.KEY_WHERE,label_where);
	}


	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		
		label_url.setText(get(CONNECTOR.KEY_URL));
		label_login.setText(get(CONNECTOR.KEY_LOGIN));
		label_where.setText(get(CONNECTOR.KEY_WHERE));
	}



	public Object i() throws Exception
	{return form.i();}


	
	
	
	private String get(String key)
	{
		if(map==null) return " ";
		if(!map.containsKey(key)) return " ";
		return (String) map.get(key);
	}
}

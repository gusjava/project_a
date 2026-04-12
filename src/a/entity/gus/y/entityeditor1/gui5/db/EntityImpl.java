package a.entity.gus.y.entityeditor1.gui5.db;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.io.File;
import java.sql.Connection;
import java.awt.Insets;

import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import a.framework.*;

public class EntityImpl implements Entity, P, I, ActionListener {
	public String creationDate() {return "20240708";}

	private Service find1;
	private Service find2;
	private Service find3;
	private Service extract;
	private Service read;
	
	private JPanel panel;
	private JTextArea area;
	private JScrollPane scroll;
	private JButton button;

	private Object data;

	public EntityImpl() throws Exception {
		find1 = Outside.service(this, "gus.y.entitydb1.entity_resource.find");
		find2 = Outside.service(this, "gus.y.entitydb1.entity_service.find");
		find3 = Outside.service(this, "gus.y.entitydb1.entity_missing_link.find");
		extract = Outside.service(this, "gus.y.entitysys1.analyze.entity.extract");
		read = Outside.service(this,"gus.x.file.string.read.n");
		
		area = new JTextArea();
		area.setEditable(false);
		area.setMargin(new Insets(3,3,3,3));

		scroll = new JScrollPane(area);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setOpaque(true);

		button = new JButton("Refresh");
		button.addActionListener(this);

		panel = new JPanel(new BorderLayout());
		panel.add(scroll, BorderLayout.CENTER);
		panel.add(button, BorderLayout.SOUTH);
	}

	public Object i() throws Exception {
		return panel;
	}

	public void p(Object obj) throws Exception {
		data = obj;
		area.setText("");
	}

	public void actionPerformed(ActionEvent e) {
		refresh();
	}

	private void refresh() {
		try {
			area.setText(buildText());
		} catch (Exception e) {
			Outside.err(this, "refresh()", e);
		}
	}
	
	private String buildText() throws Exception {
		if(data==null) return "Not initialized yet";
		
		File mainFile = (File) ((R) data).r("mainJavaFile");
		String entityName = (String) ((R) data).r("entityName");
		Connection cx = (Connection) ((R) data).r("cx");
		String src = readFile(mainFile);
		Map extractedData = (Map) extract.t(src);
			
		StringBuffer b = new StringBuffer();
		
		b.append("DB RESOURCES:\n");
		Set data1 = (Set) find1.t(new Object[] {cx, entityName});
		handleList(b, new ArrayList(data1));
		
		b.append("\n\nDB SERVICES:\n");
		Set data2 = (Set) find2.t(new Object[] {cx, entityName});
		handleList(b, new ArrayList(data2));
		
		b.append("\n\nDB MISSING LINKS:\n");
		List data3 = (List) find3.t(new Object[] {cx, entityName});
		handleList(b, data3);
		
		b.append("\n\nMAIN FILE:\n");
		b.append(mainFile.getAbsolutePath()+"\n");
		b.append("src length: "+src.length());
		

		Set resources = (Set) extractedData.get("resources");
		Set services = (Set) extractedData.get("services");
		
		b.append("\n\nEXTR RESOURCES:\n");
		handleList(b, new ArrayList(resources));
		
		b.append("\n\nEXTR SERVICES:\n");
		handleList(b, new ArrayList(services));
		
		return b.toString();
	}
	
	private void handleList(StringBuffer b, List data) throws Exception {
		if(data==null) {
			b.append("null");
			return;
		}
		b.append("result number:"+data.size()+"\n");
		for(int i=0;i<data.size();i++) {
			Object element = data.get(i);
			if(element instanceof Map) {
				handleRowMap(b, (Map) element);
			}
			else if(element instanceof String) {
				b.append((String) element);
			}
			if (i<data.size()-1) b.append("\n");
		}
	}
	
	private void handleRowMap(StringBuffer b, Map data) throws Exception {
		List keys = new ArrayList(data.keySet());
		Collections.sort(keys);
	
		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			String value = "" + data.get(key);
			
			b.append(key + "=" + value);
			if (i<keys.size()-1) b.append("\t");
		}
	}

	private String readFile(File file) throws Exception {
		return (String) read.t(file);
	}
}

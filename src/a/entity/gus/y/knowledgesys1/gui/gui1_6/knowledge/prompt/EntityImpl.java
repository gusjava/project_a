package a.entity.gus.y.knowledgesys1.gui.gui1_6.knowledge.prompt;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import a.framework.*;

public class EntityImpl implements Entity, I, V
{
	public String creationDate() {return "20260504";}

	private Service prompt;

	private JPanel panel;
	private JTextArea inputArea;
	private JTextArea outputArea;

	public EntityImpl() throws Exception
	{
		prompt = Outside.service(this, "gus.y.knowledgesys1.prompt");

		inputArea = new JTextArea();
		inputArea.setToolTipText("Ligne 1 : mots-clés séparés par espaces — lignes suivantes : description");

		outputArea = new JTextArea();
		outputArea.setEditable(false);

		JButton button = new JButton("Generate prompt");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {generate();}
		});

		JPanel panelCenter = new JPanel(new GridLayout(1,2));
		panelCenter.add(new JScrollPane(inputArea));
		panelCenter.add(new JScrollPane(outputArea));

		panel = new JPanel(new BorderLayout());
		panel.add(panelCenter, BorderLayout.CENTER);
		panel.add(button, BorderLayout.SOUTH);
	}

	public Object i() throws Exception
	{return panel;}

	public void v(String key, Object obj) throws Exception {}

	private void generate()
	{
		try
		{
			Map input = parseInput(inputArea.getText().trim());
			List result = (List) prompt.t(input);
			outputArea.setText(formatResult(result));
		}
		catch (Exception e) {Outside.err(this, "generate()", e);}
	}

	private Map parseInput(String text) throws Exception
	{
		String[] lines = text.split("\n", 2);
		String keywordsLine = lines[0].trim();
		String description = lines.length > 1 ? lines[1].trim() : "";
		List keywords = new ArrayList();
		String[] parts = keywordsLine.split("\\s+");
		for (int i = 0; i < parts.length; i++)
		{
			String kw = parts[i].trim();
			if (!kw.isEmpty()) keywords.add(kw);
		}
		Map map = new LinkedHashMap();
		map.put("keywords", keywords);
		map.put("description", description);
		return map;
	}

	private String formatResult(List result) throws Exception
	{
		if (result == null || result.isEmpty()) return "(aucun résultat)";
		StringBuffer b = new StringBuffer();
		for (int i = 0; i < result.size(); i++)
		{
			Map m = (Map) result.get(i);
			b.append("[" + (i + 1) + "] " + m.get("code") + " — " + m.get("action"));
			String obj = (String) m.get("object");
			if (obj != null && !obj.isEmpty()) b.append(" / " + obj);
			b.append("\n");
			String desc = (String) m.get("description");
			if (desc != null && !desc.isEmpty()) b.append("    " + desc + "\n");
			b.append("\n");
		}
		if (b.length() > 0 && b.charAt(b.length() - 1) == '\n') b.deleteCharAt(b.length() - 1);
		return b.toString();
	}
}
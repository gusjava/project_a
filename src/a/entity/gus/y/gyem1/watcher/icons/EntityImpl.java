package a.entity.gus.y.gyem1.watcher.icons;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.S;
import a.framework.Service;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20231129";}

	private Service watcher;

	private JPanel panel;
	private JLabel labelErr;
	private JLabel labelEntityLoaded;
	private JLabel labelEntityUnique;

	private List listErr;
	private Map mapEntityLoaded;
	private Map mapEntityUnique;

	private Icon iconErr;
	private Icon iconEntityLoaded;
	private Icon iconEntityUnique;

	public EntityImpl() throws Exception {
		watcher = Outside.service(this, "gus.y.gyem1.watcher");

		listErr = (List) Outside.resource(this, "errlist");
		mapEntityLoaded = (Map) Outside.resource(this, "entityclassmap");
		mapEntityUnique = (Map) Outside.resource(this, "uniqueentitymap");

		iconErr = (Icon) Outside.resource(this, "icon#UTIL_error");
		iconEntityLoaded = (Icon) Outside.resource(this, "icon#ELEMENT_entity_entityloaded");
		iconEntityUnique = (Icon) Outside.resource(this, "icon#ELEMENT_entity_unique");

		labelErr = new JLabel(" ");
		labelEntityLoaded = new JLabel(" ");
		labelEntityUnique = new JLabel(" ");

		panel = new JPanel(new GridLayout(1, 4));
		panel.add(labelErr);
		panel.add(labelEntityLoaded);
		panel.add(labelEntityUnique);

		S watcherErr = (S) watcher.r("supportErr");
		watcherErr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelErr.setIcon(iconErr);
				labelErr.setText(listErr.size() + " ");
			}
		});

		S watcherEntityClass = (S) watcher.r("supportEntityLoaded");
		watcherEntityClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelEntityLoaded.setIcon(iconEntityLoaded);
				labelEntityLoaded.setText(mapEntityLoaded.size() + " ");
			}
		});

		S watcherEntityUnique = (S) watcher.r("supportEntityUnique");
		watcherEntityUnique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelEntityUnique.setIcon(iconEntityUnique);
				labelEntityUnique.setText(mapEntityUnique.size() + " ");
			}
		});
	}

	public Object i() throws Exception {
		return panel;
	}
}

package a.entity.gus.y.appli1.fr.execute.about.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20240110";}
	
	public static final String PROP_APP_TITLE = "app.title";
	public static final String PROP_APP_VERSION = "app.version";
	public static final String PROP_APP_AUTHOR = "app.author";
	public static final String PROP_APP_NAME = "app.name";
	public static final String PROP_APP_STARTDATE = "app.startdate";

	public static final Border EMPTY8888 = BorderFactory.createEmptyBorder(8, 8, 8, 8);
	public static final Color BACKGROUND = Color.WHITE;
	public static final Color FOREGROUND = new Color(0, 102, 204);

	public static final int SIZE1 = 22;
	public static final int SIZE2 = 18;
	public static final int SIZE3 = 14;
	public static final int SIZE4 = 10;

	public static final String MESSAGE_CREATEDBY = "Application créée par ";
	public static final String MESSAGE_INCOMPLETE = "Version non finalisée!";
	public static final String MESSAGE_UNDEFINED = "indéfini";
	
	private Service findColor;

	private Map props;
	private Icon iconApp;
	private String coreId;

	private JPanel panel;

	public EntityImpl() throws Exception {
		findColor = Outside.service(this,"gus.y.convert1.stringtocolor");
		iconApp = (Icon) Outside.resource(this, "icon#app");
		coreId = (String) Outside.resource(this, "core.id");
		props = (Map) Outside.resource(this, "props");

		panel = new AboutPanel();
	}

	public Object i() throws Exception {
		return panel;
	}

	private String prop(String key, String defaultValue) {
		if (props.containsKey(key))
			return (String) props.get(key);
		return defaultValue;
	}

	private String prop(String key) {
		return prop(key, "?");
	}

	

	private boolean isNotFinalized() {
		return prop(PROP_APP_VERSION).endsWith("*");
	}

	private Font font(int size) {
		return new Font("Calibri", Font.PLAIN, size);
	}

	private Font fontBold(int size) {
		return new Font("Calibri", Font.BOLD, size);
	}

	private JLabel label(int size, Color color, JComponent comp) {
		if (comp == null)
			return null;
		JLabel label = (JLabel) comp;
		label.setFont(font(size));
		label.setForeground(color);
		return label;
	}

	private JLabel label(int size, Color color, String text) {
		JLabel label = new JLabel(text);
		label.setFont(font(size));
		label.setForeground(color);
		return label;
	}

	private JLabel labelBold(int size, Color color, String text) {
		JLabel label = new JLabel(text);
		label.setFont(fontBold(size));
		label.setForeground(color);
		return label;
	}

	private JLabel labelEmpty() {
		JLabel label = new JLabel(" ");
		label.setFont(font(9));
		return label;
	}

	private Color color(String key, Color defaultColor) throws Exception {
		String value = prop(key, null);
		if (value != null)
			return (Color) findColor.t(value);
		return defaultColor;
	}

	private Color background() throws Exception {
		return color("color.background.about", BACKGROUND);
	}

	private Color foreground() throws Exception {
		return color("color.foreground.about", FOREGROUND);
	}

	private class AboutPanel extends JPanel {
		public AboutPanel() throws Exception {
			super(new GridBagLayout());

			setOpaque(true);
			setBorder(EMPTY8888);
			setBackground(background());

			Color fg = foreground();

			put();
			put(labelBold(SIZE1, fg, prop(PROP_APP_TITLE)));
			put(labelBold(SIZE2, fg, "Version " + prop(PROP_APP_VERSION)));
			if (isNotFinalized())
				put(labelBold(SIZE3, Color.RED, MESSAGE_INCOMPLETE));
			put();
			put(new JLabel(iconApp));
			put();
			put(label(SIZE3, fg, MESSAGE_CREATEDBY + prop(PROP_APP_AUTHOR)));
			put();
			put(label(SIZE4, Color.BLACK, "Application name: " + prop(PROP_APP_NAME)));
			put(label(SIZE4, Color.BLACK, "Start date: " + prop(PROP_APP_STARTDATE)));
			put(label(SIZE4, Color.BLACK, "Framework name: a"));
			put(label(SIZE4, Color.BLACK, "Core ID: " + coreId));
			put();
			put(labelEmpty());
		}

		int n = 0;

		private void put() {
			put(new JLabel(" "));
		}

		private void put(JComponent c) {
			if (c == null)
				return;
			add(c, nextConstraints());
		}

		private GridBagConstraints nextConstraints() {
			return createConstraints(1, n++);
		}

		private GridBagConstraints createConstraints(int x, int y) {
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = x;
			c.gridy = y;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.anchor = GridBagConstraints.CENTER;
			c.weightx = 0.0;
			c.weighty = 0.0;
			return c;
		}
	}
}
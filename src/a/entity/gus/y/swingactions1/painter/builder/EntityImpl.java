package a.entity.gus.y.swingactions1.painter.builder;

import a.framework.*;
import javax.swing.text.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Composite;
import java.awt.AlphaComposite;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240120";}
	
	public static final int EDGE = 8;

	public static final Composite ALPHA1 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);

	public Object t(Object obj) throws Exception {
		if (obj == null)
			return null;
		return new HPainter((Color) obj);
	}

	private class HPainter extends DefaultHighlighter.DefaultHighlightPainter implements V {
		private Composite alpha;

		public HPainter(Color c) {
			super(c);
		}

		public Shape paintLayer(Graphics g, int offs0, int offs1, Shape bounds, JTextComponent c, View view) {
			Graphics2D g2 = (Graphics2D) g;

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
			if (alpha != null)
				g2.setComposite(alpha);

			Color color = getColor();
			if (color != null)
				g.setColor(color);
			else
				g.setColor(c.getSelectionColor());

			Rectangle r;

			if (offs0 == view.getStartOffset() && offs1 == view.getEndOffset()) {
				r = (bounds instanceof Rectangle) ? (Rectangle) bounds : bounds.getBounds();
			} else {
				try {
					Shape shape = view.modelToView(offs0, Position.Bias.Forward, offs1, Position.Bias.Backward, bounds);
					r = (shape instanceof Rectangle) ? (Rectangle) shape : shape.getBounds();
				} catch (BadLocationException e) {
					r = null;
				}
			}

			if (r != null) {
				r.width = Math.max(r.width, 1);
				g.fillRoundRect(r.x, r.y, r.width, r.height, EDGE, EDGE);
			}

			g2.setComposite(ALPHA1);
			return r;
		}

		public void v(String key, Object obj) throws Exception {
			if (key.equals("alpha")) {
				alpha = (Composite) obj;
				return;
			}
			throw new Exception("Unknown key: " + key);
		}
	}
}

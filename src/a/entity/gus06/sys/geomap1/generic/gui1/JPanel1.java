package a.entity.gus06.sys.geomap1.generic.gui1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.net.URL;
import java.net.URI;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import javax.swing.Icon;
import javax.swing.JPanel;
import java.io.InputStream;
import java.io.IOException;
import java.awt.Font;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.DocumentLoader;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgent;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import a.framework.*;

public class JPanel1 extends JPanel implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	
	public static final Color DEFAULT_COLOR_OTHER = new Color(70, 130, 180);
	public static final Color DEFAULT_COLOR_SELECTION = new Color(0, 0, 0, 150);
	public static final Color DEFAULT_COLOR_AREA = Color.WHITE;
	
	public static final Color DEFAULT_COLOR_BG_TOOLTIP = new Color(255, 255, 200, 230);
	public static final Color DEFAULT_COLOR_FG_TOOLTIP = Color.BLACK;
	public static final Color DEFAULT_COLOR_BORDER_TOOLTIP = Color.BLACK;
	public static final Font DEFAULT_FONT_TOOLTIP = new Font(Font.DIALOG,Font.PLAIN, 16);
	
	public static final double DEFAULT_SCALE_FACTOR = 1;
	public static final int DEFAULT_W0 = 600;
	public static final int DEFAULT_H0 = 600;
	public static final int DEFAULT_X0 = 0;
	public static final int DEFAULT_Y0 = 0;
	
	public static final String SELECTIONMODE_HOVER = "HOVER";
	public static final String SELECTIONMODE_CLICK = "CLICK";
	
	
	private EntityImpl entity;
	
	private Document doc;
	private BridgeContext ctx;
	private GraphicsNode rootNode;
	private Map<GraphicsNode, Element> m;
	
	private Rectangle bounds;
	private Rectangle area;
	private Point mouseLocation;
	
	private String selectionMode = SELECTIONMODE_HOVER;
	private double scaleFactor = DEFAULT_SCALE_FACTOR;
	private int w0 = DEFAULT_W0;
	private int h0 = DEFAULT_H0;
	private int x0 = DEFAULT_X0;
	private int y0 = DEFAULT_Y0;
	
	private Color selectionColor = DEFAULT_COLOR_SELECTION;
	private Color otherColor = DEFAULT_COLOR_OTHER;
	private Color areaColor = DEFAULT_COLOR_AREA;
	private Color hoverColor = null;
	
	private Color tooltipBgColor = DEFAULT_COLOR_BG_TOOLTIP;
	private Color tooltipFgColor = DEFAULT_COLOR_FG_TOOLTIP;
	private Color tooltipBorderColor = DEFAULT_COLOR_BORDER_TOOLTIP;
	private Font tooltipFont = DEFAULT_FONT_TOOLTIP;
	
	private String selectedKey;
	private String hoverKey;
	
	private T keyT;
	private T tooltipTextT;
	private T tooltipIconT;
	private T areaColorT;
	
	private F idPaintF;
	private F keySelectF;
	
	private boolean showTooltip = true;
	private boolean showMouseLocation = false;
	
	
	public JPanel1(EntityImpl entity) {
		super();
		this.entity = entity;
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	/*
	* LOAD
	*/
	
	public void load(URL url) throws Exception {
		URI uri = url.toURI();
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
        doc = factory.createDocument(uri.toString());
        readDocument();
	}
	
	public void load(URI uri) throws Exception {
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
        doc = factory.createDocument(uri.toString());
        readDocument();
	}
	
	public void load(InputStream is) throws Exception {
		try {
			String parser = XMLResourceDescriptor.getXMLParserClassName();
			SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
			doc = factory.createDocument(null, is);
			readDocument();
			is.close();
		}
		finally {
			try{is.close();}catch(IOException e){}
		}
	}
	
	/*
	* READ DOCUMENT
	*/
	
	private void readDocument() throws Exception {
		UserAgent userAgent = new UserAgentAdapter();
        DocumentLoader loader = new DocumentLoader(userAgent);
        ctx = new BridgeContext(userAgent, loader);
        ctx.setDynamicState(BridgeContext.DYNAMIC);
        
        GVTBuilder builder = new GVTBuilder();
        rootNode = builder.build(ctx, doc);
        
        m = new HashMap<>();
        Element root = doc.getDocumentElement();
        walk(root);
        
        bounds = rootNode.getOutline().getBounds();
        area = new Rectangle(bounds.width, bounds.height);
        
        if(m.isEmpty()) throw new Exception("No area detected");
	}

    private void walk(Node node) {
    	NodeList children = node.getChildNodes();
    	int nb = children.getLength();
        for (int i = 0; i < nb; i++) {
            walk(children.item(i));
        }
        if (node.getNodeType()==Node.ELEMENT_NODE && nb==0) {
            Element element = (Element) node;
            if(isKeyElement(element)) {
	            GraphicsNode gn = ctx.getGraphicsNode(element);
	            if (gn != null) m.put(gn, element);
            }
        }
    }
	
	/*
	* PAINT COMPONENT
	*/
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
//        g2d.setRenderingHint(RenderingHintsKeyExt.KEY_TRANSCODING, RenderingHintsKeyExt.VALUE_TRANSCODING_PRINTING);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        g2d.scale(scaleFactor, scaleFactor);
		g2d.translate(dx(), dy());
        g2d.setClip(area);

        g2d.setColor(otherColor);
        g2d.fill(area);
		
        for(GraphicsNode node : m.keySet()) {
        	Element value = m.get(node);
			
            String id = findId(value);
			if(idPaintF!=null && !apply(idPaintF, id, true)) continue;
			
            String key = buildKey(id);
			boolean isSelected = key!=null && Objects.equals(key, selectedKey);
			boolean isHover = key!=null && Objects.equals(key, hoverKey);
			
			Color fillColor = buildAreaColor(key);
			
            g2d.setColor(fillColor);
            g2d.fill(node.getOutline());
            
            g2d.setColor(Color.BLACK);
            g2d.draw(node.getOutline());
        	
            if (isSelected && selectionColor!=null) {
                g2d.setColor(selectionColor);
                g2d.fill(node.getOutline());
                
                g2d.setStroke(new java.awt.BasicStroke(2));
                g2d.draw(node.getOutline());
                g2d.setStroke(new java.awt.BasicStroke(1));
            }
			else if (isHover && hoverColor!=null) {
				g2d.setColor(hoverColor);
				g2d.fill(node.getOutline());
			}
        }
    	
        if (hoverKey!=null && showTooltip) {
			String tooltipText = buildTooltipText(hoverKey);
			Icon icon = buildTooltipIcon(hoverKey);
			
			int iconWidth = icon!=null ? icon.getIconWidth() : 0;
			int iconHeight = icon!=null ? icon.getIconHeight() : 0;
			int iconOffset = icon!=null ? iconWidth + 3 : 0;
			
            g2d.setFont(tooltipFont);
            int textWidth = g2d.getFontMetrics().stringWidth(tooltipText);
            int textHeight = g2d.getFontMetrics().getHeight();
			
            int padding = 6;
            
            int x = mouseLocation.x + 5;
            int y = mouseLocation.y - 10 - textHeight;
            
            int w = textWidth + 2 * padding + iconOffset;
            int h = textHeight + 2 * padding;
            int r = 15;
			
			int xMax = area.width + area.x - w;
            int yMax = area.height + area.y - h;
			
            if(x>xMax) x = xMax;
            if(y>yMax) y = yMax;
            
            if(x<0) x = 0;
            if(y<0) y = 0;
            
            int xt = x + padding + iconOffset;
            int yt = y + padding + textHeight - 3;
            
            g2d.setColor(tooltipBgColor); 
            g2d.fillRoundRect(x, y, w, h, r, r);
            
            g2d.setColor(tooltipBorderColor);
            g2d.drawRoundRect(x, y, w, h, r, r);
			
			if(icon!=null) {
				int xi = x + padding;
				int yi = y + padding + textHeight - iconHeight + 2;
				icon.paintIcon(this, g2d, xi, yi);
			}
			
            g2d.setColor(tooltipFgColor);
            g2d.drawString(tooltipText, xt, yt);
        }

		if(showMouseLocation && mouseLocation!=null) {
				g2d.setColor(Color.RED);
				g2d.fillRect(mouseLocation.x-4, mouseLocation.y-4, 8, 8);
		}
    }
    
    private int dx() {
    	return (int) ((x0 - w0*scaleFactor) * 0.5);
    }
    
    private int dy() {
    	return (int) ((y0 - h0*scaleFactor) * 0.5);
    }
    
    private Element elementAtPoint(Point2D p) {
        for (GraphicsNode gn : m.keySet()) {
            if (gn.contains(p))  return m.get(gn);
        }
        return null;
    }
    
    
    
    private String findId(Element element) {
    	return element!=null ? element.getAttribute("id") : null;
    }


	@Override
	public void mousePressed(MouseEvent e) {
		if(selectionMode.equals(SELECTIONMODE_CLICK)) {
			selectHover();
			repaint();
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseLocation = e.getPoint();
		mouseLocation.setLocation(mouseLocation.getX()/scaleFactor, mouseLocation.getY()/scaleFactor);
		mouseLocation.translate(-dx(), -dy());
		
		Element element = elementAtPoint(mouseLocation);
        String id = findId(element);
        String key = buildKey(id);
        
        if(!Objects.equals(key, hoverKey)) {
        	hoverKey = key;
			hoverChanged();
			
			if(selectionMode.equals(SELECTIONMODE_HOVER)) 
				selectHover();
        }
    	repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseDragged(MouseEvent e) {}
	
	
	
	private void selectHover() {
		select(hoverKey);
	}
    
    private boolean isKeyElement(Element element) {
    	String id = findId(element);
    	String key = buildKey(id);
    	return key!=null;
    }
	
	/*
	* BUILD
	*/
    
    private String buildKey(String id) {
    	return keyT!=null ? (String) apply(keyT, id) : id;
    }
	
	private String buildTooltipText(String key) {
		if(key==null) return "";
		if(tooltipTextT==null) return key;
		String value = (String) apply(tooltipTextT, key);
		return value!=null ? value : "";
	}
	
	private Icon buildTooltipIcon(String key) {
		if(key==null) return null;
		if(tooltipIconT==null) return null;
		return (Icon) apply(tooltipIconT, key);
	}
	
	private Color buildAreaColor(String key) {
		if(key==null) return null;
		if(areaColorT==null) return areaColor;
		return (Color) apply(areaColorT, key);
	}
	
	/*
	* SELECT
	*/
	
	public void select(String key){
		if(keySelectF!=null && !apply(keySelectF, key, true)) return;
		selectedKey = key;
		selectionChanged();
	}	
	
	/*
	* SELECTION MODE
	*/
	
	public void setSelectionMode(String selectionMode) {
		this.selectionMode = selectionMode;
	}
	
	public String getSelectionMode() {
		return selectionMode;
	}
	
	/*
	* SHOW TOOLTIP
	*/
	
	public void setShowTooltip(boolean showTooltip) {
		this.showTooltip = showTooltip;
	}
	
	public boolean isShowTooltip() {
		return showTooltip;
	}
	
	/*
	* SHOW MOUSE LOCATION
	*/
	
	public void setShowMouseLocation(boolean showMouseLocation) {
		this.showMouseLocation = showMouseLocation;
	}
	
	public boolean isShowMouseLocation() {
		return showMouseLocation;
	}
	
	/*
	* W0
	*/
	
	public void setW0(int w0) {
		this.w0 = w0;
	}
	
	public int getW0() {
		return w0;
	}
	
	/*
	* H0
	*/
	
	public void setH0(int h0) {
		this.h0 = h0;
	}
	
	public int getH0() {
		return h0;
	}
	
	/*
	* X0
	*/
	
	public void setX0(int x0) {
		this.x0 = x0;
	}
	
	public int getX0() {
		return x0;
	}
	
	/*
	* Y0
	*/
	
	public void setY0(int y0) {
		this.y0 = y0;
	}
	
	public int getY0() {
		return y0;
	}
	
	/*
	* AREA
	*/
	
	public void setArea(Rectangle area) {
		this.area = area;
	}
	
	public Rectangle getArea() {
		return area;
	}
	
	/*
	* HOVER
	*/
	
	public String getHoverKey() {
		return hoverKey;
	}
	
	/*
	* SELECTED
	*/
	
	public String getSelectedKey() {
		return selectedKey;
	}
	
	/*
	* TOOLTIP BG COLOR
	*/
	
	public Color getTooltipBgColor() {
		return tooltipBgColor;
	}
	
	public void setTooltipBgColor(Color tooltipBgColor) {
		this.tooltipBgColor = tooltipBgColor;
	}
	
	/*
	* TOOLTIP FG COLOR
	*/
	
	public Color getTooltipFgColor() {
		return tooltipFgColor;
	}
	
	public void setTooltipFgColor(Color tooltipFgColor) {
		this.tooltipFgColor = tooltipFgColor;
	}
	
	/*
	* TOOLTIP BORDER COLOR
	*/
	
	public Color getTooltipBorderColor() {
		return tooltipBorderColor;
	}
	
	public void setTooltipBorderColor(Color tooltipBorderColor) {
		this.tooltipBorderColor = tooltipBorderColor;
	}
	
	/*
	* TOOLTIP FONT
	*/
	
	public Font getTooltipFont() {
		return tooltipFont;
	}
	
	public void setTooltipFont(Font tooltipFont) {
		this.tooltipFont = tooltipFont;
	}
	
	/*
	* SELECTION COLOR
	*/
	
	public Color getSelectionColor() {
		return selectionColor;
	}
	
	public void setSelectionColor(Color selectionColor) {
		this.selectionColor = selectionColor;
	}
	
	/*
	* OTHER COLOR
	*/
	
	public Color getOtherColor() {
		return otherColor;
	}
	
	public void setOtherColor(Color otherColor) {
		this.otherColor = otherColor;
	}
	
	/*
	* AREA COLOR
	*/
	
	public Color getAreaColor() {
		return areaColor;
	}
	
	public void setAreaColor(Color areaColor) {
		this.areaColor = areaColor;
	}
	
	/*
	* HOVER COLOR
	*/
	
	public Color getHoverColor() {
		return hoverColor;
	}
	
	public void setHoverColor(Color hoverColor) {
		this.hoverColor = hoverColor;
	}
	
	/*
	* KEY T
	*/
	
	public T getKeyT() {
		return keyT;
	}
	
	public void setKeyT(T keyT) {
		this.keyT = keyT;
	}
	
	/*
	* AREA COLOR T
	*/
	
	public T getAreaColorT() {
		return areaColorT;
	}
	
	public void setAreaColorT(T areaColorT) {
		this.areaColorT = areaColorT;
	}
	
	/*
	* TOOLTIP TEXT T
	*/
	
	public T getTooltipTextT() {
		return tooltipTextT;
	}
	
	public void setTooltipTextT(T tooltipTextT) {
		this.tooltipTextT = tooltipTextT;
	}
	
	/*
	* TOOLTIP ICON T
	*/
	
	public T getTooltipIconT() {
		return tooltipIconT;
	}
	
	public void setTooltipIconT(T tooltipIconT) {
		this.tooltipIconT = tooltipIconT;
	}
	
	/*
	* ID PAINT F
	*/
	
	public F getIdPaintF() {
		return idPaintF;
	}
	
	public void setIdPaintF(F idPaintF) {
		this.idPaintF = idPaintF;
	}
	
	/*
	* KEY SELECT F
	*/
	
	public F getKeySelectF() {
		return keySelectF;
	}
	
	public void setKeySelectF(F keySelectF) {
		this.keySelectF = keySelectF;
	}
	
	/*
	* SCALE FACTOR
	*/
	
	public double getScaleFactor() {
		return scaleFactor;
	}
	
	public void setScaleFactor(double scaleFactor) {
		this.scaleFactor = scaleFactor;
	}
	
	/*
	* EVENTS
	*/
	
	private void selectionChanged() {
		entity.send(entity, "selectionChanged()");
	}
	
	private void hoverChanged() {
		entity.send(entity, "hoverChanged()");
	}
	
	/*
	* APPLY
	*/
	
	private Object apply(T t, Object input) {
		try{return t.t(input);}
		catch(Exception e)
		{Outside.err(entity, "apply", e);}
		return null;
	}
	
	private boolean apply(F f, Object input, boolean errOutput) {
		try{return f.f(input);}
		catch(Exception e)
		{Outside.err(entity, "apply", e);}
		return errOutput;
	}
}

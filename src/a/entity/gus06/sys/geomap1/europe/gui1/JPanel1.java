package a.entity.gus06.sys.geomap1.europe.gui1;

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
	
	public static final Color DEFAULT_COLOR_SEA = new Color(70, 130, 180);
	public static final Color DEFAULT_COLOR_SELECTION = new Color(0, 0, 0, 150);
	public static final Color DEFAULT_COLOR_AREA = Color.WHITE;
	
	public static final Color DEFAULT_COLOR_BG_TOOLTIP = new Color(255, 255, 200, 230);
	public static final Color DEFAULT_COLOR_FG_TOOLTIP = Color.BLACK;
	public static final Color DEFAULT_COLOR_BORDER_TOOLTIP = Color.BLACK;
	
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
	
	private boolean showTooltip = true;
	private String selectionMode = SELECTIONMODE_HOVER;
	
	private Color selectionColor = DEFAULT_COLOR_SELECTION;
	private Color seaColor = DEFAULT_COLOR_SEA;
	private Color areaColor = DEFAULT_COLOR_AREA;
	private Color hoverColor = null;
	
	private Color tooltipBgColor = DEFAULT_COLOR_BG_TOOLTIP;
	private Color tooltipFgColor = DEFAULT_COLOR_FG_TOOLTIP;
	private Color tooltipBorderColor = DEFAULT_COLOR_BORDER_TOOLTIP;
	
	private String selectedCode;
	private String selectedName;
	
	private String hoverCode;
	private String hoverName;
	
	private T tooltipTextT;
	private T tooltipIconT;
	private T areaColorT;
	
	
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
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
        doc = factory.createDocument(null, is);
        readDocument();
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
            if(isCountryElement(element)) {
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
        
		g2d.translate(dx(), dy());
        g2d.setClip(area);

        g2d.setColor(seaColor);
        g2d.fill(area);
        
        for(GraphicsNode node : m.keySet()) {
        	Element value = m.get(node);
            String id = findId(value);
            String code = findCode(id);
			boolean isSelected = code!=null && Objects.equals(code, selectedCode);
			boolean isHover = code!=null && Objects.equals(code, hoverCode);
			
			Color fillColor = buildAreaColor(code);
			
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
    	
        if (hoverCode!=null && showTooltip) {
			String tooltipText = buildTooltipText(hoverCode);
			Icon icon = buildTooltipIcon(hoverCode);
			
			int iconWidth = icon!=null ? icon.getIconWidth() : 0;
			int iconHeight = icon!=null ? icon.getIconHeight() : 0;
			int iconOffset = icon!=null ? iconWidth + 3 : 0;
			
            g2d.setFont(g2d.getFont().deriveFont(14f));
            int textWidth = g2d.getFontMetrics().stringWidth(tooltipText);
            int textHeight = g2d.getFontMetrics().getHeight();
			
            int padding = 6;
            
            int x = mouseLocation.x + 5;
            int y = mouseLocation.y - 10 - textHeight;
            
            int w = textWidth + 2 * padding + iconOffset;
            int h = textHeight + 2 * padding;
            int r = 15;
            
            if(x+w>area.width) x = area.width - w;
            if(y+h>area.height) y = area.height - h;
            
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
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
    
    private int dx() {
    	return (getWidth() - bounds.width) / 2;
    }
    
    private int dy() {
    	return (getHeight() - bounds.height) / 2;
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
    
    private String findCode(String id) {
    	return id!=null ? id.split("-")[0] : null;
    }
    
    private String findName(String code) {
    	if (code==null) return null;
    	if (code.contains("_")) return null;
    	Locale l = new Locale("", code);
    	String name = l.getDisplayCountry();
    	if (name.equals(code.toUpperCase())) return null;
        return name;
    }
    
    private boolean isCountryElement(Element element) {
    	String id = findId(element);
    	String code = findCode(id);
    	return findName(code)!=null;
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
		mouseLocation.translate(-dx(), -dy());
		
		Element element = elementAtPoint(mouseLocation);
        String id = findId(element);
        String code = findCode(id);
        String name = findName(code);
        if(name==null) code = null;
        
        if(!Objects.equals(code, hoverCode)) {
        	hoverCode = code;
        	hoverName = name;
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
		selectedCode = hoverCode;
		selectedName = hoverName;
		selectionChanged();
	}
	
	/*
	* BUILD
	*/
	
	private String buildTooltipText(String code) {
		if(code==null) return "";
		if(tooltipTextT==null) return hoverName;
		String value = (String) apply(tooltipTextT, code);
		return value!=null ? value : "";
	}
	
	private Icon buildTooltipIcon(String code) {
		if(code==null) return null;
		if(tooltipIconT==null) return null;
		return (Icon) apply(tooltipIconT, code);
	}
	
	private Color buildAreaColor(String code) {
		if(code==null) return null;
		if(areaColorT==null) return areaColor;
		return (Color) apply(areaColorT, code);
	}
	
	/*
	* SELECT
	*/
	
	public void select(String code){
		selectedCode = code;
		selectedName = findName(code);
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
	* HOVER
	*/
	
	public String getHoverCode() {
		return hoverCode;
	}
	
	public String getHoverName() {
		return hoverName;
	}
	
	/*
	* SELECTED
	*/
	
	public String getSelectedCode() {
		return selectedCode;
	}
	
	public String getSelectedName() {
		return selectedName;
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
	* SELECTION COLOR
	*/
	
	public Color getSelectionColor() {
		return selectionColor;
	}
	
	public void setSelectionColor(Color selectionColor) {
		this.selectionColor = selectionColor;
	}
	
	/*
	* SEA COLOR
	*/
	
	public Color getSeaColor() {
		return seaColor;
	}
	
	public void setSeaColor(Color seaColor) {
		this.seaColor = seaColor;
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
}

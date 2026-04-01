package a.entity.gus06.graphics.setrenderinghint.default1;

import a.framework.*;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141206";}


	public void p(Object obj) throws Exception
	{setRenderingHint((Graphics2D) obj);}


	
	private void setRenderingHint(Graphics2D g)
	{
		g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,	RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,		RenderingHints.VALUE_ANTIALIAS_DEFAULT);
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,		RenderingHints.VALUE_COLOR_RENDER_DEFAULT);
		g.setRenderingHint(RenderingHints.KEY_DITHERING,		RenderingHints.VALUE_DITHER_DEFAULT);
		g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,	RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,		RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,		RenderingHints.VALUE_RENDER_DEFAULT);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,		RenderingHints.VALUE_STROKE_DEFAULT);
	}
}

package a.entity.gus06.appli.vindinium.gui.gameview.session;

import java.awt.BorderLayout;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EntityImpl implements Entity, P, I {

	public String creationDate() {return "20170923";}


	private Service botsView;
	private Service progressView;
	private Service summary1View;
	private Service gameLabel;
	private Service layoutV;
	
	private JPanel panel;


	public EntityImpl() throws Exception
	{
		botsView = Outside.service(this,"gus06.appli.vindinium.gui.gameview.session.bots");
		progressView = Outside.service(this,"gus06.appli.vindinium.gui.gameview.session.progress");
		summary1View = Outside.service(this,"gus06.appli.vindinium.gui.gameview.session.boardsummary");
		gameLabel = Outside.service(this,"gus06.appli.vindinium.gui.gameview.session.gamelabel");
		layoutV = Outside.service(this,"gus06.swing.panel.build.vline.gap10");
		
		JPanel p1 = wc(gameLabel,progressView);
		
		panel = (JPanel) layoutV.t(new Object[]{
				p1,
				summary1View,
				new JLabel(" "),
				botsView
				});
	}


	public void p(Object obj) throws Exception
	{
		gameLabel.p(obj);
		botsView.p(obj);
		summary1View.p(obj);
		progressView.p(obj);
	}


	public Object i() throws Exception
	{return panel;}

	
	private JPanel wc(I w, I c) throws Exception
	{
		JPanel p = new JPanel(new BorderLayout());
		p.add((JComponent) w.i(),BorderLayout.WEST);
		p.add((JComponent) c.i(),BorderLayout.CENTER);
		return p;
	}
}

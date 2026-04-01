package a.entity.gus06.appli.vindinium.gui.gameview;

import a.framework.*;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, P, V, I {

	public String creationDate() {return "20170923";}

	public static final int EDGE = 10;
	

	private Service boardView;
	private Service panelSession;
	private Service panelServer;
	private Service playerView;
	
	private Service titleBorder;
	private Service layoutV;
	
	private JPanel panel;


	public EntityImpl() throws Exception
	{
		boardView = Outside.service(this,"gus06.appli.vindinium.gui.gameview.board");
		panelSession = Outside.service(this,"gus06.appli.vindinium.gui.gameview.session");
		panelServer = Outside.service(this,"gus06.appli.vindinium.gui.gameview.server");
		playerView = Outside.service(this,"gus06.appli.vindinium.gui.gameview.playerstate");
		titleBorder = Outside.service(this,"gus06.swing.comp.cust2.border.titledborder1.p10");
		layoutV = Outside.service(this,"gus06.swing.panel.build.vline.gap10");
		
		titleBorder.v("Game session",panelSession);
		titleBorder.v("Player bot",playerView);
		titleBorder.v("Server access",panelServer);
		
		JPanel p_east = (JPanel) layoutV.t(new Object[]{
				panelSession,
				playerView,
				panelServer,
				});
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) boardView.i(),BorderLayout.CENTER);
		panel.add(p_east,BorderLayout.EAST);
		
		panel.setBorder(BorderFactory.createEmptyBorder(EDGE,EDGE,EDGE,EDGE));
		p_east.setBorder(BorderFactory.createEmptyBorder(0,EDGE,0,0));
	}
	
	

	public Object i() throws Exception
	{return panel;}



	public void p(Object obj) throws Exception
	{
		boardView.p(obj);
		panelSession.p(obj);
		playerView.p(obj);
		panelServer.p(obj);
	}
	
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("exception")) {gameInterrupted((Exception) obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	private void gameInterrupted(Exception exception) throws Exception
	{
		panelServer.v("exception",exception);
	}
}
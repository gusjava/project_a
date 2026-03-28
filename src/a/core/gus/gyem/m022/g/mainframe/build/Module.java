package a.core.gus.gyem.m022.g.mainframe.build;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;

public class Module extends GyemSystem implements G {
	
	private JFrame frame;

	public Object g() throws Exception {
		if(frame==null) init();
		return frame;
	}
	
	private void init() throws Exception {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {exit();}
		});
	}
	
	private void exit() {
		try{moduleE(M023_E_MAINFRAME_EXIT).e();}
		catch (Exception e) {System.exit(1);}
	}
}

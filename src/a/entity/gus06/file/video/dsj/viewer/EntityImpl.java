package a.entity.gus06.file.video.dsj.viewer;

import a.framework.*;
import javax.swing.JComponent;

import de.humatic.dsj.DSFiltergraph;
import de.humatic.dsj.DSJUtils;
import de.humatic.dsj.DSMovie;
import de.humatic.dsj.SwingMovieController;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.io.File;
import javax.swing.JPanel;
import java.awt.BorderLayout;


public class EntityImpl implements Entity, I, P, PropertyChangeListener {

	public String creationDate() {return "20150616";}



	private Service shiftPanel;
	
	private File file;
	
	private DSMovie movie;
	private SwingMovieController controller;



	public EntityImpl() throws Exception
	{
		shiftPanel = Outside.service(this,"*gus06.swing.panel.shiftpanel");
	}
	
	
	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		reset();
		file = (File) obj;
		
		if(file==null || !file.isFile())
			shiftPanel.p(null);
		else initMovie();
	}
	
	
	
	private void initMovie() throws Exception
	{
		movie = new DSMovie(file.getAbsolutePath(),DSFiltergraph.DD7,this);
		controller = new SwingMovieController(movie);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(movie.asComponent(),BorderLayout.CENTER);
		panel.add(controller,BorderLayout.SOUTH);
		
		shiftPanel.p(panel);
	}
	
	
	
	public void propertyChange(PropertyChangeEvent pe)
	{ 
		switch(DSJUtils.getEventType(pe)) {}
	}
	
	
	private void reset()
	{
		if(movie==null) return;
		movie.stop();
		movie.dispose();
	}
}

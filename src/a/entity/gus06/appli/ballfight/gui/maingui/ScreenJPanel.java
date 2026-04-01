package a.entity.gus06.appli.ballfight.gui.maingui;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;


class ScreenJPanel extends JPanel implements Runnable
{
    public static final long SLEEP = 10;
    public static final double SPEED = 2;
    
    public static final Color COLOR_EMPTY = Color.WHITE;
    
    private int size;
    private int number;
    private int number_A = 0;
    private int number_B = 0;
    
    private Vector list;
    private JLabel label_A;
    private JLabel label_B;
    private JLabel label_0;
    private JPanel infoPanel;
    
    private Color colorA;
    private Color colorB;
    
    
    
    private Color randomColor()
    {
        int r = (int)(Math.random()*256);
        int g = (int)(Math.random()*256);
        int b = (int)(Math.random()*256);
        return new Color(r,g,b);
    }
    
    

    public ScreenJPanel()
    {
        list = new Vector();
        
        label_A = new JLabel(" ");
        label_B = new JLabel(" ");
        
        label_0 = new JLabel(" ");
        label_0.setForeground(COLOR_EMPTY);
        
        infoPanel = new JPanel(new GridLayout(1,3));
        infoPanel.add(label_A);
        infoPanel.add(label_B);
        infoPanel.add(label_0);
    }
    
    
    private void initColors()
    {
        colorA = randomColor();
        colorB = randomColor();
        
        label_A.setForeground(colorA);
        label_B.setForeground(colorB);
    }
    
    
    public JPanel infoPanel()
    {return infoPanel;}
    
    
    
    public synchronized void  initSimulation(int number, int size)
    {
        this.number = number;
        this.size = size;
        initColors();
        
        list.clear();
        for(int i=0;i<number;i++)
        {
            if(i<number/2) createAnimation(colorA);
            else createAnimation(colorB);
        }
    }
    
    private void createAnimation(Color color)
    {list.add(new Animation(color));}




    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0,0,getWidth(),getHeight());
        for(int i=0;i<list.size();i++)
        {
            Animation an = (Animation) list.get(i);
            an.paint(g2);
        }
    }
    
    

    public void run()
    {
        while(true)
        {
            try{Thread.sleep(SLEEP);}
            catch(Exception e){}
            
            synchronized(this)
            {
                for(int i=0;i<list.size()-1;i++)
                for(int j=i+1;j<list.size();j++)
                {
                    Animation an1 = (Animation) list.get(i);
                    Animation an2 = (Animation) list.get(j);
                    
                    if(!an1.rec().intersection(an2.rec()).isEmpty())
                    {
                        Color c1 = an1.color();
                        Color c2 = an2.color();
                        an1.change(c2);
                        an2.change(c1);
                    }
                }
                
                number_A = 0;
                number_B = 0;
                for(int i=0;i<list.size();i++)
                {
                    Animation an = (Animation) list.get(i);
                    an.updatePosition();
                    if(an.color()==colorA) number_A++;
                    if(an.color()==colorB) number_B++;
                }
                label_A.setText(" "+number_A+" ");
                label_B.setText(" "+number_B+" ");
                label_0.setText(" "+(number-number_A-number_B)+" ");

                repaint();
            }
        }
    }






    private class Animation
    {
        private Rectangle rec;
        private Color color;
        
        private double dx;
        private double dy;
        
        private double x;
        private double y; 
        
        public Rectangle rec()
        {return rec;}
        
        public Color color()
        {return color;}
        
        public void change(Color otherColor)
        {
            if(otherColor==colorA)
            {
                if(color==colorB) color = COLOR_EMPTY;
                else if(color==COLOR_EMPTY) color = colorA;
            }
            else if(otherColor==colorB)
            {
                if(color==colorA) color = COLOR_EMPTY;
                else if(color==COLOR_EMPTY) color = colorB;
            }
        }
        
        
        
        public Animation(Color color)
        {
            this.x = Math.random()*(getWidth()-size);
            this.y = Math.random()*(getHeight()-size);
            
            dx =(2*Math.random()-1)*SPEED;
            dy =(2*Math.random()-1)*SPEED;
            
            rec = new Rectangle((int)x,(int)y,size,size);
            this.color = color;
        }

        
        public void paint(Graphics2D g2)
        {
            g2.setColor(color);
            g2.fillOval(rec.x,rec.y,rec.width,rec.height);
            g2.setColor(Color.BLACK);
            g2.drawOval(rec.x,rec.y,rec.width,rec.height);
        }
        

        public void updatePosition()
        {
            x += dx;
            y += dy;
            rec.setLocation((int)x,(int)y);
            
            if(x+size>=getWidth()) dx = -1*Math.abs(dx);
            else if(x<=0) dx = Math.abs(dx);
            if(y+size>=getHeight()) dy = -1*Math.abs(dy);
            else if(y<=0) dy = Math.abs(dy);
        }
    }

}

package ca.etsmtl.capra.tool.range.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

public class DataView extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private final int GROSSEUR_POINT=5;
	private final double ANGLE_DEPART=-0.785398163;
	private double ZOOM=0.03;
	private int origineX;
	private int origineY;
	private List<Point2D> positionXY=new Vector<Point2D>();
	
	public DataView(int width,int height){
		this.setSize(width, height);
		this.setBackground(Color.BLACK);
		origineX=width/2;
		origineY=height/2;
//		positionXY=new Vector<Point2D>();		
		
		this.addMouseWheelListener(createScrollMouseListener());
	}
	
	private MouseWheelListener createScrollMouseListener() {
		return new MouseWheelListener() {
			@Override
			
			public void mouseWheelMoved(MouseWheelEvent event) {
				ZOOM += (double) (event.getWheelRotation() / 100.0);
				repaint();
			}
			
		};
	}

	public void setPositionRad(int[] positionRad){
		Vector<Point2D> points = new Vector<Point2D>();
		for(int i=0;i<541;i++){
			Point2D point=new Point();
			point.setLocation(positionRad[i]*Math.cos(i*0.00872664626+ANGLE_DEPART),positionRad[i]*Math.sin(i*0.00872664626+ANGLE_DEPART));
			points.add(point);
		}
		positionXY=points;
		this.repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.GREEN);
		
		for(Point2D xy:positionXY)	
				g2d.fill(new Ellipse2D.Double(ZOOM*xy.getX()+origineX, ZOOM*xy.getY()+origineY, GROSSEUR_POINT, GROSSEUR_POINT));
		
		
	}
	
	public void setPositionXY(List<Point2D> vector)
	{
		this.positionXY=vector;
		this.repaint();
	
	}

}

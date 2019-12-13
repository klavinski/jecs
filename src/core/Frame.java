package core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {

	private static BufferedImage canvas;
	public static final int FPS = 60;
	private static HashMap<Integer, Boolean> keyboard = new HashMap<Integer, Boolean>();
	private static JPanel panel;
	
	public Frame( String title, int width, int height, double resolution ) {
		
		super( title );
		this.setIconImage( new ImageIcon( "src/icon.png" ).getImage() );
		canvas = new BufferedImage( width, height, BufferedImage.TYPE_INT_ARGB );
		
		KeyListener keyListener = new KeyListener() {

			@Override
			public void keyPressed( KeyEvent e ) {
				
				keyboard.put( e.getKeyCode(), true );
			}

			@Override
			public void keyReleased( KeyEvent e ) {
				
				keyboard.put( e.getKeyCode(), false );
			}

			@Override
			public void keyTyped( KeyEvent e ) {}
			
		};
		this.addKeyListener( keyListener );
		
		panel = new JPanel() {
	    	
	        @Override
	        public void paintComponent( Graphics g ) {
	        	
	        	( ( Graphics2D ) g ).scale( resolution, resolution );
	            g.drawImage( canvas, 0, 0, null );
	        }
	    };
	    panel.setPreferredSize( new Dimension( ( int ) resolution * width, ( int ) resolution * height ) );
        this.add( panel );
        
        this.pack();
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setVisible( true );
	}
	
	public static void refresh() {
		
		panel.repaint();
	}
	
	public static BufferedImage getCanvas() {
		
		return canvas;
	}
	
	public static boolean getKey( String key ) {

		switch ( key ) {
			
			case "down" : return keyboard.getOrDefault( 83, false );
			case "left" : return keyboard.getOrDefault( 81, false );
			case "right": return keyboard.getOrDefault( 68, false );
			case "space": return keyboard.getOrDefault( 32, false );
			case "shift": return keyboard.getOrDefault( 16, false );
			case "up"   : return keyboard.getOrDefault( 90, false );
		}
		return false;
	}
}

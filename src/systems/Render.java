package systems;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import javax.imageio.ImageIO;

import components.*;
import core.*;

public class Render extends System_ {

	private BufferedImage canvas;
	private HashMap<String, BufferedImage> gfx = new HashMap<String, BufferedImage>();
	private int milliseconds = 0;
	
	public Render() {
		
		canvas = Frame.getCanvas();
		File gfxFolder = new File( "./gfx" );
		for ( File spriteFolder: gfxFolder.listFiles() )
	    for ( File png:       spriteFolder.listFiles() ) try {
	    	
			gfx.put(
				spriteFolder.getName() + png.getName().replaceFirst( ".[^.]*$", "" ),
				ImageIO.read( png )
			);
			
		} catch (IOException e) { e.printStackTrace(); }	    
	}
	
	public void apply( Entity[] entities ) {
		
		milliseconds += 1000 / Frame.FPS;
		int frameNumber = milliseconds / 150 % 4;
		
		ArrayList<Entity> entitiesToDraw = new ArrayList<Entity>();
		
		for ( Entity entity: entities )
			
			if ( Height.in( entity ) )
			if ( Sprite.in( entity ) )
			if ( Width .in( entity ) )
			if ( X     .in( entity ) )
			if ( Y     .in( entity ) )
			if ( Z     .in( entity ) )				
				
				entitiesToDraw.add( entity );
		
		class SortByXYZ implements Comparator<Entity> {
			
		    public int compare( Entity a, Entity b ) {
		    	
		    	//See the fire
		    	if ( Sprite.in( a ) && Sprite.of( a ) == "flame" )
		    		return 1;
		    	
		    	if ( Sprite.in( b ) && Sprite.of( b ) == "flame" )
		    		return -1;
		    	
		    	//No ambiguity: a is farther in Y than b
		    	if ( Y.of( a ) - Width.of( a ) / 2 > Y.of( b ) + Width.of( b ) / 2 )	
		    		return 1;

		    	if ( Y.of( b ) - Width.of( b ) / 2 > Y.of( a ) + Width.of( a ) / 2 )
		    		return -1;
		    	
		    	// a is higher and in front of b: we draw a then b
		    	if ( Y.of( a ) + Width.of( a ) > Y.of( b ) + Width.of( b ) )
		    	if ( Y.of( a ) - Width.of( a ) > Y.of( b ) - Width.of( b ) )
		    	if ( Z.of( a ) >= Z.of( b ) )
		    		return 1;
		    	
		    	if ( Y.of( b ) + Width.of( b ) > Y.of( a ) + Width.of( a ) )
		    	if ( Y.of( b ) - Width.of( b ) > Y.of( a ) - Width.of( a ) )
			    if ( Z.of( b ) >= Z.of( a ) )
			    	return -1;
		    	
		    	//The highest
		    	if ( Z.of( a ) + Height.of( a ) > Z.of( b ) + Height.of( b ) )
		    		return 1;
		    	if ( Z.of( b ) + Height.of( b ) > Z.of( a ) + Height.of( a ) )
		    		return -1;
		    	else
		    		return 0;
		    }
		}
		
		Collections.sort( entitiesToDraw, new SortByXYZ() );

		for ( Entity entity: entitiesToDraw ) {
			
			String spriteName = ( Direction.in( entity )
				      ? Sprite.of( entity ) + "_" + Direction.of( entity )
					  : Sprite.of( entity ) ) +
					  ( ( Animated.in( entity ) && Animated.of( entity ) ) ? frameNumber : 0 );
			
			BufferedImage sprite = gfx.get( spriteName );
			if ( sprite != null )
			canvas.getGraphics().drawImage( 
					sprite,
					( int ) ( 16 *   X.of( entity ) ) - sprite.getWidth() / 2,
					( int ) ( 16 * ( Y.of( entity ) - Z.of( entity ) / 2 - Height.of( entity ) / 2 - Width.of( entity ) / 2 ) ),
					null
				);
		}
		Frame.refresh();
	}
	
}

package systems;

import components.*;
import core.*;
import entities.*;

public class Action extends System_ {

	double soundTimeout = 0.0;
	
	@Override
	public void apply( Entity[] entities ) {
		
		soundTimeout -= 1.0 / Frame.FPS;

		for ( Entity         entity: entities )
		if  ( Controlled.in( entity )         )
		if  ( Controlled.of( entity ) == true )
		if  ( Frame.getKey( "space" ) ) {
			
			if ( Type.in( entity ) && Type.of( entity ) == "fire" ) {

				if ( X        .in( entity ) )
				if ( Y        .in( entity ) )
				if ( Z        .in( entity ) )
				if ( Width    .in( entity ) )
				if ( Direction.in( entity ) ) {
					
					double dx = Direction.of( entity ) == "left"  ? -1
							  : Direction.of( entity ) == "right" ?  1 : 0;
					double dy = Direction.of( entity ) == "up"    ? -1
							  : Direction.of( entity ) == "down"  ?  1 : 0;
					
					new Flame( 
						X.of( entity ) + dx,
						Y.of( entity ) + dy,
						Z.of( entity ),
						null
					);
					
					if ( soundTimeout <= 0.0 ) {
						Sound.play( "fire" );
						soundTimeout = 1.0;
					}
				}
			} else {
				
				for ( Entity        trainer:      entities  )
				if  ( Sprite    .in( trainer )              )
				if  ( Sprite    .of( trainer ) == "trainer" )
				if  ( Controlled.in( trainer )              )
				if  ( Controlled.of( trainer )              )
				if  ( Direction .in( trainer )              )
				if  ( X         .in( trainer )              )
				if  ( Y         .in( trainer )              )
					
				for ( Entity     blackPidgey:          entities  )
				if  ( Sprite.in( blackPidgey )                   )
				if  ( Sprite.of( blackPidgey ) == "black_pidgey" )
				if  ( X     .in( blackPidgey )                   )
				if  ( Y     .in( blackPidgey )                   )
				if  (
					Direction.of( trainer ) == "up"    && Math.abs( X.of( trainer )     - X.of( blackPidgey ) ) +
													      Math.abs( Y.of( trainer ) - 1 - Y.of( blackPidgey ) ) < 1.0
				 || Direction.of( trainer ) == "down"  && Math.abs( X.of( trainer )     - X.of( blackPidgey ) ) +
					                                      Math.abs( Y.of( trainer ) + 1 - Y.of( blackPidgey ) ) < 1.0
				 || Direction.of( trainer ) == "left"  && Math.abs( X.of( trainer ) - 1 - X.of( blackPidgey ) ) +
					                                      Math.abs( Y.of( trainer )     - Y.of( blackPidgey ) ) < 1.0 
				 || Direction.of( trainer ) == "right" && Math.abs( X.of( trainer ) + 1 - X.of( blackPidgey ) ) +
					                                      Math.abs( Y.of( trainer )     - Y.of( blackPidgey ) ) < 1.0 
				) {					
					Sound.play( "capture" );
					Controlled.of( blackPidgey,     false  );
					Sprite    .of( blackPidgey, "pokeball" );									
				}	
				
				if ( DZ  .in( entity )        )
				if ( DZ  .of( entity ) <= 0.0 )
				if ( DZ  .of( entity ) == 0.0 ||
				     Type.in( entity ) &&
				     Type.of( entity ) == "flying"
				) {
					
					DZ.of( entity, 1.0 / Math.sqrt( Frame.FPS ) );
					Sound.play( "jump" );
				}
			}
		}
	}
}
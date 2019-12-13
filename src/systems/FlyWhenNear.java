package systems;

import components.*;
import core.*;

public class FlyWhenNear extends System_ {

	@Override
	public void apply( Entity[] entities ) {

		for ( Entity         pidgey:    entities  )
		if  ( Sprite    .in( pidgey )             )
		if  ( Sprite    .of( pidgey ) == "pidgey" )
		if  ( Controlled.in( pidgey ) ==   false  )
		if  ( X         .in( pidgey )             )
		if  ( Y         .in( pidgey )             )
		if  ( Z         .in( pidgey )             )
		if  ( Z         .of( pidgey ) >      1.0  )
		if  ( DZ        .in( pidgey )             )
		if  ( DZ        .of( pidgey ) <=     0.0  ) {
			
			for ( Entity     trainer:     entities  )
			if  ( Sprite.in( trainer )              )
			if  ( Sprite.of( trainer ) == "trainer" )
			if  ( X     .in( trainer )              )
			if  ( Y     .in( trainer )              )
			if  ( Z     .in( trainer )              )
			if  (
				Math.abs( X.of( pidgey ) - X.of( trainer ) ) +
				Math.abs( Y.of( pidgey ) - Y.of( trainer ) ) +
				Math.abs( Z.of( pidgey ) - Z.of( trainer ) )
			< 3.0  ) {
				
				DZ.of( pidgey, 1.0 / Math.sqrt( Frame.FPS ) );
				Sound.play( "jump" );
			}
		}
	}
}

package systems;

import components.*;
import core.*;
import entities.*;

public class BurnPidgey extends System_ {

	@Override
	public void apply( Entity[] entities ) {

		for ( Entity     pidgey:     entities )
		if  ( Sprite.in( pidgey )             )
		if  ( Sprite.of( pidgey ) == "pidgey" )
		if  ( X     .in( pidgey )             )
		if  ( Y     .in( pidgey )             )
		if  ( Z     .in( pidgey )             )
		
		for ( Entity      burningEntity: entities )
		if  ( Burning.in( burningEntity )         )
		if  ( Burning.of( burningEntity )         )
		if  ( X      .in( burningEntity )         )
		if  ( Y      .in( burningEntity )         )
		if  ( Z      .in( burningEntity )         )
		if  (				
			Math.abs( X.of( pidgey ) - X.of( burningEntity ) ) +
			Math.abs( Y.of( pidgey ) - Y.of( burningEntity ) ) +
			Math.abs( Z.of( pidgey ) - Z.of( burningEntity ) )			
		< 3.0 ) {
			
			new Flame( X.of( pidgey ), Y.of( pidgey ), Z.of( pidgey ), null );
			Sprite.of( pidgey, "black_pidgey" );
		}
	}
}

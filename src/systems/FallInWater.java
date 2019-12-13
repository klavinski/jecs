package systems;

import components.DZ;
import components.Z;
import core.Entity;
import core.Sound;
import core.System_;

public class FallInWater extends System_ {

	@Override
	public void apply( Entity[] entities ) {

		for ( Entity entity: entities )
		if  ( DZ.in( entity )         )
		if  ( Z .in( entity )         )
		if  ( Z .of( entity ) <= -1.9 ) {
			
			Sound.play( "splash" );
			entity.remove();
		}
	}
}

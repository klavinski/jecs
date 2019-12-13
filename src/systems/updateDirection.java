package systems;

import components.*;
import core.*;

public class updateDirection extends System_ {

	@Override
	public void apply( Entity[] entities ) {

		for ( Entity        entity: entities )
		if  ( Direction.in( entity )         )
		if  ( DX       .in( entity )         )
		if  ( DY       .in( entity )         ) {
			
			if ( Math.abs( DX.of( entity ) ) >
			     Math.abs( DY.of( entity ) )
			) {
				
				if ( DX.of( entity ) < -1.0 / Frame.FPS ) Direction.of( entity, "left"  );
				if ( DX.of( entity ) >  1.0 / Frame.FPS ) Direction.of( entity, "right" );
				
			} else {
				
				if ( DY.of( entity ) < -1.0 / Frame.FPS ) Direction.of( entity, "up"   );
				if ( DY.of( entity ) >  1.0 / Frame.FPS ) Direction.of( entity, "down" );
			}
		}
	}
}
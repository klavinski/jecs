package systems;

import components.*;
import core.*;

public class Move extends System_ {

	@Override
	public void apply( Entity[] entities ) {

		for ( Entity         entity: entities )
		if  ( Controlled.in( entity )         )
		if  ( Controlled.of( entity )         )
		if  ( DX        .in( entity )         )
		if  ( DY        .in( entity )         ) {
			
			double dX    = 0.0;
			double dY    = 0.0;
			double speed = 4.0 / Frame.FPS;
			
			if ( Frame.getKey( "down"  ) ) dY += speed;
			if ( Frame.getKey( "left"  ) ) dX -= speed;
			if ( Frame.getKey( "right" ) ) dX += speed;
			if ( Frame.getKey( "up"    ) ) dY -= speed;
			
			DX.of( entity, dX );
			DY.of( entity, dY );
			
			if ( Animated.in( entity ) )
				 Animated.of( entity,
						 Frame.getKey( "down"  ) ||
						 Frame.getKey( "left"  ) ||
						 Frame.getKey( "right" ) ||
						 Frame.getKey( "up"    )
			     );
		}
	}
}
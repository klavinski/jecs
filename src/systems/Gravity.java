package systems;

import components.*;
import core.*;

public class Gravity extends System_ {

	@Override
	public void apply( Entity[] entities ) {

		for ( Entity entity: entities )			
		if  ( DZ.in( entity )         )
			
			DZ.of( entity, 
			DZ.of( entity ) - 9.8 * 2.0 / ( Frame.FPS * ( Frame.FPS + 1 ) )
			);
	}
}

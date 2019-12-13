package systems;

import components.*;
import core.*;

public class Burn extends System_ {

	@Override
	public void apply( Entity[] entities ) {
		
		for ( Entity      entity: entities )
		if  ( Health .in( entity )         )
		if  ( Burning.in( entity )         )
		if  ( Burning.of( entity ) == true ) {
			
			Health.of( entity,
			Health.of( entity ) - 1.0 / Frame.FPS
			);
			
			if ( Health.of( entity ) <= 0.0 )
				entity.remove();
		}
	}

}

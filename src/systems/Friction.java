package systems;

import components.*;
import core.*;

public class Friction extends System_ {

	@Override
	public void apply( Entity[] entities ) {
		
		for ( Entity entity: entities )
		if  ( DX.in( entity )         )
		if  ( DY.in( entity )         )
		if  ( DZ.in( entity )         )
		if  (  X.in( entity )         )
	    if  (  Y.in( entity )         )
	    if  (  Z.in( entity )         ) {

	    	if ( Z.of( entity ) <= 0.1 ) {
	    		
	    	    DX.of( entity,
	    		DX.of( entity ) * 0.8
	    	    );
	    	    DY.of( entity,
		    	DY.of( entity ) * 0.8
	    	    );
	    	    
	    	} else {
	    		
	    		DX.of( entity, 0.0 );
	    		DY.of( entity, 0.0 );
	    	}
	    }
	}
}
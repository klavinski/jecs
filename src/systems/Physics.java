package systems;

import components.*;
import core.*;

public class Physics extends System_ {

	@Override
	public void apply( Entity[] entities ) {

		for ( Entity     entity: entities )
		if  ( DX    .in( entity )         )
		if  ( DY    .in( entity )         )
		if  ( DZ    .in( entity )         )
		if  ( Height.in( entity )         )
		if  ( Width .in( entity )         )
		if  ( X     .in( entity )         )
	    if  ( Y     .in( entity )         )
	    if  ( Z     .in( entity )         ) {
	    	
	    	boolean moveX = true;
	    	boolean moveY = true;
	    	boolean moveZ = true;
	    	
	    	double x = X.of( entity ) + DX.of( entity );
	    	double y = Y.of( entity ) + DY.of( entity );
	    	double z = Z.of( entity ) + DZ.of( entity );
	    	
	    	for ( Entity     otherEntity :  entities )
	    	if  (            otherEntity != entity   )
	    	if  ( Height.in( otherEntity )           )
	    	if  ( Width .in( otherEntity )           )
	    	if  ( X     .in( otherEntity )           )
	    	if  ( Y     .in( otherEntity )           )
	    	if  ( Z     .in( otherEntity )           ) {
	    		
	    		if ( contact(
	    			X     .of( entity ),
	    			Y     .of( entity ),
	    			z,
	    			Width .of( entity ),
	    			Height.of( entity ),
	    			otherEntity
	    		) ) {

	    			if ( DZ.in( otherEntity ) )
					     DZ.of( otherEntity,
					     DZ.of( otherEntity ) +
					     DZ.of( entity )
					     );
	    			
	    			if ( DX.in( otherEntity ) )
	    				 DX.of( entity,
	    				 DX.of( entity ) +
	    				 DX.of( otherEntity )
	    				 );
	    			if ( DY.in( otherEntity ) )
	    				 DY.of( entity,
	    				 DY.of( entity ) +
	    				 DY.of( otherEntity )
	    				 );
					    
					DZ.of( entity, 0.0 );
					moveZ = false;
		    	}
	    		
	    		if ( contact(
	    			x,
	    			Y     .of( entity ),
	    			Z     .of( entity ),
	    			Width .of( entity ),
	    			Height.of( entity ),
	    			otherEntity
	    		) ) {

	    			if ( DX.in( otherEntity ) )				    	
				    	 DX.of( otherEntity,
				    	 DX.of( otherEntity ) +
				    	 DX.of( entity )
				    	 );	    	
				    
	    			DX.of( entity, 0.0 );
				    moveX = false;
	    		}
	    		
	    		if ( contact(
	    			X     .of( entity ),
	    			y,
	    			Z     .of( entity ),
	    			Width .of( entity ),
	    			Height.of( entity ),
	    			otherEntity
	    		) ) {

	    			if ( DY.in( otherEntity ) )				    	
				    	 DY.of( otherEntity,
				    	 DY.of( otherEntity ) +
				    	 DY.of( entity )
				    );	
	    			
				    DY.of( entity, 0.0 );
				    moveY = false;
				}
	    		
	    	}
	    	
	    	if ( moveX )
	    		
	    		X.of( entity,
	    	    X.of( entity ) + DX.of( entity )
	    	    );
	    	
	    	if ( moveY )
	    		
	    	    Y.of( entity,
	    		Y.of( entity ) + DY.of( entity )
	    		);
	    	
	    	if ( moveZ )
	    		
	    		Z.of( entity,
	    	    Z.of( entity ) + DZ.of( entity )
	    		);
	    	
	    }
	}
	
	private static boolean contact( double x, double y, double z, double width, double height, Entity entity ) {
		
		return ( ! (
	    		x - width / 2 <= X.of( entity ) - Width.of( entity ) / 2
	    	 && x + width / 2 <= X.of( entity ) - Width.of( entity ) / 2
	    	 || x - width / 2 >= X.of( entity ) + Width.of( entity ) / 2
	    	 && x + width / 2 >= X.of( entity ) + Width.of( entity ) / 2
	    	) && ! (
	    		y - width / 2 <= Y.of( entity ) - Width.of( entity ) / 2
	 		 && y + width / 2 <= Y.of( entity ) - Width.of( entity ) / 2
	 		 || y - width / 2 >= Y.of( entity ) + Width.of( entity ) / 2
	 		 && y + width / 2 >= Y.of( entity ) + Width.of( entity ) / 2
	 		) && ! (
	 			z <= Z.of( entity ) && z + height <= Z.of( entity )
		 	 || z >= Z.of( entity ) + Height.of( entity ) && z + height >= Z.of( entity ) + Height.of( entity )
	 	) );
	}
}

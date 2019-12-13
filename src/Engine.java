import core.*;
import systems.*;

public class Engine {

	private static System_[] systems;

	public static void main( String[] args ) throws InterruptedException {
		
		new Frame( "Engine", 16 * 16, 16 * 12, 3.0 );
		Sound.play( "music" );
		
		systems = new System_[] {
			
			new InitEntities(),			
			new Gravity(),
			new Move(),
			new ChangeControlled(),			
			new Physics(),
			new Action(),
			new updateDirection(),
			new WatchPlayer(),
			new Friction(),
			new FallInWater(),
			new Burn(),
			new RemoveTied(),
			new PropagateFire(),
			new FlyWhenNear(),
			new BurnPidgey(),
			new Render()
		};
		
		while ( true ) {
		
			Thread.sleep( 1000 / Frame.FPS );
			for ( System_ system: systems )
				system.apply( Entity.getEntities() );			
		}
	}
}

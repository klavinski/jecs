package core;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	public static void play( String file ) {
		
		try {
			
	        Clip clip = AudioSystem.getClip();
	        clip.open( AudioSystem.getAudioInputStream( new File( "./sfx/" + file + ".wav" ) ) );
	        clip.start();
	        
	    } catch ( Exception e ) { System.out.println( file + " is missing." ); }
	}
}

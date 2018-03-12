package jri.agile.game;

import java.awt.Desktop;
import java.net.URI;

public class RickVideoPlayerImpl implements RickVideoPlayer {
	public void play () {
		try {
			Desktop d = Desktop.getDesktop();
			d.browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
		} catch (Exception exc) {
			
		}
	}
}

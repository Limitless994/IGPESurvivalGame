package dev.igpe.theamazingame.gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {
	
	public static Font loadFont(String path, float size) {
		InputStream is = null;
		try {
			is = FontLoader.class.getClassLoader().getResourceAsStream(path);
			return Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, size);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			if (is != null) {
				try {
					is.close();
				}
				catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
				
		}
		return null;
	}

}
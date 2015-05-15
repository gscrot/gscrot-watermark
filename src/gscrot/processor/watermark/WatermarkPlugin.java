package gscrot.processor.watermark;

import gscrot.api.Plugin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JMenuItem;

import com.redpois0n.gscrot.ImageProcessor;
import com.redpois0n.gscrot.ui.GlobalPopupMenu;

public class WatermarkPlugin extends Plugin {
	
	public static enum Mode {
		IMAGE,
		TEXT;
	}
	
	public static enum Position {
		TOPLEFT,
		TOPRIGHT,
		BOTTOMLEFT,
		BOTTOMRIGHT;
	}
	
	public static String string = "Watermark";
	public static Position position = Position.TOPLEFT;
	public static Mode mode = Mode.TEXT;
	public static Font font = new Font("Arial", Font.PLAIN, 14);
	public static Color foreground = Color.white;
	public static Color background = null;

	public WatermarkPlugin() {
		super("Watermark");
		ImageProcessor.addGraphicsProcessor(new WatermarkProcessor());
		
		JMenuItem item = new JMenuItem("Watermark Settings");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DialogSettings().setVisible(true);
			}
		});
		GlobalPopupMenu.SETTINGS_ITEMS.add(item);
		
		load();
	}
	
	public static void load() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(".watermark")));
			
			position = (Position) ois.readObject();
			mode = (Mode) ois.readObject();
			string = (String) ois.readObject();
			font = (Font) ois.readObject();
			foreground = (Color) ois.readObject();
			background = (Color) ois.readObject();
			
			ois.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void save() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(".watermark")));
			
			oos.writeObject(position);
			oos.writeObject(mode);
			oos.writeObject(string);
			oos.writeObject(font);
			oos.writeObject(foreground);
			oos.writeObject(background);
			
			oos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

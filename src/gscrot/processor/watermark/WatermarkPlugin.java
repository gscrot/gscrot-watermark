package gscrot.processor.watermark;

import gscrot.api.Plugin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import com.redpois0n.gscrot.ImageProcessor;
import com.redpois0n.gscrot.ui.GlobalPopupMenu;

public class WatermarkPlugin extends Plugin {
	
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
		
	}

	public static void save() {
		
	}

}

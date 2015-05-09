package gscrot.processor.watermark;

import gscrot.api.Plugin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import com.redpois0n.gscrot.ImageProcessor;
import com.redpois0n.gscrot.ui.GlobalPopupMenu;

public class WatermarkPlugin extends Plugin {

	public WatermarkPlugin() {
		super("Watermark");
		ImageProcessor.addProcessor(new WatermarkProcessor());
		
		JMenuItem item = new JMenuItem("Watermark Settings");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DialogSettings().setVisible(true);
			}
		});
		GlobalPopupMenu.SETTINGS_ITEMS.add(item);
	}

}

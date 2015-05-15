package gscrot.processor.watermark;

import gscrot.processor.watermark.WatermarkPlugin.Mode;
import gscrot.processor.watermark.WatermarkPlugin.Position;
import iconlib.IconUtils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.redpois0n.gscrot.GraphicsImageProcessor;

public class WatermarkProcessor extends GraphicsImageProcessor {

	public WatermarkProcessor() {
		super("Watermark", IconUtils.getIcon("watermark", WatermarkProcessor.class));
	}

	@Override
	public void process(Graphics2D g, int width, int height) {
		if (WatermarkPlugin.mode == Mode.TEXT) {
			g.setFont(WatermarkPlugin.font);
			g.setColor(WatermarkPlugin.foreground);
			
			String s = WatermarkPlugin.string;
			
			if (WatermarkPlugin.position == Position.TOPLEFT) {
				g.drawString(s, 10, 10 + g.getFontMetrics().getHeight());
			} else if (WatermarkPlugin.position == Position.TOPRIGHT) {
				g.drawString(s, width - g.getFontMetrics().stringWidth(s) - 10, 10);
			} else if (WatermarkPlugin.position == Position.BOTTOMLEFT) {
				g.drawString(s, 10, height - g.getFontMetrics().getHeight());
			} else if (WatermarkPlugin.position == Position.BOTTOMRIGHT) {
				g.drawString(s, width - g.getFontMetrics().stringWidth(s) - 10, height - g.getFontMetrics().getHeight());
			}
		} else if (WatermarkPlugin.mode == Mode.IMAGE) {
			try {
				File file = WatermarkPlugin.file;
				
				if (file != null) {
					BufferedImage image = ImageIO.read(file);
					
					if (WatermarkPlugin.position == Position.TOPLEFT) {
						g.drawImage(image, 10, 10, null);
					} else if (WatermarkPlugin.position == Position.TOPRIGHT) {
						g.drawImage(image, width - image.getWidth() - 10, 10, null);
					} else if (WatermarkPlugin.position == Position.BOTTOMLEFT) {
						g.drawImage(image, 10, height - image.getHeight() - 10, null);
					} else if (WatermarkPlugin.position == Position.BOTTOMRIGHT) {
						g.drawImage(image, width - image.getWidth() - 10, height - image.getHeight() - 10, null);
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}

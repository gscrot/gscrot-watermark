package gscrot.processor.watermark;

import iconlib.IconUtils;

import java.awt.Graphics2D;

import com.redpois0n.gscrot.GraphicsImageProcessor;

public class WatermarkProcessor extends GraphicsImageProcessor {

	public WatermarkProcessor() {
		super("Watermark", IconUtils.getIcon("watermark", WatermarkProcessor.class));
	}

	@Override
	public void process(Graphics2D g, int width, int height) {
		
	}

}

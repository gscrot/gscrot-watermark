package gscrot.processor.watermark;

import java.awt.Graphics2D;

import iconlib.IconUtils;

import com.redpois0n.gscrot.ImageProcessor;

public class WatermarkProcessor extends ImageProcessor {

	public WatermarkProcessor() {
		super("Watermark", IconUtils.getIcon("watermark", WatermarkProcessor.class));
	}

	@Override
	public void process(Graphics2D g) {
		
	}

}

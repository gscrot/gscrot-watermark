package gscrot.processor.watermark;

import com.redpois0n.gscrot.ImageProcessor;

import gscrot.api.Plugin;

public class WatermarkPlugin extends Plugin {

	public WatermarkPlugin() {
		super("Watermark");
		ImageProcessor.addProcessor(new WatermarkProcessor());
	}

}

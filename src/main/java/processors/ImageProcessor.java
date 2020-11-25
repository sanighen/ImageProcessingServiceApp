package processors;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.twelvemonkeys.image.ResampleOp;

import config.ConfigurationProvider;

public class ImageProcessor {

	private ConfigurationProvider cp;

	public ImageProcessor() {
	}

	public ImageProcessor(ConfigurationProvider cp) {
		this.cp = cp;
	}

	public void transform(String fileName) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(new File(cp.get("path.original") + fileName));

		BufferedImageOp resampler = new ResampleOp(300, 300, ResampleOp.FILTER_LANCZOS);

		BufferedImage output = resampler.filter(bufferedImage, null);

		ImageIO.write(output, "jpeg", new File(cp.get("path.processed") + fileName));
	}

	public void setConfigurationProvider(ConfigurationProvider cp) {
		this.cp = cp;
	}
	
	

}

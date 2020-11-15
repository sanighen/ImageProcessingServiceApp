package main;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import com.twelvemonkeys.image.ResampleOp;

public class Application {

	public static void main(String[] args) throws InterruptedException, IOException {

		File rootDirectory = new File("images/original");

		BufferedImage bufferedImage;
		BufferedImageOp resampler;
		BufferedImage output;

		boolean isDirectory = rootDirectory.exists() && rootDirectory.isDirectory();

		File[] lastFiles = {};
		
		if (isDirectory) {
			try {
				while (true) {
					File[] files = rootDirectory.listFiles();
					if (Arrays.compare(files, lastFiles) != 0) {
						System.out.println("FOUND FILES: ");
						for (File file : files) {
							if ((file.getName().endsWith(".jpg") || file.getName().endsWith(".jpeg"))
									&& file.isFile()) {
								System.out.println(file);
								bufferedImage = ImageIO.read(file);
								resampler = new ResampleOp(300, 300, ResampleOp.FILTER_LANCZOS);
								output = resampler.filter(bufferedImage, null);
								ImageIO.write(output, "jpeg", new File("images/processed/" + file.getName()));
							}
						}
					}
					lastFiles = files;
					Thread.sleep(500);
				}
			} catch (NullPointerException e) {
				System.err.println("This directory no longer exists.");
			}
		} else {
			System.err.println("No such directory");
		}

//		BufferedImage bufferedImage = ImageIO.read(new File("images/original/2-7.jpg"));
//		
//		BufferedImageOp resampler = new ResampleOp(300, 300, ResampleOp.FILTER_LANCZOS);
//
//	    BufferedImage output = resampler.filter(bufferedImage, null);
//		
//		ImageIO.write(output, "jpeg", new File("images/processed/2-7.jpg"));

	}

}

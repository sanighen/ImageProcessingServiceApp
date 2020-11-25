package main;

import java.io.IOException;

import behavior.DirectoryObserver;
import config.ConfigurationProvider;
import processors.ImageProcessor;

public class Application {

	public static void main(String[] args) throws InterruptedException, IOException {

		System.out.println("STARTING");

		DirectoryObserver observer = new DirectoryObserver(new ConfigurationProvider());

		observer.addFileProcessor(".jpg", new ImageProcessor());

		observer.observe();

//		ImageProcessor imageProcessor = new ImageProcessor(new ConfigurationProvider());
//		imageProcessor.transform("Colors.jpg");

		System.out.println("FINISHED");

	}
}

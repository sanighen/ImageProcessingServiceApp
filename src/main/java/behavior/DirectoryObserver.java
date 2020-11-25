package behavior;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import config.ConfigurationProvider;
import processors.ImageProcessor;

public class DirectoryObserver {

	private ConfigurationProvider cp;

	private HashMap<String, ImageProcessor> processors;

	public DirectoryObserver(ConfigurationProvider cp) {
		this.cp = cp;
		processors = new HashMap<>();
	}

	public void addFileProcessor(String extension, ImageProcessor ip) {
		ip.setConfigurationProvider(cp);
		processors.put(extension, ip);

	}

	public void observe() throws InterruptedException, IOException {

		File rootDirectory = new File(cp.get("path.original"));

		File[] lastFiles = {};

		if (isDirectory(rootDirectory)) {
			try {
				while (true) {
					File[] files = rootDirectory.listFiles();
					if (Arrays.compare(files, lastFiles) != 0) {
						//System.out.println("FOUND FILES: ");
						for (File file : files) {
							if (file.isFile()
									&& (file.getName().endsWith(".jpg") || file.getName().endsWith(".jpeg"))) {
								//System.out.println(file);
								processors.get(".jpg").transform(file.getName());
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
	}

	public boolean isDirectory(File file) {
		if (file.exists() && file.isDirectory())
			return true;
		return false;
	}

}

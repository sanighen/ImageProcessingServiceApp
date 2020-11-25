package config;

public class ConfigurationProvider {

	public String get(String key) {
		if (key.equals("path.original"))
			return "images/original/";
		else if (key.equals("path.processed"))
			return "images/processed/";
		return null;
	}

}

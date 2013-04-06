package samples.with.exception;

import java.beans.ConstructorProperties;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class Resource2 {

	private final URL url;
	private final File file;
	private String mimeType;

	@ConstructorProperties({ "urlString", "filepath" })
	public Resource2(String urlString, String filepath) throws MalformedURLException, FileNotFoundException {
		url = new URL(urlString);
		file = new File(filepath);
		if (file.exists() == false) {
			throw new FileNotFoundException(filepath);
		}
	}

	public URL getUrl() {
		return url;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

}

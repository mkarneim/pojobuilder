package testdata.exception;

import net.karneim.pojobuilder.GeneratePojoBuilder;

import java.beans.ConstructorProperties;
import java.net.MalformedURLException;
import java.net.URL;

@GeneratePojoBuilder
public class Resource {
	private final URL url;
	private String mimeType;

	@ConstructorProperties({ "urlString" })
	public Resource(String urlString) throws MalformedURLException {
		url = new URL(urlString);
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

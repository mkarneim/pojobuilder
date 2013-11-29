package samples.with.exception;

import java.beans.ConstructorProperties;
import java.net.MalformedURLException;
import java.net.URL;

import net.karneim.pojobuilder.GeneratePojoBuilder;

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

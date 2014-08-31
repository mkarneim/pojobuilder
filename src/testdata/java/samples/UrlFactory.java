package samples;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLStreamHandler;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class UrlFactory {

  @GeneratePojoBuilder(withName="UrlBuilder", intoPackage = "samples")
  public static URL createUrl(String protocol, String host, int port, String file, URLStreamHandler handler)
      throws MalformedURLException {
    return new URL(protocol, host, port, file, handler);
  }
}

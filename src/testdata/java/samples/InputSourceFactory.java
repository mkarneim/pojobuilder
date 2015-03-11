package samples;

import java.io.InputStream;

import net.karneim.pojobuilder.GeneratePojoBuilder;

import org.xml.sax.InputSource;

public class InputSourceFactory {
  @GeneratePojoBuilder(intoPackage = "samples", includeProperties = {"byteStream", "encoding"})
  public static InputSource createInputSource(InputStream byteStream) {
    return new InputSource(byteStream);
  }
}

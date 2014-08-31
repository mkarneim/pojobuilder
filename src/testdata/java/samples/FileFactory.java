package samples;

import java.io.File;

import net.karneim.pojobuilder.FactoryProperties;
import net.karneim.pojobuilder.GeneratePojoBuilder;

public class FileFactory {

  @GeneratePojoBuilder(intoPackage = "samples")
  @FactoryProperties({"path"})
  public static File createFile(String arg1) {
    return new File(arg1);
  }
}

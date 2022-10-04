package net.karneim.pojobuilder.testenv;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.nio.file.Files;

public class Util {

  public static File createTempDir() {
    try {
      File tempFile = Files.createTempDirectory("temp" + "project").toFile();
      if (!tempFile.exists()) {
        throw new IllegalStateException(String.format("Directory '%s' could not be created.", tempFile));
      }
      return tempFile;
    } catch (IOException e) {
      throw new UndeclaredThrowableException(e);
    }
  }

  public static boolean deleteDir(File dir) {
    if (dir.isDirectory()) {
      String[] children = dir.list();
      for (int i = 0; i < children.length; i++) {
        boolean success = deleteDir(new File(dir, children[i]));
        if (!success) {
          return false;
        }
      }
    }

    return dir.delete();
  }

}

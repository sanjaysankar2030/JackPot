package core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public interface ModelReadInterface{

  String readFileBytes(String path) throws IOException ;
  String readFileJson(String path) throws IOException;
}

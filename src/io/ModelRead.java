package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ModelRead {
  public String readFileBytes(String path) throws IOException {
    byte[] data = Files.readAllBytes(Paths.get(path));
    String text = new String(data, java.nio.charset.StandardCharsets.UTF_8);
    return text;
  }
}

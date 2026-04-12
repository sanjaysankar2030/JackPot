package io;

import core.ModelReadInterface;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ModelRead implements ModelReadInterface {
  public String readFileBytes(String path) throws IOException {
    byte[] data = Files.readAllBytes(Paths.get(path));
    String text = new String(data, java.nio.charset.StandardCharsets.UTF_8);
    return text;
  }

  public String readFileJson(String path) throws IOException {
    return "String";
  }
}

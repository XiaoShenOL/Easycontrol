/*
 * 本项目大量借鉴学习了开源投屏软件：Scrcpy，在此对该项目表示感谢
 */
package top.saymzx.easycontrol.server.helper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public final class Command {
  private Command() {
    // not instantiable
  }

  public static void exec(String... cmd) throws IOException, InterruptedException {
    Process process = Runtime.getRuntime().exec(cmd);
    int exitCode = process.waitFor();
    if (exitCode != 0) {
      throw new IOException("Command " + Arrays.toString(cmd) + " returned with value " + exitCode);
    }
  }

  public static String execReadLine(String... cmd) throws IOException, InterruptedException {
    String result = null;
    Process process = Runtime.getRuntime().exec(cmd);
    Scanner scanner = new Scanner(process.getInputStream());
    if (scanner.hasNextLine()) {
      result = scanner.nextLine();
    }
    int exitCode = process.waitFor();
    if (exitCode != 0) {
      throw new IOException("Command " + Arrays.toString(cmd) + " returned with value " + exitCode);
    }
    return result;
  }

  public static String execReadOutput(String... cmd) throws IOException, InterruptedException {
    Process process = Runtime.getRuntime().exec(cmd);
    StringBuilder builder = new StringBuilder();
    Scanner scanner = new Scanner(process.getInputStream());
    while (scanner.hasNextLine()) {
      builder.append(scanner.nextLine()).append('\n');
    }
    int exitCode = process.waitFor();
    if (exitCode != 0) {
      throw new IOException("Command " + Arrays.toString(cmd) + " returned with value " + exitCode);
    }
    return builder.toString();
  }
}

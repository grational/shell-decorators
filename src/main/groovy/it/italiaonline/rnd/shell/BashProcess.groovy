package it.italiaonline.rnd.shell

/**
 * This class execute a shell process using bash
 */
class BashProcess implements ShellProcess {

  private final String  command
  private final Integer timeout

  /**
   * Secondary Constructor
   * <p>
   * @param cmd  a String containing the pipeline to execute
   */
  BashProcess(String cmd) {
    this (
      cmd,
      30 // default timeout
    )
  }

  /**
   * Primary Constructor
   * <p>
   * @param cmd  a String containing the pipeline to execute
   * @param timeout  the process can last at most timeout
   */
  BashProcess(String cmd, Integer timeout) {
    this.command = cmd
    this.timeout = timeout
  }

  /**
   * This method returns the output of the command.
   * The execution can last at most {@code timeout} seconds
   * <p>
   * @return a String containing the output of the last command in the pipeline
   */
  String output() {
    [ 'timeout', "${this.timeout}", 'bash', '-c', command ].execute().text.trim()
  }
}

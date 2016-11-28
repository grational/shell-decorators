package it.italiaonline.rnd.shell

import spock.lang.Specification

/**
 * This class test the BashProcess wrapper
 */
class BashProcessSpec extends Specification {

  /**
   * A test with a single (non pipelined) echo command
   */
  def "Verify single command output"() {
    setup:
      String singleCmd = /echo 'hello world'/
      ShellProcess echo = new BashProcess(singleCmd)
    when:
      String result = echo.output()
    then:
      result == 'hello world'
  }

  /**
   * A test with a pipeline with 'echo' and 'tr'
   */
  def "Pipeline output"() {
    setup:
      String multipleCmds = /echo 'hello world' | tr ' ' '_'/
      ShellProcess pipeline = new BashProcess(multipleCmds)
    when:
      String result = pipeline.output()
    then:
      result == 'hello_world'
  }
}

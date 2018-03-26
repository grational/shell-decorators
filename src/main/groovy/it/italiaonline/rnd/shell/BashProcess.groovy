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
	 * without space or newlines
	 */
	String output() {
		[ 'timeout', "${this.timeout}", 'bash', '-c', command ].execute().text.trim()
	}

	/**
	 * This method allows to follow the output and error stream of the command
	 * The execution can last at most {@code timeout} seconds
	 * <p>
	 * @param stdout An OutputStream to capture the stdout of the command (default: System.out)
	 * @param stderr An OutputStream to capture the stderr of the command (default: System.err)
	 */
	void stream(
		OutputStream stdout = System.out,
		OutputStream srderr = System.err
	) {
		[ 'timeout', "${this.timeout}", 'bash', '-c', command ]
		.execute()
		.waitForProcessOutput(stdout, srderr)
	}
}

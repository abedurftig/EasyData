package org.easydata;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CmdUtil {

	public static final Options PROGRAM_OPTIONS = buildOptions(); 
	
	public static final String ARG_INPUT = "in";
	public static final String ARG_OUTPUT = "out";
	public static final String ARG_CSV = "csv";
	public static final String ARG_JSON = "json";
	public static final String ARG_HELP = "help";
	
	private static CommandLine CMD = null; 
	
	public static CommandLine init(String[] args) throws ParseException {
		
		CommandLineParser parser = new DefaultParser();
		CMD = parser.parse(PROGRAM_OPTIONS, args);
		
		return CMD;
		
	}
	
	public static boolean has(String arg) {
		return CMD.hasOption(arg);
	}
	
	public static String withDefault(String arg, String def) {
		return CMD.getOptionValue(arg, def);
	}
	
	private static Options buildOptions() {
		
		Options options = new Options();
		
		Option csvOption = new Option(ARG_CSV, false, "read CSV files and create JSON configuration files"); 
		Option jsonOption = new Option(ARG_JSON, false, "read JSON files and generate Java classes");
		Option helpOption = new Option(ARG_HELP, false, "print quick help");
		
		OptionGroup firstArg = new OptionGroup();
		firstArg.addOption(csvOption);
		firstArg.addOption(jsonOption);
		firstArg.addOption(helpOption);
		firstArg.setRequired(true);
		
		options.addOptionGroup(firstArg);
		
		Option inputOption = new Option(ARG_INPUT, true, "the path to the folder which contains the input files");
		Option outputOption = new Option(ARG_OUTPUT, true, "the path to the folder which contains the input files");
		
		options.addOption(inputOption);
		options.addOption(outputOption);
		
		return options;
		
	}
	
}

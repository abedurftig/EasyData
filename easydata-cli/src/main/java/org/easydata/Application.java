package org.easydata;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;
import org.easydata.config.CSVModelDefinitionReader;
import org.easydata.config.JSONModelDefinitionReader;
import org.easydata.config.JSONModelDefinitionWriter;
import org.easydata.generator.EasyCodeGenerator;
import org.easydata.generator.EasyPojoGenerator;
import org.easydata.generator.EasyRepositoryGenerator;
import org.easydata.model.EasyModel;
import org.easydata.util.EasyLogger;

import com.sun.codemodel.JCodeModel;

public class Application {

	public static void main(String[] args) {
		
		try {
			CmdUtil.init(args);
		} catch (ParseException e) {
			EasyLogger.getEasyLogger().error("Sorry, we were not able to read the arguments.");
			EasyLogger.getEasyLogger().error(e.getMessage());
			System.exit(-1);
		}
		
		if (CmdUtil.has(CmdUtil.ARG_CSV)) {
			
			String defaultInput = Paths.get("csv").toAbsolutePath().toString();
			String defaultOutput = Paths.get("json").toAbsolutePath().toString();
			
			try {
				generateJSONFromCSV(
						CmdUtil.withDefault(CmdUtil.ARG_INPUT, defaultInput),
						CmdUtil.withDefault(CmdUtil.ARG_OUTPUT, defaultOutput)
					);
			} catch (IllegalArgumentException | IOException e) {
				EasyLogger.getEasyLogger().error("Failed with message: " + e.getMessage());
				System.exit(-1);
			}
			
			
		} else if (CmdUtil.has(CmdUtil.ARG_JSON)) {
			
			String defaultInput = Paths.get("json").toAbsolutePath().toString();
			String defaultOutput = Paths.get("gen").toAbsolutePath().toString();
			
			try {
				generateJavaFromJSON(
						CmdUtil.withDefault(CmdUtil.ARG_INPUT, defaultInput),
						CmdUtil.withDefault(CmdUtil.ARG_OUTPUT, defaultOutput)
					);
			} catch (IllegalArgumentException | IOException e) {
				EasyLogger.getEasyLogger().error("Failed with message: " + e.getMessage());
				System.exit(-1);
			}
			
		} else if (CmdUtil.has(CmdUtil.ARG_HELP)) {
			
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("java -jar EasyData-Runnable", CmdUtil.PROGRAM_OPTIONS);
			
		}
		
	}
	
	private static void generateJSONFromCSV(String in, String out) throws IllegalArgumentException, IOException {
		
		EasyLogger.getEasyLogger().info("Searching for CSV in " + in);
		EasyLogger.getEasyLogger().info("Writing JSON to " + out);
		
		CSVModelDefinitionReader reader = new CSVModelDefinitionReader();
		EasyModel em = reader.readAndParseModelDefinitions(new File(in));
		
		File outFile = new File(out);
		if (!outFile.exists()) {
			outFile.mkdir();
		}
		
		JSONModelDefinitionWriter writer = new JSONModelDefinitionWriter();
		writer.writeOutModelDefinition(em, outFile);
		
	}
	
	private static void generateJavaFromJSON(String in, String out) throws IllegalArgumentException, IOException {
		
		EasyLogger.getEasyLogger().info("Searching for JSON in " + in);
		EasyLogger.getEasyLogger().info("Writing Java to " + out);
		
		JSONModelDefinitionReader reader = new JSONModelDefinitionReader();
		EasyModel em = reader.readAndParseModelDefinitions(new File(in));
		
		File outFile = new File(out);
		if (!outFile.exists()) {
			outFile.mkdir();
		}
		
		JCodeModel jModel = new JCodeModel();
		
		EasyCodeGenerator genModel = new EasyPojoGenerator();
		genModel.generateCode(em, jModel);
		
		EasyCodeGenerator genRepo = new EasyRepositoryGenerator();
		genRepo.generateCode(em, jModel);
		
		jModel.build(outFile);
		
	}
	
}

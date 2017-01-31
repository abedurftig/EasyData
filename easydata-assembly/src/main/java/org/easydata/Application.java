package org.easydata;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.easydata.config.CSVModelDefinitionReader;
import org.easydata.config.JSONModelDefinitionReader;
import org.easydata.config.JSONModelDefinitionWriter;
import org.easydata.generator.EasyCodeGenerator;
import org.easydata.generator.EasyPojoGenerator;
import org.easydata.generator.EasyRepositoryGenerator;
import org.easydata.model.EasyModel;

import com.sun.codemodel.JCodeModel;

public class Application {

	public static void main(String[] args) {
		
		String firstArg = null;
		String secondArg = null;
		
		if (args.length == 0) {
			
			System.err.println("No argument provided!");
			System.exit(-1);
			
		}
		
		if (args.length >= 1) {
			
			firstArg = args[0];
			if (!firstArg.equals("csv") &&
				!firstArg.equals("json") &&
				!firstArg.equals("generate")) {
				
				System.err.println("The first arg should be one of {'csv', 'json', 'generate'}!");
				System.exit(-1);
				
			} else {
				System.out.println("Running with first arg '" + firstArg + "'.");
			}
			
			if (args.length == 2) {
				secondArg = args[1].split("=")[1];
			} else {
				secondArg = Paths.get("").toAbsolutePath().toString();
			}
			
			System.out.println("Running with second arg '" + secondArg + "'.");
			
		}
		
		if (firstArg.equals("csv")) {
			try {
				generateJSONFromCSV(new File(secondArg));
			} catch (IllegalArgumentException | IOException e) {
				System.err.println("Failed with message: " + e.getMessage());
				System.exit(-1);
			}
		}
		
		if (firstArg.equals("json")) {
			try {
				generateJavaFromJSON(new File(secondArg));
			} catch (IllegalArgumentException | IOException e) {
				System.err.println("Failed with message: " + e.getMessage());
				System.exit(-1);
			}
		}
		
	}
	
	private static void generateJSONFromCSV(File path) throws IllegalArgumentException, IOException {
		
		String inputPath = path.getAbsolutePath() + File.separator + "csv";
		String outputPath = path.getAbsolutePath() + File.separator + "json";
		System.out.println("Searching for CSV in " + inputPath);
		System.out.println("Writing JSON to " + outputPath);
		
		CSVModelDefinitionReader reader = new CSVModelDefinitionReader();
		EasyModel em = reader.readAndParseModelDefinitions(new File(inputPath));
		
		File out = new File(outputPath);
		if (!out.exists()) {
			out.mkdir();
		}
		
		JSONModelDefinitionWriter writer = new JSONModelDefinitionWriter();
		writer.writeOutModelDefinition(em, out);
		
	}
	
	private static void generateJavaFromJSON(File path) throws IllegalArgumentException, IOException {
		
		String inputPath = path.getAbsolutePath() + File.separator + "json";
		String outputPath = path.getAbsolutePath() + File.separator + "gen";
		System.out.println("Searching for JSON in " + inputPath);
		System.out.println("Writing Java to " + outputPath);
		
		JSONModelDefinitionReader reader = new JSONModelDefinitionReader();
		EasyModel em = reader.readAndParseModelDefinitions(new File(inputPath));
		
		File out = new File(outputPath);
		if (!out.exists()) {
			out.mkdir();
		}
		
		JCodeModel jModel = new JCodeModel();
		
		EasyCodeGenerator genModel = new EasyPojoGenerator();
		genModel.generateCode(em, jModel);
		
		EasyCodeGenerator genRepo = new EasyRepositoryGenerator();
		genRepo.generateCode(em, jModel);
		
		jModel.build(out);
		
	}
	
}

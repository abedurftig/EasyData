package org.easydata.generator;

import org.easydata.model.EasyModel;

import com.sun.codemodel.JCodeModel;

public abstract class EasyCodeGenerator {

	public abstract void generateCode(EasyModel easyModel, JCodeModel codeModel);
	
}

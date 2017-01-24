package org.easydata.generator;

import org.easydata.model.EasyClass;
import org.easydata.model.EasyField;
import org.easydata.model.EasyModel;
import org.easydata.model.EasyPojo;

import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;

public class EasyPojoGenerator extends EasyCodeGenerator {

	private static final String _MODEL_PACKAGE_NAME = "your.domain.data";
	private JCodeModel _cm = null;
	
	@Override
	public void generateCode(EasyModel model, JCodeModel codeModel) {

		this._cm = codeModel;
		JPackage pkg = codeModel._package(_MODEL_PACKAGE_NAME);
		
		try {
			
			for (EasyClass clazz : model.classes) {
				addClass(clazz, pkg);
			}
			
		} catch (JClassAlreadyExistsException jcae) {}
		
	}
	
	private void addClass(EasyClass clazz, JPackage pkg) throws JClassAlreadyExistsException {
		
		JDefinedClass jc = pkg._class(clazz.targetClassName);
		jc._extends(EasyPojo.class);
		
		// add key field first 
		EasyField key = clazz.getKeyField();
		JFieldVar keyVar = null;
		if (key != null) {
			keyVar = addFieldToClass(key, jc);
		}
		
		// add the other fields after
		for (EasyField field : clazz.fields) {
			if (!field.key) {
				addFieldToClass(field, jc);
			}
		}
		
		// interface methods
		JMethod keyValue = jc.method(JMod.PUBLIC, String.class, "getKeyValue");
        
		if (keyVar != null) {
			keyValue.body()._return(JExpr._this().ref(keyVar));
		} else {
			keyValue.body()._return(JExpr._null());
		}
		
	}
	
	private JFieldVar addFieldToClass(EasyField field, JDefinedClass jc) {
		
		boolean withGetter = true;
		boolean withSetter = true;
		
		JFieldVar jField = jc.field(JMod.PRIVATE, field.getJavaType(), field.targetFieldName);

        if (withGetter) {
        	
        	JMethod getter = jc.method(JMod.PUBLIC, jField.type(), "get" + jField.name());
            getter.body()._return(jField);
            getter.javadoc().add("Returns the " + jField.name() + ".");
            getter.javadoc().addReturn().add(jField.name());
            
        }

        if (withSetter) {
        
            JMethod setter = jc.method(JMod.PUBLIC, _cm.VOID, "set" + jField.name());
            setter.param(jField.type(), jField.name());
            setter.body().assign(JExpr._this().ref(jField.name()), JExpr.ref(jField.name()));
            setter.javadoc().add("Set the " + jField.name() + ".");
            setter.javadoc().addParam(jField.name()).add("the new " + jField.name());
        	
        }
        
        return jField;
		
	}

}

package org.easydata.generator;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.easydata.model.EasyCSVRepository;
import org.easydata.model.EasyClass;
import org.easydata.model.EasyField;
import org.easydata.model.EasyModel;
import org.easydata.model.EasyRepository;
import org.easydata.model.EasyType;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;
import com.sun.codemodel.JWhileLoop;

public class EasyRepositoryGenerator extends EasyCodeGenerator {

	private static final String _MODEL_PACKAGE_NAME = "your.domain.data";
	
	private static final Comparator<EasyField> _FIELD_INDEX_COMP = new Comparator<EasyField>() {
    	public int compare(EasyField one, EasyField two) {
    		return one.index - two.index;
    	}
    };
	
	private JCodeModel _cm = null;
	private EasyModel _em = null;
	
	@Override
	public void generateCode(EasyModel model, JCodeModel codeModel) {

		this._cm = codeModel;
		this._em = model;
		
		JPackage pkg = codeModel._package(_MODEL_PACKAGE_NAME);
		
		try {
			
			for (EasyClass clazz : model.classes) {
				addClass(clazz, pkg);
			}
			
			addRepositoriesClass();
			
		} catch (JClassAlreadyExistsException jcae) {}
		
	}
	
	private void addClass(EasyClass clazz, JPackage pkg) throws JClassAlreadyExistsException {

		String name = clazz.targetClassName + getPluralSuffix(clazz);
		
		JDefinedClass jc = pkg._class(name);
		
		JClass superClass = this._cm.ref(EasyCSVRepository.class).narrow(this._cm.ref(clazz.targetClassName));
		
		jc._extends(superClass);
		
		JType jtype = this._cm.ref(it.unimi.dsi.fastutil.objects.Object2ObjectMap.class).narrow(
				this._cm.ref(clazz.getKeyField().getJavaTypeAsString()),
				this._cm.ref(clazz.targetClassName)
		);
		
		JClass jtypeImpl = this._cm.ref(it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap.class).narrow(
				this._cm.ref(clazz.getKeyField().getJavaTypeAsString()),
				this._cm.ref(clazz.targetClassName)
		);
		
		// map holding the single items
		JFieldVar jField = jc.field(JMod.PRIVATE, jtype, name);
		jField.init(JExpr._new(jtypeImpl));
		
		// createFrom method
		addCreateFromMethod(clazz, jc);
		
		// add method
		addAddMethod(clazz, jc, jField);
		
		// getById method
		addGetByIdMethod(clazz, jc, jField);
		
		// getInputFileName method
		addGetInputFileNameMethod(clazz, jc);
		
		addSizeMethod(clazz, jc, jField);
		
	}
	
	private void addCreateFromMethod(EasyClass clazz, JDefinedClass jc) {
		
		JMethod createFrom = jc.method(JMod.PUBLIC, this._cm.ref(clazz.targetClassName), "createFrom");
		createFrom.param(CSVRecord.class, "record");
		createFrom._throws(java.text.ParseException.class);
		
		JVar dateFormat = null;
        
        if (clazz.hasDateField()) {
        	 
        	dateFormat = createFrom.body().decl(this._cm.ref(SimpleDateFormat.class), "sdf");
			dateFormat.init(JExpr._new(this._cm.ref(SimpleDateFormat.class)).arg("dd/MM/yy"));	
        	
        }
        
        JClass createFromResultType = this._cm.ref(clazz.targetClassName);
        JVar createFromResult = createFrom.body().decl(createFromResultType, clazz.targetClassName.toLowerCase());
        createFromResult.init(JExpr._new(this._cm.ref(clazz.targetClassName)));
        
        List<EasyField> fields = new ArrayList<EasyField>(clazz.fields);
        Collections.sort(fields, _FIELD_INDEX_COMP);
        
		for (EasyField field : fields) {
		
			JExpression p2 = JExpr.direct("record.get(" + field.index + ")");
			
			if (field.type.equals(EasyType.TEXT.getType())) {
				createFrom.body().add(createFromResult.invoke("set" + field.targetFieldName).arg(p2));
			} else if (field.type.equals(EasyType.DATE.getType())) {
				createFrom.body().add(
						createFromResult.invoke("set" + field.targetFieldName).arg(
								dateFormat.invoke("parse").arg(p2)
							));
			} else if (field.type.equals(EasyType.INTEGER.getType())) {
				createFrom.body().add(
						createFromResult.invoke("set" + field.targetFieldName).arg(
								this._cm.ref(Integer.class).staticInvoke("valueOf").arg(p2)
							));
			}
			
		}
		
		
		createFrom.body()._return(createFromResult);
		
	}
	
	private void addAddMethod(EasyClass clazz, JDefinedClass jc, JFieldVar map) {
		
		JMethod add = jc.method(JMod.PUBLIC, this._cm.BOOLEAN, "add");
		add._throws(IllegalArgumentException.class);
		JVar param = add.param(this._cm.ref(clazz.targetClassName), "item");
		
		add.body()
			._if(param.eq(JExpr._null()))
			._then()
			._throw(JExpr._new(this._cm.ref(IllegalArgumentException.class)).arg("item cannot be null!"));
		
		add.body()
			._if(param.invoke("getKeyValue").eq(JExpr._null()).cor(param.invoke("getKeyValue").invoke("isEmpty")))
			._then()
			._throw(JExpr._new(this._cm.ref(IllegalArgumentException.class)).arg("item key value cannot be null or empty!"));
		
		add.body()
			._if(map.invoke("containsKey").arg(param.invoke("getKeyValue")).not())
			._then()
				.add(map.invoke("put").arg(param.invoke("getKeyValue")).arg(param))
				._return(JExpr.TRUE);
					
		add.body()._return(JExpr.FALSE);
		
	}
	
	private void addGetByIdMethod(EasyClass clazz, JDefinedClass jc, JFieldVar map) {
		
		JMethod getById = jc.method(JMod.PUBLIC, this._cm.ref(clazz.targetClassName), "getById");
		JVar param = getById.param(String.class, "id");
		getById.body()._return(JExpr._this().ref(map).invoke("get").arg(param));
		
	}
	
	private void addGetInputFileNameMethod(EasyClass clazz, JDefinedClass jc) {
		
		JMethod getInputFileName = jc.method(JMod.PUBLIC, String.class, "getInputFileName");
		getInputFileName.body()._return(JExpr.lit(clazz.fileName));
		
	}
	
	private void addSizeMethod(EasyClass clazz, JDefinedClass jc, JFieldVar map) {
		
		JMethod size = jc.method(JMod.PUBLIC, this._cm.INT, "size");
		size.body()._return(map.invoke("size"));
		
	}
	
	private void addRepositoriesClass() throws JClassAlreadyExistsException {
		
		JDefinedClass repos = this._cm._package(_MODEL_PACKAGE_NAME)._class("Repositories");
		
		JType jtype = this._cm.ref(java.util.Map.class).narrow(
				this._cm.ref(Class.class).narrow(this._cm.wildcard()),
				this._cm.ref(EasyRepository.class).narrow(this._cm.wildcard())
		);
		
		JClass jtypeImpl = this._cm.ref(java.util.HashMap.class).narrow(
				this._cm.ref(Class.class).narrow(this._cm.wildcard()),
				this._cm.ref(EasyRepository.class).narrow(this._cm.wildcard())
		);
		
		JFieldVar jField = repos.field(JMod.PRIVATE | JMod.STATIC, jtype, "REPOS");
		jField.init(JExpr._new(jtypeImpl));
		
		// init method
		JMethod init = repos.method(JMod.PUBLIC | JMod.STATIC, this._cm.VOID, "init");
		JVar param = init.param(String.class, "path");
		init._throws(IOException.class);
		
		for (EasyClass clazz : this._em.classes) {
			
			String name = clazz.targetClassName + getPluralSuffix(clazz);
			init.body().add(jField.invoke("put")
					.arg(JExpr.dotclass(this._cm.ref(name)))
					.arg(JExpr._new(this._cm.ref(name))));
			
		}
		
		JClass repo = this._cm.ref(Iterator.class).narrow(this._cm.ref(EasyRepository.class).narrow(this._cm.wildcard()));;
		init.body().decl(repo, "iter", JExpr.ref("REPOS").invoke("values").invoke("iterator"));
		
		JWhileLoop loop = init.body()._while(JExpr.ref("iter").invoke("hasNext"));
		JVar repoVar = loop.body().decl(this._cm.ref(EasyRepository.class).narrow(this._cm.wildcard()), "repo", JExpr.ref("iter").invoke("next"));
		loop.body().add(repoVar.invoke("init").arg(param));
		
		// get method
		JType genericType = this._cm.directClass("T");
		JMethod get = repos.method(JMod.PUBLIC | JMod.STATIC, genericType, "get");
		
		JClass genericClass = this._cm.ref(EasyRepository.class).narrow(this._cm.wildcard());
		
		get.generify("T", genericClass);
        JClass parameterType = this._cm.ref(Class.class).narrow(genericType);
        JVar getParam = get.param(parameterType, "type");
        get.body()._return(getParam.invoke("cast").arg(JExpr.ref("REPOS").invoke("get").arg(getParam)));
		
	}
	
	private String getPluralSuffix(EasyClass clazz) {
		String suffix = "s";
		if (clazz.targetClassName.endsWith("s")) {
			suffix = "es";
		}
		return suffix;
	}

}

package org.wk.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class RhinoService {
	
	
	public static final String JS_FUNCTION_NAME = "sayHelloWorld";
	
	
	public Object runJsFile(File jsFile, String jsFunctionName, Object[] args) {
		
		Object result = null;
		
		try {
			
			ScriptEngineManager engineMgr = new ScriptEngineManager();
			ScriptEngine jsEngine = engineMgr.getEngineByName("ECMAScript");
			Invocable invocableEngine = (Invocable)jsEngine;
			
			Reader reader = new InputStreamReader(new FileInputStream(jsFile));
			
			jsEngine.eval(reader);
			result = invocableEngine.invokeFunction(jsFunctionName, args);
			
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

}

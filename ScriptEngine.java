package SurushLibs;

import java.util.ArrayList;
import java.util.Arrays;

public class ScriptEngine {

	private String code = "";
	private onRunFunction orf = null;
	private ArrayList<String> customFunc = new ArrayList<>();
	private ArrayList<String> baseFunc = new ArrayList<>();
	private String result = "";
	private int index = 0;
	private String lastCode = "";

	public ScriptEngine() {
		baseFunc.add("fun1()");
		baseFunc.add("fun2()");
		baseFunc.add("fun3()");
		baseFunc.add("fun4()");

	}

	public String getLastExecutionScript() {
		return code;
	}

	public void setOnRunListener(onRunFunction r) {
		orf = r;
	}

	public ScriptEngine execute(String script) {
		code = script.toLowerCase();
		lastCode = script;
		result = "Script executed:";
		ArrayList<String> func = new ArrayList<>();
		if (code.contains("runfun1()")) {
			index++;
			result = result + "\n" + "[" + String.valueOf(index) + "]: Runed function: Fun1()";
			if (orf != null) {
				func.add("Fun1()");
				orf.onRunBaseFunction(toSArray(func));
			}
		}
		if (code.contains("runfun2()")) {
			index++;
			result = result + "\n" + "[" + String.valueOf(index) + "]: Runed function: Fun2()";
			if (orf != null) {
				func.add("Fun2()");
				orf.onRunBaseFunction(toSArray(func));
			}
		}
		if (code.contains("runfun3()")) {
			index++;
			result = result + "\n" + "[" + String.valueOf(index) + "]: Runed function: Fun3()";
			if (orf != null) {
				func.add("Fun3()");
				orf.onRunBaseFunction(toSArray(func));
			}
		}
		if (code.contains("runfun4()")) {
			index++;
			result = result + "\n" + "[" + String.valueOf(index) + "]: Runed function: Fun4()";
			if (orf != null) {
				func.add("Fun4()");
				orf.onRunBaseFunction(toSArray(func));
			}
		}

		ArrayList<String> rf = new ArrayList<>();
		for (int i = 0; i < customFunc.size(); i++) {
			if (code.contains(customFunc.get(i).toLowerCase())) {
				rf.add(customFunc.get(i));
			}
		}
		if (orf != null && rf.size() > 0) {
			orf.onRunCustomFunction(toSArray(rf));
			index++;
			result = "Script executed:\n[" + String.valueOf(index) + "]:Runned custom functions:"
					+ Arrays.toString(toSArray(rf));
		}

		if (!code.contains("runfun1()") && !code.contains("runfun2()") && !code.contains("runfun3()")
				&& !code.contains("runfun4()") && rf.size() == 0) {
			result = "Script executed: null";
		}

		return this;
	}

	public ScriptEngine callCustomFunction(String name) {
		if (orf != null) {
			this.orf.onRunCustomFunction(new String[] { name });
		}
		index++;
		result = "Script executed:\n [" + String.valueOf(index) + "]:Called custom function:" + name;
		return this;
	}

	public ScriptEngine callCustomFunctions(String... names) {
		if (orf != null) {
			this.orf.onRunCustomFunction(names);
		}
		index++;
		result = "Script executed:\n [" + String.valueOf(index) + "]:Called custom functions:" + Arrays.toString(names);

		return this;
	}

	private String[] toSArray(ArrayList<String> a) {
		String[] r = new String[a.size()];
		for (int i = 0; i < a.size(); i++) {
			r[i] = customFunc.get(i);
		}
		return r;
	}

	public String getLogResult() {
		return result;
	}

	public String customLog(ReturnString s) {
		result = s.Return();
		return s.Return();
	}

	public String lastLog() {
		return result;
	}

	public String lastCode() {
		return lastCode;
	}

	public ScriptEngine setCustomFunctions(ArrayList<String> str) {

		customFunc = str;

		return this;
	}

	public interface onRunFunction {
		public void onRunBaseFunction(String[] names);

		public void onRunCustomFunction(String[] names);
	}
}

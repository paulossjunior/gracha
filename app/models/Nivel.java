package models;

import java.util.LinkedHashMap;
import java.util.Map;

public enum Nivel {

	BASICO, AVANCADO, NONE;
	
	public static Map<String, String> fNivel() {
		LinkedHashMap<String, String> vals = new LinkedHashMap<String, String>();

		
		vals.put(BASICO.toString(), BASICO.toString());
		vals.put(AVANCADO.toString(), AVANCADO.toString());
		vals.put(NONE.toString(), NONE.toString());
		
		return vals;
	}
	
}

package models;

import java.util.LinkedHashMap;
import java.util.Map;

public enum TipoRelacao {

	ENTRADA, SAIDA;
	
	public static Map<String, String> fTipoRelacao() {
		
		LinkedHashMap<String, String> vals = new LinkedHashMap<String, String>();

		vals.put(ENTRADA.toString(), ENTRADA.toString());
		vals.put(SAIDA.toString(), SAIDA.toString());
		
		return vals;
	}
	
}

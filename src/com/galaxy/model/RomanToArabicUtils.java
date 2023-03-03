package com.galaxy.model;

import java.util.HashMap;
import java.util.Map;

public class RomanToArabicUtils {
	private static Map <Character, Integer> symbols;
	private static final String mensajeError = "I have no idea what you are talking about";
	private static Map<String, String>RomanString = new HashMap<>();
	private static Map<String, Double> precioCosa = new HashMap<>();
	
	public RomanToArabicUtils() {
		symbols = new HashMap<>();
		symbols.put('I',1);
		symbols.put('V',5);
		symbols.put('X',10);
		symbols.put('L',50);
		symbols.put('C',100);
		symbols.put('D',500);
		symbols.put('M',1000);
	}
	
	public int toArabicNumber(String romanNumber) {
		int arabicNumber = 0;
		int size = romanNumber.length();
		int i = 0;
		try {
			for(i=0; i<size;i++) {
				int izquierda = symbols.get(romanNumber.charAt(i));
				int derecha = symbols.get(romanNumber.charAt(i+1));
				if(izquierda<derecha) {
					arabicNumber += derecha - izquierda;
					i++;
				}else {
					arabicNumber += izquierda;
				}
			}
		}catch(Exception ex) {
			System.out.println("Error message: "+ ex.getMessage());
		}
		
		return arabicNumber;
	}
	
	public String getTranslateArabicRoman(String inputLinea) {
		String[] linea = inputLinea.split(" ");
		int length = linea.length;
		String finalPalabra = linea[length - 1];
		
		if(finalPalabra.length() == 1 && null != symbols.get(finalPalabra.charAt(0))) {
			//Para identificar los strings de definición de variables tipo Credits
			//por ejemplo: glob is I
			RomanString.put(linea[0], finalPalabra);
		}else if (finalPalabra.endsWith("s")) {
			//Para identificar los strings de definición de variables tipo Credits
			//por ejemplo: glob glob Silver is 34 Credits
			
			int monto = Integer.parseInt(linea[length -2]);
			String nombreVariable = linea[length - 4];
			
			String romanString = "";
		
			for(int i = 0; i<length - 4; i++) {
				String temporal = RomanString.get(linea[i]);
				if(null == temporal || "".equals(temporal)) {
					System.out.println(mensajeError);
					return mensajeError;
				}
				romanString += temporal;
			}
			int num = toArabicNumber(romanString);
			precioCosa.put(nombreVariable, monto *1.0 / num);
		}else if(finalPalabra.endsWith("?")) {
			//Para identificar los strings de pregunta "how many" y "how much"
			//por ejemplo: how many Credits is glob prok Silver?
			
			if("many".equals(linea[1])) {
				//"how many"
				String romanString = "";
				
				for(int i = 4; i<length - 2; i++) {
					String temporal = RomanString.get(linea[i]);
					if(null == temporal || "".equals(temporal)) {
						System.out.println(mensajeError);
						return mensajeError;
					}
					romanString += temporal;
				}
				int num = toArabicNumber(romanString);
				
				String nombre = linea[length - 2];
				StringBuffer result = new StringBuffer();
				//Devolver la respuesta de la lectura
				//glob prok Silver is 68 Credits?
				for(int i = 4; i < length - 2; i++) {
					result.append(linea[i]).append(" ");
				}
				result.append(nombre).append(" is ").append((int) (num * precioCosa.get(nombre))).append(" Credits");
				System.out.println(result);
			}else if("much".equals(linea[1])) {
				//"how much"
				String romanString = "";
				
				for(int i = 3; i<length - 1; i++) {
					String temporal = RomanString.get(linea[i]);
					if(null == temporal || "".equals(temporal)) {
						System.out.println(mensajeError);
						return mensajeError;
					}
					romanString += temporal;
				}
				int numRes = toArabicNumber(romanString);
				
				StringBuffer result = new StringBuffer();
				//Devolver la respuesta de la lectura

				for(int i = 3; i < length - 1; i++) {
					result.append(linea[i]).append(" ");
				}
				result.append("is " + numRes);
				System.out.println(result);
			}
		}
		return null;
	}
}

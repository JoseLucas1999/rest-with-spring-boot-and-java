package br.com.lucas.util;

import br.com.lucas.exeption.ResourceNotFoundException;

public class MathUtil {
	
	public static  double convertToDouble(String strNumber) throws IllegalArgumentException{
		if (strNumber == null || strNumber.isEmpty())
			throw new ResourceNotFoundException("please, set an numeric value!");;
		String number = strNumber.replace(",", ".");
		return Double.parseDouble(number);
		//return 1D;
	}

	public static boolean isNumeric(String strNumber) {
		if (strNumber == null || strNumber.isEmpty()) {return false;}
		String number = strNumber.replace(",", "."); //substitui virgula por ponto
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
		//if number.matches("[-+]?[0-9]*\\.?[0-9]+");{return true;}
	}
}

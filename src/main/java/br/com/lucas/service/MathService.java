package br.com.lucas.service;

import org.springframework.stereotype.Service;

import br.com.lucas.exeption.ResourceNotFoundException;
import br.com.lucas.util.MathUtil;

@Service
public class MathService {
    
	public Double sum(String a, String b) { 
		if(!MathUtil.isNumeric(a) || !MathUtil.isNumeric(b)) 
			throw new ResourceNotFoundException("please, set an numeric value!");
		return MathUtil.convertToDouble(a) + MathUtil.convertToDouble(b);
    }
    
    public Double substraction(String a, String b) { 
		if(!MathUtil.isNumeric(a) || !MathUtil.isNumeric(b)) 
			throw new ResourceNotFoundException("please, set an numeric value!");
		return MathUtil.convertToDouble(a) - MathUtil.convertToDouble(b);
    }
    
    public Double multiplication(String a, String b) { 
		if(!MathUtil.isNumeric(a) || !MathUtil.isNumeric(b)) 
			throw new ResourceNotFoundException("please, set an numeric value!");
		return MathUtil.convertToDouble(a) * MathUtil.convertToDouble(b);
    }
    
    public Double division(String a, String b) { 
		if(!MathUtil.isNumeric(a) || !MathUtil.isNumeric(b)) 
			throw new ResourceNotFoundException("please, set an numeric value!");
		return MathUtil.convertToDouble(a) / MathUtil.convertToDouble(b);
    }
    
    public Double average(String a, String b) { 
		if(!MathUtil.isNumeric(a) || !MathUtil.isNumeric(b)) 
			throw new ResourceNotFoundException("please, set an numeric value!");
		return (MathUtil.convertToDouble(a) + MathUtil.convertToDouble(b))/2;
    }
    
    public Double sqrt(String a) { 
		if(!MathUtil.isNumeric(a)) 
			throw new ResourceNotFoundException("please, set an numeric value!");
		return Math.sqrt(MathUtil.convertToDouble(a));
    }
}

// @Service indica a class como um service
// MathUtil.isNumeric() : método criado na class Mathutil
// MathUtil.convertToDouble() : método criado na class Mathutil
// UnsupportedMathOperationException() : método criado no package exception

package com.miguelteles.projeto.resources.util;

import com.miguelteles.projeto.services.exception.InvalidDataException;

//Esta é uma classe útil para operações com data. Contém um único método.

public final class Date {
	
	//Este método valida a data passada como String. Joga uma exceção de InvalidDataException (exceção customizada) caso a data seja inválido.
	//Decidi manter como String já que não é necessário fazer nenhuma operação de comparação de datas ou algo parecido.
	public static boolean dataValida(String data) throws InvalidDataException {
		String[] datas = data.split("-");
		int[] datasInt = new int[3];
		int i=0;
		for(String str : datas) {
			datasInt[i]=Integer.parseInt(str);
			i++;
		}
		
		if(datas[0].length()!=4) throw new InvalidDataException("Ano inválido");
				
		int limiteDias=31;
		switch(datasInt[1]) {
			case 1: break;
			case 2: limiteDias=28; break;
			case 3: break;
			case 4: limiteDias=30; break;
			case 5: break;
			case 6: limiteDias=30; break;
			case 7: break;
			case 8: break;
			case 9: limiteDias=30; break;
			case 10: break;
			case 11: limiteDias=30; break;
			case 12: break;
			default:				
				throw new InvalidDataException("Mês inválido");
				
		}
		
		if(datasInt[2]<0 || datasInt[2]>limiteDias) throw new InvalidDataException("Dia inválido");
		
		return true;
	}
	
}

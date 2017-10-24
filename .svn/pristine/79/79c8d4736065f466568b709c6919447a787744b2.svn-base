package com.benqzl.unit;


public class DataTypeConvert {
	public static void main(String[] args){
		//new DataTypeConvert().longtobytes(new Long(127));
		
		String str = "adaasssdda";
		if(str.matches(".*das.*")){
			System.out.println(true);
		}
	}
	
	public byte[] longtobytes(Long l){
		
		String binaryStr = Long.toBinaryString(l);
		while(binaryStr.length()<8){
			binaryStr="0"+binaryStr;
		}
		byte[] b = new byte[8];
		for(int i = 0;i<binaryStr.length();i++){
			b[i]=Byte.parseByte(String.valueOf(binaryStr.charAt(i)), 2);
			//System.out.println(b[i]);
		}
		
		return b;
	}
}

package com.benqzl.unit;

import java.util.UUID;

public class CreateGuid {
	public static void main(String[] args){
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
		float sum = 0;
		float f = 3500*2+300*2*(89/2)+80*2*(89/2)+100*2*(89/2);
		sum+=f;
		System.out.println(f);
		f = 3500*2+300*2*5+80*2*5+100*2*5;
		sum+=f;
		System.out.println(f);
		f=3500*2+300*2*10+80*2*10+100*2*10;
		sum+=f;
		System.out.println(f);
		System.out.println(sum);
	}
}

package com.csii.mianshi;

import lombok.Getter;

public enum CountyEnum {
	one(1,"齐"),two(2,"楚"),three(3,"燕"),four(4,"赵"),five(5,"魏"),six(6,"韩");
	@Getter private Integer code;	
	@Getter private String retMessage;
	 CountyEnum(Integer code, String retMessage) {
		this.code = code;
		this.retMessage = retMessage;
	}
	 public static CountyEnum forEach_CountyEnum(int index) {
		 CountyEnum[] myArray =  CountyEnum.values();
		 for(CountyEnum ele:myArray) {
			 if(index == ele.getCode()) {
				 return ele;
			 }
		 }
		 return null;
	 }
	
	

}

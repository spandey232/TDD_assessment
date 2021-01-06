package com.tdd.assessment;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MainMethod {
	
	@Test
	public static void main(String[] args) {
		StringCalculator obj=new StringCalculator();
		Assert.assertEquals(0, obj.Add(""));
		Assert.assertEquals(1, obj.Add("1"));
		Assert.assertEquals(1+2, obj.Add("1,2"));
		Assert.assertEquals(1+2, obj.Add("1,2"));
		Assert.assertEquals(1+2+3, obj.Add("1,2,3"));
	}
}
class StringCalculator{
	public int Add(String numbers){
		if(numbers=="") return 0;
		else{
			int sum=0;
			String[] splitArr=numbers.split(",");
			for(int i=0;i<splitArr.length;i++){
				sum+=Integer.parseInt(splitArr[i]);
			}
			return sum;
		}
	}
}

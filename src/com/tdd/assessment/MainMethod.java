package com.tdd.assessment;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MainMethod {
	
	@Test
	public static void main(String[] args) {
		StringCalculator obj=new StringCalculator();
		Assert.assertEquals(0, obj.Add_with_handle_unknown_amount(""));
		Assert.assertEquals(1, obj.Add_with_handle_unknown_amount("1"));
		Assert.assertEquals(1+2, obj.Add_with_handle_unknown_amount("1,2"));
		Assert.assertEquals(1+2, obj.Add_with_handle_unknown_amount("1,2"));
		Assert.assertEquals(1+2+3, obj.Add_with_handle_unknown_amount("1,2,3"));
		//Assert.assertEquals(NumberFormatException.class, obj.Add_with_handle_unknown_amount("1,2,33333333333"));     Number Format Exception 
	}
}
class StringCalculator{
	
	public int Add_with_handle_unknown_amount(String numbers){
		if(numbers=="") return 0;
		else{
			int sum=0;
			String[] splitArr=numbers.split(",");
			for(int i=0;i<splitArr.length;i++){
				try{
				sum+=Integer.parseInt(splitArr[i]);
				}catch(Exception e){
					throw new NumberFormatException("Not a Valid Number");
				}
			}
			return sum;
		}
	}
	public int Add(String numbers){
		if(numbers=="") return 0;
		else{
			int sum=0;
			String[] splitArr=numbers.split(",");
			for(int i=0;i<splitArr.length;i++){
				try{
				sum+=Integer.parseInt(splitArr[i]);
				}catch(Exception e){
					throw new NumberFormatException("Not a Valid Number");
				}
			}
			return sum;
		}
	}
}

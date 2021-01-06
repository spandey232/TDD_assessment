package com.tdd.assessment;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MainMethod {
	
	@Test
	public static void main(String[] args) {
		StringCalculator obj=new StringCalculator();
		Assert.assertEquals(0, obj.AddSupportDifferentDelimeter(""));
		Assert.assertEquals(0, obj.AddSupportDifferentDelimeter(","));
		Assert.assertEquals(1, obj.AddSupportDifferentDelimeter("1"));
		Assert.assertEquals(1+2, obj.AddSupportDifferentDelimeter("1,2"));
		Assert.assertEquals(1+2, obj.AddSupportDifferentDelimeter("1,2"));
		Assert.assertEquals(1+2+3, obj.AddSupportDifferentDelimeter("1,2,3"));
		//Assert.assertEquals(NumberFormatException.class, obj.Add_with_handle_new_line("1,2,33333333333"));     Number Format Exception 
		
		Assert.assertEquals(0, obj.AddSupportDifferentDelimeter("\n"));
		Assert.assertEquals(1, obj.AddSupportDifferentDelimeter("\n1"));
		Assert.assertEquals(1+2+3, obj.AddSupportDifferentDelimeter("1,2\n3"));
		Assert.assertEquals(1+2, obj.AddSupportDifferentDelimeter("1\n2"));
		Assert.assertEquals(1+2+3, obj.AddSupportDifferentDelimeter("1\n2\n3"));
		
		Assert.assertEquals(0, obj.AddSupportDifferentDelimeter("//:\n"));
		Assert.assertEquals(6, obj.AddSupportDifferentDelimeter("//'\n1'2'3"));
		Assert.assertEquals(1, obj.AddSupportDifferentDelimeter("//:\n1"));
		Assert.assertEquals(1+2+3, obj.AddSupportDifferentDelimeter("//;\n1;2;3"));
		Assert.assertEquals(1+2, obj.AddSupportDifferentDelimeter("//:\n1:2"));
		Assert.assertEquals(1+2+3, obj.AddSupportDifferentDelimeter("//,\n1,2,3"));

	}
}
class StringCalculator{
	
	public int AddSupportDifferentDelimeter(String numbers){
		if(numbers=="") return 0;
		else{
			int sum=0;
			if(numbers.startsWith("//")){
				String numberPart=numbers.substring(numbers.indexOf("\n")+1);
				String[] splitArr=numberPart.split(numbers.substring(2,3));
				for(int i=0;i<splitArr.length;i++){
					if(splitArr[i].isEmpty()==false){
						try{
							sum+=Integer.parseInt(splitArr[i]);
						}catch(Exception e){
								throw new NumberFormatException("Not a Valid Number");
						}
					}
				}
			}else{
				String[] splitArr=numbers.split(",|\n");
				for(int i=0;i<splitArr.length;i++){
					if(splitArr[i].isEmpty()==false){
						try{
							sum+=Integer.parseInt(splitArr[i]);
						}catch(Exception e){
								throw new NumberFormatException("Not a Valid Number");
						}
					}
				}
			}
			return sum;
		}
	}
	public int Add_with_handle_new_line(String numbers){
		if(numbers=="") return 0;
		else{
			int sum=0;
			String[] splitArr=numbers.split(",|\n");
			for(int i=0;i<splitArr.length;i++){
				if(splitArr[i].isEmpty()==false){
				try{
				sum+=Integer.parseInt(splitArr[i]);
				}catch(Exception e){
					throw new NumberFormatException("Not a Valid Number");
				}
			  }
			}
			return sum;
		}
	}
	public int Add_with_handle_unknown_amount(String numbers){
		if(numbers=="") return 0;
		else{
			int sum=0;
			String[] splitArr=numbers.split(",");
			for(int i=0;i<splitArr.length;i++){
				if(splitArr[i].isEmpty()==false){
				try{
				sum+=Integer.parseInt(splitArr[i]);
				}catch(Exception e){
					throw new NumberFormatException("Not a Valid Number");
				}
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
				if(splitArr[i].isEmpty()==false){
				try{
				sum+=Integer.parseInt(splitArr[i]);
				}catch(Exception e){
					throw new NumberFormatException("Not a Valid Number");
				}
			  }
			}
			return sum;
		}
	}
}

package com.tdd.assessment;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MainMethod {
	
	@Test
	public static void main(String[] args) {
		StringCalculator obj=new StringCalculator();
		Assert.assertEquals(0, obj.addDelimeterOfAnyLength(""));
		Assert.assertEquals(0, obj.addDelimeterOfAnyLength(","));
		Assert.assertEquals(1, obj.addDelimeterOfAnyLength("1"));
		Assert.assertEquals(1+2, obj.addDelimeterOfAnyLength("1,2"));
		Assert.assertEquals(1+2, obj.addDelimeterOfAnyLength("1,2"));
		Assert.assertEquals(1+2+3, obj.addDelimeterOfAnyLength("1,2,3"));
		//Assert.assertEquals(NumberFormatException.class, obj.Add_with_handle_new_line("1,2,33333333333"));   //  throws  "Not a Valid Number"
		
		Assert.assertEquals(0, obj.addDelimeterOfAnyLength("\n"));
		Assert.assertEquals(1, obj.addDelimeterOfAnyLength("\n1"));
		Assert.assertEquals(1+2+3, obj.addDelimeterOfAnyLength("1,2\n3"));
		Assert.assertEquals(1+2, obj.addDelimeterOfAnyLength("1\n2"));
		Assert.assertEquals(1+2+3, obj.addDelimeterOfAnyLength("1\n2\n3"));
		
		Assert.assertEquals(0, obj.addDelimeterOfAnyLength("//:\n"));
		Assert.assertEquals(6, obj.addDelimeterOfAnyLength("//'\n1'2'3"));
		Assert.assertEquals(1, obj.addDelimeterOfAnyLength("//:\n1"));
		Assert.assertEquals(1+2+3, obj.addDelimeterOfAnyLength("//;\n1;2;3"));
		Assert.assertEquals(1+2, obj.addDelimeterOfAnyLength("//:\n1:2"));
		Assert.assertEquals(1+2+3, obj.addDelimeterOfAnyLength("//,\n1,2,3"));

		Assert.assertEquals(0, obj.addDelimeterOfAnyLength("//:\n"));
		//Assert.assertEquals(RuntimeException.class, obj.AddThrowNegNumException("//'\n1'2'-3"));			throws  "negatives not allowed[-3]"
		//Assert.assertEquals(RuntimeException.class, obj.AddThrowNegNumException("//'\n-1'-2'-3"));        throws  "negatives not allowed[-1, -2, -3]"
		
		Assert.assertEquals(3, obj.addDelimeterOfAnyLength("//'\n1'2'3000"));
		Assert.assertEquals(0, obj.addDelimeterOfAnyLength("//:\n1100"));
		Assert.assertEquals(1+3, obj.addDelimeterOfAnyLength("//;\n1;2000;3"));
		Assert.assertEquals(1002, obj.addDelimeterOfAnyLength("//:\n1000:2"));
		Assert.assertEquals(1+3, obj.addDelimeterOfAnyLength("//,\n1,2000,3"));
		
		Assert.assertEquals(3, obj.addDelimeterOfAnyLength("//''''\n1''2'''3000"));
		Assert.assertEquals(0, obj.addDelimeterOfAnyLength("//:::::::\n1100"));
		Assert.assertEquals(1+200+3, obj.addDelimeterOfAnyLength("//;;;\n1;;;200;;;;;3"));
		Assert.assertEquals(1002, obj.addDelimeterOfAnyLength("//:::\n1000:::2"));
		Assert.assertEquals(1+20+3, obj.addDelimeterOfAnyLength("//,,,,,,\n1,,,,,,20,,,,3"));
		System.out.println(obj.GetCalledCount());
	}
}
class StringCalculator{
	int methodCallCount=0;
	public int addDelimeterOfAnyLength(String numbers){
		synchronized(this) {
			methodCallCount++;
	    }
		if(numbers=="") return 0;
		else{
			int sum=0;
			ArrayList<Integer> negativeValues=new ArrayList<>();
			if(numbers.startsWith("//")){
				String numberPart=numbers.substring(numbers.indexOf("\n")+1);
				String[] splitArr=numberPart.split(numbers.substring(2,3)+"+");
				for(int i=0;i<splitArr.length;i++){
					if(splitArr[i].isEmpty()==false){
						try{
							int value=Integer.parseInt(splitArr[i]);
							if(value<0)
						    	negativeValues.add(value);
							if(value<=1000)
							    sum+=value;
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
							int value=Integer.parseInt(splitArr[i]);
							if(value<0)
						    	negativeValues.add(value);
							if(value<=1000)
								sum+=value;
						}catch(Exception e){
								throw new NumberFormatException("Not a Valid Number");
						}
					}
				}
			}
			if(negativeValues.size()>0)
				throw new RuntimeException("negatives not allowed"+negativeValues);
			return sum;
		}
	}
	
	public int AddIgnoreMoreThan1000(String numbers){
		synchronized(this) {
			methodCallCount++;
	    }
		if(numbers=="") return 0;
		else{
			int sum=0;
			ArrayList<Integer> negativeValues=new ArrayList<>();
			if(numbers.startsWith("//")){
				String numberPart=numbers.substring(numbers.indexOf("\n")+1);
				String[] splitArr=numberPart.split(numbers.substring(2,3));
				for(int i=0;i<splitArr.length;i++){
					if(splitArr[i].isEmpty()==false){
						try{
							int value=Integer.parseInt(splitArr[i]);
							if(value<0)
						    	negativeValues.add(value);
							if(value<=1000)
							    sum+=value;
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
							int value=Integer.parseInt(splitArr[i]);
							if(value<0)
						    	negativeValues.add(value);
							if(value<=1000)
								sum+=value;
						}catch(Exception e){
								throw new NumberFormatException("Not a Valid Number");
						}
					}
				}
			}
			if(negativeValues.size()>0)
				throw new RuntimeException("negatives not allowed"+negativeValues);
			return sum;
		}
	}
	public int AddThrowNegNumException(String numbers){
		synchronized(this) {
			methodCallCount++;
	    }
		if(numbers=="") return 0;
		else{
			int sum=0;
			ArrayList<Integer> negativeValues=new ArrayList<>();
			if(numbers.startsWith("//")){
				String numberPart=numbers.substring(numbers.indexOf("\n")+1);
				String[] splitArr=numberPart.split(numbers.substring(2,3));
				for(int i=0;i<splitArr.length;i++){
					if(splitArr[i].isEmpty()==false){
						try{
							if(Integer.parseInt(splitArr[i])<0)
						    	negativeValues.add(Integer.parseInt(splitArr[i]));
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
							if(Integer.parseInt(splitArr[i])<0)
						    	negativeValues.add(Integer.parseInt(splitArr[i]));
							sum+=Integer.parseInt(splitArr[i]);
						}catch(Exception e){
								throw new NumberFormatException("Not a Valid Number");
						}
					}
				}
			}
			if(negativeValues.size()>0)
				throw new RuntimeException("negatives not allowed"+negativeValues);
			return sum;
		}
	}
	
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
	
	public int GetCalledCount(){
		return methodCallCount;
	}

}

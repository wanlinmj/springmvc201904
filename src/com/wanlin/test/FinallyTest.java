package com.wanlin.test;
/**
 * @author kangwl_pc
 *
 */
public class FinallyTest {

	public static void main(String[] args){
		System.out.println(getValue());
	}
	
	public static int getValue(){
		try{
			return 0;
		}finally{
			return 1;
		}
	}
}

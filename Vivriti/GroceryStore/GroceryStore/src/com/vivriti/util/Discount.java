package com.vivriti.util;

import java.util.Scanner;

import com.vivriti.constants.ConstantsUtil;
import com.vivriti.modal.CustomerVO;

public class Discount {

	Scanner in = new Scanner(System.in);

	public static void main(String args[]){ 


	}

	public CustomerVO setDiscountSeniorCitizen(CustomerVO p_objCustomerVO){

		CustomerVO l_objCustomerVO = p_objCustomerVO;

		try{

			System.out.println(ConstantsUtil.SENIOR_CITIZEN);

			char citizenFlag = in.next().charAt(0);

			if(citizenFlag == 'Y'){

				System.out.println(ConstantsUtil.SENIOR_CITIZEN_DISCOUNT);

				l_objCustomerVO.setSeniotCitizenDiscount(in.nextFloat());

			}else{

				l_objCustomerVO.setSeniotCitizenDiscount(0);

			}
		}catch(Exception exp){

			System.out.println(exp.getMessage());
		}
		return l_objCustomerVO;
	}

	public CustomerVO setDiscountCurrEmployee(CustomerVO p_objCustomerVO){

		CustomerVO l_objCustomerVO = p_objCustomerVO;

		try{

			System.out.println(ConstantsUtil.CURR_EMPLOYEE);

			char employeeFlag = in.next().charAt(0);

			if(employeeFlag == 'Y'){

				System.out.println(ConstantsUtil.CURR_EMPLOYEE_DISCOUNT);

				l_objCustomerVO.setEmpDiscount(in.nextFloat());

			}else{

				l_objCustomerVO.setEmpDiscount(0);

				l_objCustomerVO.setIsCurrentEmployee(false);
			}
		}catch(Exception exp){

			System.out.println(exp.getMessage());
		}

		return l_objCustomerVO;

	}

	public float calculateDiscount(float p_fTotalPrice, float p_fDisPrice){

		float discountValue = (int)(p_fTotalPrice) * (p_fDisPrice)/100 ;

		return (int)(p_fTotalPrice) - discountValue ;
	}
}

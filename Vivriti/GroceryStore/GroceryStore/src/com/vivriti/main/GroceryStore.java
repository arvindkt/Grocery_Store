package com.vivriti.main;

import java.util.Scanner;

import com.vivriti.constants.ConstantsUtil;
import com.vivriti.modal.CustomerVO;
import com.vivriti.util.Billing;
import com.vivriti.util.Discount;
import com.vivriti.util.Store;

public class GroceryStore {

	Scanner in = new Scanner(System.in);

	Billing m_objBilling = new Billing();

	Store m_objStore = new Store();

	public static void main(String args[]){

		GroceryStore l_objGroceryStore = new GroceryStore();

		l_objGroceryStore.initializeGroceryStore();

	}
	
	public void initializeGroceryStore(){

		try{
			
			m_objStore.createRegister();

			m_objStore.addItemsToRegister();

			initializeTrasaction();

		}catch(Exception exp){

			System.out.println(exp.getMessage());
		}
	}

	public void initializeTrasaction(){

		char transactionFlag = 'Y';

		try{

			if(transactionFlag != 'Y' || transactionFlag != 'N'){

				while(transactionFlag == 'Y'){

					System.out.println(ConstantsUtil.DRAW_LINE);

					System.out.println(ConstantsUtil.START_BILLING);

					System.out.println(ConstantsUtil.DRAW_LINE);

					CustomerVO l_objCustomerVO = getCustomerInput();

					m_objBilling.startBilling(l_objCustomerVO);

					System.out.println(ConstantsUtil.DRAW_LINE);

					System.out.println(ConstantsUtil.COMPLETED_BILLING);

					System.out.println(ConstantsUtil.MORE_TRANSCATION);

					transactionFlag = in.next().charAt(0);

				}

				System.out.println(ConstantsUtil.THANKS);

				m_objBilling.showingFinalInventory();

			}

		}catch(Exception exp){

			System.out.println(exp.getMessage());
		}

	}

	public CustomerVO getCustomerInput(){
		
		Discount l_objDiscount = new Discount();

		CustomerVO l_objCustomerVO = new CustomerVO();
		
		try{
		
		l_objCustomerVO = l_objDiscount.setDiscountSeniorCitizen(l_objCustomerVO);

		l_objCustomerVO = l_objDiscount.setDiscountCurrEmployee(l_objCustomerVO);

		System.out.println(ConstantsUtil.CURRENT_EMP);

		char currentEmpFlag = in.next().charAt(0);

		l_objCustomerVO = getCustomerInput(l_objCustomerVO,currentEmpFlag);
		
		}catch(Exception exp){
			
			System.out.println(exp.getMessage());
		}

		return l_objCustomerVO;
		
	}

	public CustomerVO getCustomerInput(CustomerVO p_objCustomerVO, char currentEmpFlag){
		
		try{

		if(currentEmpFlag == 'Y'){

			System.out.println(ConstantsUtil.EMP_NAME);

			p_objCustomerVO.setCustomerName(in.next());

			System.out.println(ConstantsUtil.EMP_AGE);

			p_objCustomerVO.setAge(in.nextInt());

			System.out.println(ConstantsUtil.EMP_PHONE);

			p_objCustomerVO.setPhoneNumber(in.next());

			p_objCustomerVO.setIsCurrentEmployee(true);

			p_objCustomerVO.setIsSeniorCitizen(false);

		}else{

			System.out.println(ConstantsUtil.CUS_NAME);

			p_objCustomerVO.setCustomerName(in.next());

			System.out.println(ConstantsUtil.CUS_AGE);

			p_objCustomerVO.setAge(in.nextInt());

			System.out.println(ConstantsUtil.CUS_PHONE);

			p_objCustomerVO.setPhoneNumber(in.next());

			p_objCustomerVO.setIsSeniorCitizen((p_objCustomerVO.getAge() >= 60)?true:false);

			p_objCustomerVO.setIsCurrentEmployee(false);

		}
		
		}catch(Exception exp){
			
			System.out.println(exp.getMessage());
		}

		return p_objCustomerVO;
	}

}

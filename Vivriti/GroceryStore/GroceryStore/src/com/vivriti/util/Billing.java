package com.vivriti.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.vivriti.constants.ConstantsUtil;
import com.vivriti.modal.CustomerVO;
import com.vivriti.modal.ProductVO;

public class Billing {
	
	static Map<String,Map<String,List<Map<String,ProductVO>>>> m_mapRegister = new LinkedHashMap<String,Map<String,List<Map<String,ProductVO>>>>();

	static Map<String,List<Map<String,ProductVO>>> m_mapRegisterItems = new LinkedHashMap<String,List<Map<String,ProductVO>>>();

	CustomerVO m_objCustomerVO = new CustomerVO();

	Discount m_objDiscount  = new Discount();

	Store m_objStore = new Store();

	Scanner in = new Scanner(System.in);

	Map<String,ProductVO> m_mapCart = new HashMap<String,ProductVO>();

	public Billing(Map<String, List<Map<String, ProductVO>>> p_mapRegisterItems) {

		m_mapRegisterItems = p_mapRegisterItems;

	}

	public Billing() {

	}

	public void startBilling(CustomerVO p_objCustomerVO){

		try{

			m_objCustomerVO = p_objCustomerVO;

			char billingFlag = 'Y';

			while(billingFlag == 'Y'){

				m_objStore.showRegisters(m_mapRegisterItems);

				System.out.println(ConstantsUtil.REGISER_SELECT);

				String l_sRegisterName = in.next();

				while(!m_mapRegisterItems.keySet().contains(l_sRegisterName)){

					System.out.println(ConstantsUtil.VALID_REGISTER);

					l_sRegisterName = in.next();
				}

				chooseItems(l_sRegisterName);

				System.out.println(ConstantsUtil.ADD_MORE_ITEMS_CART);

				billingFlag = in.next().charAt(0);

			}

			generateBills();

		}catch(Exception exp){

			System.out.println(exp.getMessage());
		}

	}

	public void chooseItems(String p_sRegisterName){

		try{

			System.out.println(ConstantsUtil.DRAW_LINE);

			System.out.printf(ConstantsUtil.SPACE_CHOOSE_ITEMS,ConstantsUtil.NAME,ConstantsUtil.QUANTITY,ConstantsUtil.PRICE,ConstantsUtil.DISCOUNT);

			System.out.println(ConstantsUtil.DRAW_LINE);

			List<Map<String, ProductVO>> l_lstItems = m_mapRegisterItems.get(p_sRegisterName);

			for(int i = 0; i < l_lstItems.size(); i++){

				Map<String,ProductVO> l_mapItems = l_lstItems.get(i);

				for(Entry<String,ProductVO> items : l_mapItems.entrySet()){

					System.out.printf(ConstantsUtil.SPACE_CHOOSE_ITEMS,items.getValue().getItemName(),
							items.getValue().getQuantity(),items.getValue().getPrice(),items.getValue().getDiscount());

				}
			}
			
			System.out.println(ConstantsUtil.DRAW_LINE);

			System.out.println(ConstantsUtil.SELECT_ITEM);

			System.out.println(ConstantsUtil.ITEM_NAME);

			String l_sItemName = in.next() ;

			System.out.println(ConstantsUtil.ITEM_QUANTITY);

			int l_iQuantity = in.nextInt();

			for(int i = 0; i < l_lstItems.size(); i++){

				Map<String,ProductVO> l_mapItems = l_lstItems.get(i);

				if(l_mapItems.containsKey(l_sItemName)){

					ProductVO l_objProductVO = new ProductVO();

					ProductVO items = l_mapItems.get(l_sItemName);

					while(!l_mapItems.containsKey(l_sItemName)){

						System.out.println(ConstantsUtil.VALID_ITEM);

						System.out.println(ConstantsUtil.ITEM_NAME);

						l_sItemName = in.next() ;

					}

					while(l_iQuantity > items.getQuantity() || items.getQuantity() == 0){

						System.out.println(ConstantsUtil.VALID_QUANTITY);

						System.out.println(ConstantsUtil.ITEM_QUANTITY);

						l_iQuantity = in.nextInt();
					}

					int remainQuantity = items.getQuantity() - l_iQuantity ;

					if(items.getQuantity() >= l_iQuantity){

						items.setQuantity(remainQuantity);
						
						l_mapItems.put(items.getItemName(), items);
					}

					if(items.getQuantity() == 0){

						l_lstItems.remove(i);
					}

					l_objProductVO = (ProductVO)items.clone();

					l_objProductVO.setQuantity(l_iQuantity);

					m_mapCart.put(l_sItemName, l_objProductVO);

				}

			}

		}catch (CloneNotSupportedException e) {

			System.out.println(e.getMessage());

		}catch(Exception  e){

			System.out.println(e.getMessage());

		}


	}

	public void generateBills(){

		try{

			System.out.println(ConstantsUtil.DRAW_LINE);

			System.out.println(ConstantsUtil.BILL_SUMMARY);

			System.out.println(ConstantsUtil.DRAW_LINE);

			System.out.println(ConstantsUtil.CUSTOMER_NAME+ m_objCustomerVO.getCustomerName());

			System.out.println(ConstantsUtil.AGE + m_objCustomerVO.getAge());

			System.out.println(ConstantsUtil.PHONE_NO+ m_objCustomerVO.getPhoneNumber());

			System.out.println(ConstantsUtil.DRAW_LINE);

			System.out.printf(ConstantsUtil.SPACE_BILL,ConstantsUtil.S_NO,ConstantsUtil.NAME,ConstantsUtil.QUANTITY,ConstantsUtil.PRICE,ConstantsUtil.DISCOUNT,ConstantsUtil.TOTAL,ConstantsUtil.DISCOUNT_PRICE);

			System.out.println(ConstantsUtil.DRAW_LINE);

			float l_fTotalPrice = 0f;

			float l_fDiscountTotal = 0f;

			int i =0;

			for(Entry<String,ProductVO> l_mapCart : m_mapCart.entrySet()){

				ProductVO l_objProductVO = l_mapCart.getValue();

				float l_fTotal = l_objProductVO.getPrice() * l_objProductVO.getQuantity();

				l_fDiscountTotal = m_objDiscount.calculateDiscount(l_fTotal,l_objProductVO.getDiscount());

				l_fTotalPrice = l_fTotalPrice + l_fDiscountTotal;

				System.out.printf(ConstantsUtil.SPACE_BILL,++i,l_objProductVO.getItemName(),l_objProductVO.getQuantity(),l_objProductVO.getPrice(),l_objProductVO.getDiscount()+"%",l_fTotal,l_fDiscountTotal);

			}

			System.out.println(ConstantsUtil.DRAW_LINE);

			if(m_objCustomerVO.isSeniorCitizen()){

				System.out.println(ConstantsUtil.TOTAL_AMOUNT + l_fTotalPrice);

				System.out.println(ConstantsUtil.SENIOR_CITIZEN_DIS + m_objCustomerVO.getSeniotCitizenDiscount()+"%");

				l_fTotalPrice = m_objDiscount.calculateDiscount(l_fTotalPrice, m_objCustomerVO.getSeniotCitizenDiscount());
			}

			if(m_objCustomerVO.isCurrentEmployee()){

				System.out.println("Total Amount :\t" + l_fTotalPrice);

				System.out.println( ConstantsUtil.EMP_DISCOUNT+ m_objCustomerVO.getEmpDiscount()+"%");

				l_fTotalPrice = m_objDiscount.calculateDiscount(l_fTotalPrice, m_objCustomerVO.getEmpDiscount());
			}

			System.out.println(ConstantsUtil.TOTAL_BE_PAID+ l_fTotalPrice);

		}catch(Exception exp){

			System.out.println(exp.getMessage());
		}

	}

	public void showingFinalInventory(){
		
		try{
			
			m_objStore.displayInventory(m_mapRegisterItems);

		}catch(Exception  exp){
			
			System.out.println(exp.getMessage());

		}
	}

}


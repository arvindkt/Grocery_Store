package com.vivriti.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.vivriti.constants.ConstantsUtil;
import com.vivriti.modal.ProductVO;

public class Store {

	Scanner in = new Scanner(System.in);

	Map<String,List<Map<String,ProductVO>>> m_mapRegisterItems = new LinkedHashMap<String,List<Map<String,ProductVO>>>();

	public void initBilling(){

		Billing l_objBilling = new Billing(m_mapRegisterItems);

	}

	public void createRegister(){

		try{

			System.out.println(ConstantsUtil.ADD_REGISTER);

			char flag = 'N' ;

			do{

				System.out.println(ConstantsUtil.REGISTER_NAME);

				String l_sRegisterName = in.next();

				List<Map<String,ProductVO>> l_lstProduct = new ArrayList<Map<String,ProductVO>>();

				m_mapRegisterItems.put(l_sRegisterName,l_lstProduct);

				System.out.println(ConstantsUtil.MORE_REGISTER);

				flag = in.next().charAt(0);

			}while(flag == 'Y');

		}catch(Exception exp){

			System.out.println(exp.getMessage());
		}

	}

	public void addItemsToRegister(){

		try{

			System.out.println(ConstantsUtil.ADD_ITEMS);

			char flag = 'N';

			do{

				Map<String,ProductVO> l_mapItems = new HashMap<String,ProductVO>();

				showRegisters(m_mapRegisterItems);

				System.out.println(ConstantsUtil.SELECT_REGISTER);

				String l_sRegisterName = in.next();

				while(!m_mapRegisterItems.keySet().contains(l_sRegisterName)){

					System.out.println(ConstantsUtil.VALID_REGISTER);

					l_sRegisterName = in.next();
				}

				ProductVO l_objProductVO = new ProductVO();

				System.out.println(ConstantsUtil.ITEM_NAME);

				l_objProductVO.setItemName(in.next());

				System.out.println(ConstantsUtil.ITEM_QUANTITY);

				l_objProductVO.setQuantity(in.nextInt());

				System.out.println(ConstantsUtil.ITEM_PRICE);

				l_objProductVO.setPrice(in.nextFloat());

				System.out.println(ConstantsUtil.ITEM_DISCOUNT);

				l_objProductVO.setDiscount(in.nextFloat());

				l_mapItems.put(l_objProductVO.getItemName(),l_objProductVO);

				if(!m_mapRegisterItems.containsKey(l_sRegisterName)){

					List<Map<String,ProductVO>> l_lstProducts = new ArrayList<Map<String,ProductVO>>();

					l_lstProducts.add(l_mapItems);

					m_mapRegisterItems.put(l_sRegisterName, l_lstProducts);

				}else{

					List<Map<String,ProductVO>> l_lstProducts = m_mapRegisterItems.get(l_sRegisterName);

					l_lstProducts.add(l_mapItems);

					m_mapRegisterItems.put(l_sRegisterName, l_lstProducts);
				}

				System.out.println(ConstantsUtil.ADD_MORE_ITEMS);

				flag = in.next().charAt(0);

			}while(flag == 'Y');

			displayInventory(m_mapRegisterItems);

			initBilling();

		}catch(Exception exp){

			System.out.println(exp.getMessage());
		}

	}

	public void showRegisters(Map<String, List<Map<String, ProductVO>>> m_mapRegisterItems2){

		try{

			System.out.println(ConstantsUtil.DRAW_LINE);

			System.out.printf(ConstantsUtil.SPACE_REGISTER,ConstantsUtil.S_NO,ConstantsUtil.REGISTER);

			System.out.println(ConstantsUtil.DRAW_LINE);

			int i = 0;

			for(String register : m_mapRegisterItems2.keySet()){
				System.out.printf(ConstantsUtil.SPACE_REGISTER,++i,register);
			}

		}catch(Exception exp){

			System.out.println(exp.getMessage());
		}
	}

	public void displayInventory(Map<String,List<Map<String,ProductVO>>> m_mapRegisterItems){

		try{

			System.out.println(ConstantsUtil.DRAW_LINE);

			System.out.println(ConstantsUtil.GROCERY_STORE);

			System.out.println(ConstantsUtil.DRAW_LINE);

			System.out.printf(ConstantsUtil.SPACE_GROCERY,ConstantsUtil.REGISTER,ConstantsUtil.NAME,ConstantsUtil.QUANTITY,ConstantsUtil.PRICE,ConstantsUtil.DISCOUNT);

			System.out.println(ConstantsUtil.DRAW_LINE);

			for(Entry<String, List<Map<String, ProductVO>>> entry : m_mapRegisterItems.entrySet()){

				List<Map<String, ProductVO>> l_lstItems = entry.getValue();

				for(int i = 0; i < l_lstItems.size(); i++){

					Map<String,ProductVO> l_mapItems = l_lstItems.get(i);

					for(Entry<String,ProductVO> items : l_mapItems.entrySet()){

						System.out.printf(ConstantsUtil.SPACE_GROCERY,entry.getKey(),items.getValue().getItemName(),
								items.getValue().getQuantity(),items.getValue().getPrice(),items.getValue().getDiscount()+"%");

					}
				}
			}

			System.out.println(ConstantsUtil.DRAW_LINE);

		}catch(Exception exp){

			System.out.println(exp.getMessage());
		}

	}


}



package dao.custom.impl;


import dao.DAOFactory;
import dao.DAOType;
import dao.SuperDAO;
import dao.custom.QueryDAO;
import entity.CustomEntity;

import java.util.List;

class QueryDAOImplTest {
    public static void main(String[] args) {
        QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOType.QUERY);
        CustomEntity ce = queryDAO.getOrderDetail("OD001");
        System.out.println(ce.getOrderId()+" , "+ce.getCustomerName()+" , "+ce.getOrderDate());

        CustomEntity ce1 = queryDAO.getOrderDetail2("OD001");
        System.out.println(ce1.getOrderId()+" , "+ce1.getCustomerName()+" , "+ce1.getCustomerId());

        List<CustomEntity> list = queryDAO.searchOrder();
        System.out.println(list);
    }

}
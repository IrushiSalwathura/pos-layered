package dao;

import dao.custom.CustomerDAO;
import dao.custom.ItemDAO;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailDAO;
import dao.custom.impl.CustomerDAOImpl;
import dao.custom.impl.ItemDAOImpl;
import dao.custom.impl.OrderDAOImpl;
import dao.custom.impl.OrderDetailDAOImpl;
import db.DBConnection;
import entity.Item;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static  DAOFactory getInstance(){
        return (daoFactory == null)? daoFactory = new DAOFactory(): daoFactory;
    }

    public SuperDAO getDAO(DAOType daoType){
        switch (daoType){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM :
                return new ItemDAOImpl();
            case ORDER :
                return new OrderDAOImpl();
            case ORDERDETAIL :
                return new OrderDetailDAOImpl();
            default:
                return null;
        }
    }

    /*public CustomerDAO getCustomerDAO(){
        return new CustomerDAOImpl();
    }

    public ItemDAO getItemDAO(){
        return new ItemDAOImpl();
    }

    public OrderDAO geOrderDAO(){
        return new OrderDAOImpl();
    }

    public OrderDetailDAO geOrderDetailDAO(){
        return new OrderDetailDAOImpl();
    }*/
}

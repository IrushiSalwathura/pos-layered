package dao;

import com.sun.org.apache.regexp.internal.RE;
import dao.custom.*;
import dao.custom.impl.*;
import db.DBConnection;
import entity.Item;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static  DAOFactory getInstance(){
        return (daoFactory == null)? daoFactory = new DAOFactory(): daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOType daoType){
        switch (daoType){
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case ITEM :
                return (T) new ItemDAOImpl();
            case ORDER :
                return (T) new OrderDAOImpl();
            case ORDERDETAIL :
                return (T) new OrderDetailDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
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

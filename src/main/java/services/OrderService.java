package services;

import core.Client;
import core.Order;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    public OrderService(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public Order saveOrder(Order order, Client client){
        return transactionHelper.executeInTransaction(session -> {
            order.setClient(client);
            session.persist(order);
            return order;
        });
    }

    public void deleteOrder(Long id){
        transactionHelper.executeInTransaction(session -> {
            Order orderForDelete = session.get(Order.class, id);
            session.remove(orderForDelete);
        });
    }

    public List<Order> findOrderByPrice(BigDecimal price){
        return transactionHelper.executeInTransaction(session -> {
            return session.createQuery("from Order where totalAmount = :price", Order.class)
                    .setParameter("price", price)
                    .getResultList();
        });
    }

}

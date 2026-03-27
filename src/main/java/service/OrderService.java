package service;

import core.Client;
import core.Order;
import core.OrderStatus;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final SessionFactory sessionFactory;

    public OrderService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Order saveOrder(Long clientId, BigDecimal amount){
        Client client = sessionFactory.getCurrentSession().get(Client.class, clientId);
        if(client == null){
            throw new IllegalArgumentException("Клиент с таким id не найдет");
        }
        Order order = new Order(LocalDateTime.now(), amount, OrderStatus.ACCEPTED);

        client.addOrder(order);

        sessionFactory.getCurrentSession().persist(order);
        return order;
    }

    public void deleteOrder(Long id){
        Order order = getOrderById(id);

        Client client = order.getClient();
        if(client != null){
            client.deleteOrder(order);
        }
        sessionFactory.getCurrentSession().remove(order);
    }

    public Order getOrderById(Long id){
        Order order = sessionFactory.getCurrentSession().get(Order.class, id);
        if(order == null){
            throw new IllegalArgumentException("Ордер с таким id не найден");
        }
        return order;
    }

    public List<Order> findByPrice(BigDecimal price) {
        List<Order> orderListByPrice = sessionFactory.getCurrentSession()
                .createQuery("from Order where totalAmount = :price", Order.class)
                .setParameter("price", price)
                .getResultList();
        return orderListByPrice;
    }

    public List<Order> findByClientId(Long clientId){
        List<Order> orderListByCLientId = sessionFactory.getCurrentSession()
                .createQuery("from Order where client.id = :clientId", Order.class)
                .setParameter("clientId", clientId)
                .getResultList();
        return orderListByCLientId;
    }

    public List<Order> findByPriceAndClientId(Long clientId, BigDecimal price) {
        List<Order> orderListByCLientIdAndPrice = sessionFactory.getCurrentSession()
                .createQuery("from Order where client.id = :clientId and totalAmount = :price", Order.class)
                .setParameter("clientId", clientId)
                .setParameter("price", price)
                .getResultList();
        return orderListByCLientIdAndPrice;
    }

    public static void printOrders(List<Order> orders){
        if(orders.isEmpty()){
            System.out.println("Заказ не найден");
            return;
        }

        System.out.println("Найдены заказы: ");
        orders.forEach( order -> {
            System.out.println("Id: " + order.getId()
            + "; ClientId: " + order.getClient().getId()
            + "; Date: " + order.getOrderDate()
            + "; Amount: " + order.getTotalAmount()
            + "; Status: " + order.getStatus());
        });
    }

}

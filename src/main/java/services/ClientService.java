package services;

import core.Client;
import core.Coupon;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    public ClientService(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public Client saveClient(Client client){
         return transactionHelper.executeInTransaction(session -> {
            session.persist(client);
            return client;
        });
    }

    public void deleteClient(Long id){
        transactionHelper.executeInTransaction(session -> {
            Client clientForDelete = session.get(Client.class, id);
            session.remove(clientForDelete);
        });
    }

    public void editName(Long id, String newName){
        transactionHelper.executeInTransaction(session -> {
            Client edit_client = session.get(Client.class, id);
            edit_client.setName(newName);
        });
    }

    public void editEmail(Long id, String newEmail){
        transactionHelper.executeInTransaction(session -> {
            Client edit_client = session.get(Client.class, id);
            edit_client.setEmail(newEmail);
        });
    }

    public Client getClientById(Long id) {
        return transactionHelper.executeInTransaction(session -> {
            return session.get(Client.class, id);
        });
    }

    public void addCoupon(Long clientId, Long couponId){
        transactionHelper.executeInTransaction(session -> {
            Client client = session.get(Client.class, clientId);
            Coupon coupon = session.get(Coupon.class, couponId);

            if(client != null && coupon != null){
                client.getCouponList().add(coupon);
            } else {
                throw new EntityNotFoundException("Клиент или купон не найден");
            }
        });
    }

}

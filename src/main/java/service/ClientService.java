package service;

import core.Client;
import core.Coupon;
import core.Profile;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class ClientService {

    private final SessionFactory sessionFactory;

    public ClientService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Client createClient(String name, String email, String phone, String address){
         validateName(name);
         validateEmail(email);

         Client client = new Client(LocalDateTime.now(), name, email);
         Profile profile = new Profile(phone, address);

         client.setProfile(profile);

         sessionFactory.getCurrentSession().persist(client);
         return client;
    }

    public void deleteClient(Long id){
        Client client = getClientById(id);
        sessionFactory.getCurrentSession().remove(client);
    }

    public void editName(Long id, String newName){
        Client client = getClientById(id);
        client.setName(newName);
    }

    public void editEmail(Long id, String newEmail){
        Client client = getClientById(id);
        client.setName(newEmail);
    }

    public void editPhone(Long id, String newPhone){
        Client client = getClientById(id);
        if(client.getProfile() == null){
            throw new IllegalArgumentException("У клиента с таким id-" + id + " отсутствует профиль");
        }
        client.getProfile().setPhone(newPhone);
    }

    public void editAddress(Long id, String newAddress){
        Client client = getClientById(id);
        if(client.getProfile() == null){
            throw new IllegalArgumentException("У клиента с таким id-" + id + " отсутствует профиль");
        }
        client.getProfile().setAddress(newAddress);
    }

    public void addCoupon(Long clientId, Long couponId){
        Client client = sessionFactory.getCurrentSession().get(Client.class, clientId);
        Coupon coupon = sessionFactory.getCurrentSession().get(Coupon.class, couponId);

        if(client == null){
            throw new IllegalArgumentException("Клиент с таким id не найден");
        }
        if(coupon == null){
            throw new IllegalArgumentException("Купон с таким id не найден");
        }
        client.getCouponList().add(coupon);
    }

    public Client getClientById(Long id) {
        Client client = sessionFactory.getCurrentSession().get(Client.class, id);
        if(client == null){
            throw new IllegalArgumentException("Клиент с таким id не наден");
        }
        return client;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя обязательно");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email обязателен");
        }
    }
}


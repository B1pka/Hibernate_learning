package service;

import core.Client;
import core.Coupon;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CouponService {

    private final SessionFactory sessionFactory;

    public CouponService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Coupon saveCoupon(Coupon coupon){
        if (coupon == null) {
            throw new IllegalArgumentException("Купон обязателен");
        }
        if (coupon.getCode() == null) {
            throw new IllegalArgumentException("Код купона обязателен");
        }
        sessionFactory.getCurrentSession().persist(coupon);
        return coupon;
    }

    public void deleteCoupon(Long id){
        Coupon coupon = getCouponById(id);
        sessionFactory.getCurrentSession().remove(coupon);
    }

    public void addCouponToClient(Long clientId, Long couponId){
        Client client = sessionFactory.getCurrentSession().get(Client.class, clientId);
        Coupon coupon = sessionFactory.getCurrentSession().get(Coupon.class, couponId);

        if(client == null){
            throw new IllegalArgumentException("Клиент с таким id не найден");
        }
        if(coupon == null){
            throw new IllegalArgumentException("Купон с таким id не найден");
        }
        if(client.getCouponList().contains(coupon)){
            throw new IllegalArgumentException("У клиента уже есть данный купон");
        }

        client.addCoupon(coupon);
    }

    public void deleteCouponFromClient(Long clientId, Long couponId){
        Client client = sessionFactory.getCurrentSession().get(Client.class, clientId);
        Coupon coupon = sessionFactory.getCurrentSession().get(Coupon.class, couponId);

        if(client == null){
            throw new IllegalArgumentException("Клиент с таким id не найден");
        }
        if(coupon == null){
            throw new IllegalArgumentException("Купон с таким id не найден");
        }

        client.deleteCoupon(coupon);
    }

    public Coupon getCouponById(Long id){
        Coupon coupon = sessionFactory.getCurrentSession().get(Coupon.class, id);
        if(coupon == null){
            throw new IllegalArgumentException("Купон с таким id не найден");
        }
        return coupon;
    }
}

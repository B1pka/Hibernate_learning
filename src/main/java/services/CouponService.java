package services;

import core.Coupon;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class CouponService {

    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    public CouponService(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public Coupon saveCoupon(Coupon coupon){
        return transactionHelper.executeInTransaction(session -> {
            session.persist(coupon);
            return coupon;
        });
    }

    public void deleteCoupon(Long id){
        transactionHelper.executeInTransaction(session -> {
            Coupon couponForDelete = session.get(Coupon.class, id);
            session.remove(couponForDelete);
        });
    }
}

package core;

import jakarta.persistence.*;

@Entity
@Table(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coupon_gen")
    @SequenceGenerator(name = "coupon_gen", sequenceName = "coupons_id_seq", allocationSize = 2)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Column(name = "promocode")
    private Long code;

    @Column(name = "discount")
    private Long discount;



    public Coupon() {

    }

    public Coupon(Long discount, Long code) {
        this.discount = discount;
        this.code = code;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }
}

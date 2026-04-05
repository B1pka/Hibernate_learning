package core;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "promocode", nullable = false)
    private Long code;

    @Column(name = "discount", nullable = false)
    private Long discount;

    @ManyToMany(mappedBy = "couponList")
    private List<Client> clientList = new ArrayList<>();

    public Coupon() {

    }

    public Coupon(Long discount, Long code) {
        this.discount = discount;
        this.code = code;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
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

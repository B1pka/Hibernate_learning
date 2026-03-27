package core;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clients_gen")
    @SequenceGenerator(name = "clients_gen", sequenceName = "clients_id_seq", allocationSize = 2)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;

    @OneToMany(mappedBy = "client",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order){
        orders.add(order);
        order.setClient(this);
    }

    public void deleteOrder(Order order){
        orders.remove(order);
        order.setClient(null);
    }

    @OneToOne(mappedBy = "client",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Profile profile;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "client_coupons",
    joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "coupon_id", referencedColumnName = "id"))
    private List<Coupon> couponList = new ArrayList<>();

    public void addCoupon(Coupon coupon){
        if(coupon == null){
            return;
        }
        if(!couponList.contains(coupon)){
            couponList.add(coupon);
        }
        if(!coupon.getClientList().contains(this)){
            coupon.getClientList().add(this);
        }
    }

    public void deleteCoupon(Coupon coupon){
        if(coupon == null){
            return;
        }

        couponList.remove(coupon);
        coupon.getClientList().remove(this);
    }

    public Client() {

    }

    public Client(LocalDateTime registrationDate, String name, String email) {
        this.registrationDate = registrationDate;
        this.name = name;
        this.email = email;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        if(this.profile != null){
            this.profile.setClient(null);
        }
        this.profile = profile;

        if(profile != null && profile.getClient() != this){
            profile.setClient(this);
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<Coupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<Coupon> couponList) {
        this.couponList = couponList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDate() {
        return registrationDate;
    }

    public void setDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

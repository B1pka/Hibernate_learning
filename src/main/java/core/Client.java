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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @OneToOne(mappedBy = "client", cascade = CascadeType.REMOVE)
    private Profile profile;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "client_coupons",
    joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "coupon_id", referencedColumnName = "id"))
    private List<Coupon> couponList = new ArrayList<>();
    public Client() {

    }

    public Client(LocalDateTime registrationDate, String name, String email) {
        this.registrationDate = registrationDate;
        this.name = name;
        this.email = email;
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

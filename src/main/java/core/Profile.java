package core;


import jakarta.persistence.*;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_gen")
    @SequenceGenerator(name = "profile_gen", sequenceName = "profile_id_gen", allocationSize = 2)
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id",
            referencedColumnName = "id",
            nullable = false,
            unique = true)
    private Client client;

    public Profile() {
    }

    public Profile(String phone, String address) {
        this.phone = phone;
        this.address = address;
    }

    public Profile(String phone, String address, Client client) {
        this.address = address;
        this.phone = phone;
        this.client = client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

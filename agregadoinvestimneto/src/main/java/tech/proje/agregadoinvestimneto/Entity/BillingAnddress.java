package tech.proje.agregadoinvestimneto.Entity;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Table_BillingAddress")
public class BillingAnddress {

    @Id
    @Column(name = "Account_id")
    private UUID billingAddressId;



    @Column(name = "Street")
    private String street;

    @Column(name = "Number")
    private String number;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "Account_id")
    private Account account;



    public BillingAnddress() {
    }


    public BillingAnddress(UUID billingAddressId, String street, String number, Account account) {
        this.billingAddressId = billingAddressId;
        this.street = street;
        this.number = number;
        this.account = account;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public void setBillingAddressId(UUID billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

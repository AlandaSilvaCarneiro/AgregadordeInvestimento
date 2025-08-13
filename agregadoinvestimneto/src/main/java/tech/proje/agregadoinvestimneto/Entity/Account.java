package tech.proje.agregadoinvestimneto.Entity;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Table_Accout")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id")
    private UUID idAccout;


    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario user;



    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
    @PrimaryKeyJoinColumn
    private BillingAnddress billingAnddress;

    public BillingAnddress getBillingAnddress() {
        return billingAnddress;
    }

    public List<AccountStock> getAccountStocks() {
        return accountStocks;
    }

    @OneToMany(mappedBy = "account")
    private List<AccountStock> accountStocks;
    public Account() {
    }
    public Account(String description, Usuario user, BillingAnddress billingAnddress, List<AccountStock> accountStocks, UUID idAccout) {
        this.description = description;
        this.user = user;
        this.billingAnddress = billingAnddress;
        this.accountStocks = accountStocks;
        this.idAccout = idAccout;
    }

    public Account(String description, Usuario user, BillingAnddress billingAnddress, List<AccountStock> accountStocks) {
        this.description = description;
        this.user = user;
        this.billingAnddress = billingAnddress;
        this.accountStocks = accountStocks;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }



    public UUID getIdAccout() {
        return idAccout;
    }

    public String getDescription() {
        return description;
    }


    public void setIdAccout(UUID idAccout) {
        this.idAccout = idAccout;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}



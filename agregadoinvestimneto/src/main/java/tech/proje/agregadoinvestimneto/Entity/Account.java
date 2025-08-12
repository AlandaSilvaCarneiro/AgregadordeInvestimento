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



    @OneToOne(mappedBy = "account")
    @PrimaryKeyJoinColumn
    private BillingAnddress billingAnddress;

    @OneToMany(mappedBy = "account")
    private List<AccountStock> accountStocks;
    public Account() {
    }


    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Account(UUID idAccout, String description) {
        this.idAccout = idAccout;
        this.description = description;
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



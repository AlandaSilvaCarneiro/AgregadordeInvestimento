package tech.proje.agregadoinvestimneto.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "TB_Account_Stock")
public class AccountStock {
    @EmbeddedId
    private AccountStockID id;

    @ManyToOne
    @MapsId("stockId")
    @JoinColumn(name = "stockId_id")
    private  Stock stock;


    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private  Account account;

    @Column(name = "Quantity")
    private Integer quantity;


    public AccountStock() {
    }

    public AccountStock(AccountStockID id, Stock stock, Account account, Integer quantity) {
        this.id = id;
        this.stock = stock;
        this.account = account;
        this.quantity = quantity;
    }

    public AccountStockID getId() {
        return id;
    }

    public Stock getStock() {
        return stock;
    }

    public Account getAccount() {
        return account;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setId(AccountStockID id) {
        this.id = id;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

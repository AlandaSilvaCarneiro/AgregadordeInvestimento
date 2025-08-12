package tech.proje.agregadoinvestimneto.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Table_Stock")
public class Stock {
    @Id
    @Column(name = "Stock_id")
    private String stockId;


    @Column(name = "Description")
    private String description;



    public Stock() {
    }

    public Stock(String stockId, String description) {
        this.stockId = stockId;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

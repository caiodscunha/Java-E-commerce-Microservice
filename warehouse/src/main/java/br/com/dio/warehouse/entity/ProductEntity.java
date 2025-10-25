package br.com.dio.warehouse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.*;

import static jakarta.persistence.CascadeType.ALL;
import static org.apache.coyote.ActionCode.AVAILABLE;

@Entity
@Getter
@Setter
@ToString
public class ProductEntity {

    @Id
    private UUID id;

    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "product", cascade = ALL)
    private Set<StockEntity> stocks = new HashSet<>();

    private StockEntity getStockWithMinSoldPrice() {
        return stocks.stream()
                .filter(s -> s.getStatus().equals(AVAILABLE))
                .min(Comparator.comparing(StockEntity::getSoldPrice))
                .orElseThrow();
    }

    public StockEntity decStock(){
        var stock = getStockWithMinSoldPrice();

        stock.decAmount();
        return stock;
    }

    public BigDecimal getPrice(){
        return getStockWithMinSoldPrice()
                .getSoldPrice();
    }

    @PrePersist
    private void prePersist() {
        this.id = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

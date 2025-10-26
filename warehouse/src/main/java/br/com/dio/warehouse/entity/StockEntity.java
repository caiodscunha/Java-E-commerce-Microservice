package br.com.dio.warehouse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import static br.com.dio.warehouse.entity.StockStatus.UNAVAILABLE;
import static jakarta.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@ToString
public class StockEntity {

    @Id
    private UUID id;

    private Long ammount;

    private BigDecimal boughtPrice;

    @Enumerated(STRING)
    private StockStatus status;

    private BigDecimal soldPrice;

    public boolean isUnavailable(){
        return status == UNAVAILABLE;
    }

    public void decAmount(){
        this.ammount -= 1;
        if(this.ammount == 0){
            this.status = UNAVAILABLE;
        }
    }

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @PrePersist
    private void prePersist() {
        this.id = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StockEntity that = (StockEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(ammount, that.ammount) && Objects.equals(boughtPrice, that.boughtPrice) && status == that.status && Objects.equals(soldPrice, that.soldPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ammount, boughtPrice, status, soldPrice);
    }
}

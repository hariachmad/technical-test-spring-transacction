// package technical.transaction_usecase.domain.entities;

// import java.util.HashSet;
// import java.util.Set;

// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToMany;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.Table;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Table(name = "product_sales")
// @Getter
// @Setter
// @NoArgsConstructor
// @Entity
// public class ProductSales {
//     @Id
//     String id;

//     @ManyToOne
//     @JoinColumn(name = "product_id")
//     Product product;

//     int quantity;

//     @ManyToMany(mappedBy = "productSales")
//     public Set<Transaction> transactions = new HashSet<>();
// }

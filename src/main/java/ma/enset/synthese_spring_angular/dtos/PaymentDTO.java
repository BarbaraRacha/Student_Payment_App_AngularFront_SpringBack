package ma.enset.synthese_spring_angular.dtos;


import jakarta.persistence.*;
import lombok.*;
import ma.enset.synthese_spring_angular.entities.PaymentStatus;
import ma.enset.synthese_spring_angular.entities.PaymentType;
import ma.enset.synthese_spring_angular.entities.Student;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder @Data @ToString
public class PaymentDTO {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private Double amount;
    private String code;
    private String programId;
    private PaymentType type;
    private PaymentStatus status;
}

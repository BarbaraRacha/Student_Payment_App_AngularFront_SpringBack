package ma.enset.synthese_spring_angular.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.enset.synthese_spring_angular.entities.PaymentStatus;
import ma.enset.synthese_spring_angular.entities.PaymentType;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewPaymentDTO {
    private LocalDate date;
    private Double amount;
    private String studentCode;
    private PaymentType type;
}

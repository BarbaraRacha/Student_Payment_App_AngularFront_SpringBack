package ma.enset.synthese_spring_angular.repositories;

import ma.enset.synthese_spring_angular.entities.Payment;
import ma.enset.synthese_spring_angular.entities.PaymentStatus;
import ma.enset.synthese_spring_angular.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByStudentCode(String studentCode);
    List<Payment> findByStatus(PaymentStatus paymentStatus);
    List<Payment> findByType(PaymentType paymentType);

}

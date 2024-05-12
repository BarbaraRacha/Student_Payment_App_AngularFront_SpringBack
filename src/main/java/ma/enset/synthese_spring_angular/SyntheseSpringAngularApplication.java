package ma.enset.synthese_spring_angular;

import ma.enset.synthese_spring_angular.entities.Payment;
import ma.enset.synthese_spring_angular.entities.PaymentStatus;
import ma.enset.synthese_spring_angular.entities.PaymentType;
import ma.enset.synthese_spring_angular.entities.Student;
import ma.enset.synthese_spring_angular.repositories.PaymentRepository;
import ma.enset.synthese_spring_angular.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class SyntheseSpringAngularApplication {

    public static void main(String[] args) {
        SpringApplication.run(SyntheseSpringAngularApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository,
                                        PaymentRepository paymentRepository) {
        return args -> {
            studentRepository.save(Student.builder()
                    .id(UUID.randomUUID().toString())
                    .firstName("Mohammed")
                    .code("1234")
                    .programId("SDIA").build());
            studentRepository.save(Student.builder()
                    .id(UUID.randomUUID().toString())
                    .firstName("Racha")
                    .code("5678")
                    .programId("SDIA").build());
            studentRepository.save(Student.builder()
                    .id(UUID.randomUUID().toString())
                    .firstName("Yasmin")
                    .code("91011")
                    .programId("GLSID").build());
            studentRepository.save(Student.builder()
                    .id(UUID.randomUUID().toString())
                    .firstName("Hatim")
                    .code("1213")
                    .programId("GLSID").build());


            PaymentType[] paymentTypes = PaymentType.values();
            Random random = new Random();
            studentRepository.findAll().forEach(st->{
                for (int i = 0; i <10; i++) {
                    int index = random.nextInt(paymentTypes.length);
                    Payment payment  = Payment.builder()
                            .amount(1000+(Math.random()*20000))
                            .type(paymentTypes[index])
                            .status (PaymentStatus.CREATED)
                            .date(LocalDate.now())
                            .student(st)
                            .build();
                    paymentRepository.save(payment);
                }
            });
        };
    }
}

package ma.enset.synthese_spring_angular.web;

import ma.enset.synthese_spring_angular.dtos.NewPaymentDTO;
import ma.enset.synthese_spring_angular.entities.Payment;
import ma.enset.synthese_spring_angular.entities.PaymentStatus;
import ma.enset.synthese_spring_angular.entities.PaymentType;
import ma.enset.synthese_spring_angular.entities.Student;
import ma.enset.synthese_spring_angular.repositories.PaymentRepository;
import ma.enset.synthese_spring_angular.repositories.StudentRepository;
import ma.enset.synthese_spring_angular.services.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class PaymentRestController {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;
    private PaymentService paymentService;

    public PaymentRestController(StudentRepository studentRepository, PaymentRepository paymentRepository, PaymentService paymentService) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
    }

    @GetMapping("/payments")
    public List<Payment> allPayments() {
        return paymentRepository.findAll();
    }
    @GetMapping("/students/{code}/payments")
    public List<Payment> paymentsByStudent(@PathVariable String code) {
        return paymentRepository.findByStudentCode(code);
    }
    @GetMapping("/payments/byStatus")
    public List<Payment> paymentsByStatus(@RequestParam PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }
    @GetMapping("/payments/byType")
    public List<Payment> paymentsByType(@RequestParam PaymentType type) {
        return paymentRepository.findByType(type);
    }
    @GetMapping("/payments/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentRepository.findById(id).get();
    }
    @GetMapping("/students")
    public List<Student> allStudents() {
        return studentRepository.findAll();
    }
    @GetMapping("/students/{code}")
    public Student getStudentByCode(@PathVariable String code) {
        return studentRepository.findByCode(code);
    }
    @GetMapping("/studentsByProgramId")
    public List<Student> getStudentByProgramId(String programId) {
        return studentRepository.findByProgramId(programId);
    }
    @PutMapping("/payments/{id}")
    public Payment updatePaymentStatus(@RequestParam PaymentStatus status, @PathVariable Long id) {
        return  this.paymentService.updatePaymentStatus(status, id);
    }

    @PostMapping(path="/payments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment (@RequestParam MultipartFile file, NewPaymentDTO newPaymentDTO) throws IOException {
        return this.paymentService.savePayment(file, newPaymentDTO);
    }

    @GetMapping(path="/payments/{id}/file", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable Long id) throws IOException {
        return this.paymentService.getPaymentFile(id);
    }
}

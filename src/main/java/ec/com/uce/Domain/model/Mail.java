package ec.com.uce.Domain.model;

import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table(name = "mail")
@Entity
public class Mail extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "seq_mail_generador", sequenceName = "seq_mail", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mail_generador")
    @Column(name="mail_id")
    private Integer id;

    @Column(name="mail_destinatario")
    private String destinatario;

    @Column(name="mail_asunto")
    private String asunto;

    @Column(name="mail_cuerpo")
    private String cuerpo;

    @Column(name="mail_fecha_envio")
    private LocalDateTime fechaEnvio;

    @Column(name="mail_leido")
    private Boolean leido;

    public Mail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }
    
}
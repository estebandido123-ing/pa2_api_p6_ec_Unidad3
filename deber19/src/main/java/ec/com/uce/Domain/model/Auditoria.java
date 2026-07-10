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

@Table(name = "auditoria")
@Entity
public class Auditoria extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "seq_auditoria", sequenceName = "seq_auditoria", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_auditoria")
    @Column(name="aud_id")
    private Integer id;

    @Column(name="aud_nombre_metodo")
    private String nombreMetodo;

    @Column(name="aud_argumentos", length = 1000) 
    private String argumentos;

    @Column(name="aud_fecha_hora")
    private LocalDateTime fechaHoraEjecucion;

    @Column(name="aud_tiempo_ms")
    private Long tiempoEjecucionMs;

    public Auditoria() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreMetodo() {
        return nombreMetodo;
    }

    public void setNombreMetodo(String nombreMetodo) {
        this.nombreMetodo = nombreMetodo;
    }

    public String getArgumentos() {
        return argumentos;
    }

    public void setArgumentos(String argumentos) {
        this.argumentos = argumentos;
    }

    public LocalDateTime getFechaHoraEjecucion() {
        return fechaHoraEjecucion;
    }

    public void setFechaHoraEjecucion(LocalDateTime fechaHoraEjecucion) {
        this.fechaHoraEjecucion = fechaHoraEjecucion;
    }

    public Long getTiempoEjecucionMs() {
        return tiempoEjecucionMs;
    }

    public void setTiempoEjecucionMs(Long tiempoEjecucionMs) {
        this.tiempoEjecucionMs = tiempoEjecucionMs;
    }

    
}
package ec.edu.uce.domain.model;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "auditoria")
public class Auditoria {

    @Id
    @SequenceGenerator(name = "seq_auditoria_generador", sequenceName = "seq_auditoria",allocationSize = 1)
    @GeneratedValue(generator = "seq_auditoria_generador",strategy = GenerationType.IDENTITY)
    @Column(name = "audi_id")
    private Long id;

    @Column(name = "audi_nombre_metodo")
    private String nombreMetodo;

    @Column(name = "audi_argumentos", columnDefinition = "TEXT")
    private String argumentos;

    @Column(name = "audi_fecha_hora_ejecucion")
    private LocalDateTime fechaHoraEjecucion;

    @Column(name = "audi_tiempo_ejecucion_ms")
    private Long tiempoEjecucionMs;

    // Getters y Setters
    
    public Long getId() {
        return id;
    }

    public Auditoria() {
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return "Auditoria [id=" + id + ", nombreMetodo=" + nombreMetodo + ", argumentos=" + argumentos
                + ", fechaHoraEjecucion=" + fechaHoraEjecucion + ", tiempoEjecucionMs=" + tiempoEjecucionMs + "]";
    }
    
}
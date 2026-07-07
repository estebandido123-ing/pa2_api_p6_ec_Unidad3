package ec.edu.uce.domain.model;

import java.time.LocalDate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table(name = "reporte")
@Entity
public class Reporte extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "seq_reporte_generador", sequenceName = "seq_reporte", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reporte_generador")
    @Column(name = "repor_id")
    private Integer id;

    @Column(name = "repor_titulo")
    private String titulo;

    @Column(name = "repor_descripcion")
    private String descripcion;

    @Column(name = "repor_fecha")
    private LocalDate fecha;

    @Column(name = "repor_formato")
    private String formato;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    @Override
    public String toString() {
        return "Reporte [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", fecha=" + fecha
                + ", formato=" + formato + "]";
    }

    

}
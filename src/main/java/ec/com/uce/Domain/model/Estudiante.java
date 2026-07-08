package ec.com.uce.Domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table(name = "estudiante")
@Entity
public class Estudiante extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "seq_estudiante", sequenceName = "seq_estudiante", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_estudiante")
    @Column(name="est_id")
    private Integer id;

    @Column(name="est_nombre")
    private String nombre;

    @Column(name="est_matricula")
    private String matricula;

    public Estudiante() {}

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    // ¡MUY IMPORTANTE PARA LA AUDITORÍA!
    @Override
    public String toString() {
        return "Estudiante [id=" + id + ", nombre=" + nombre + ", matricula=" + matricula + "]";
    }
}
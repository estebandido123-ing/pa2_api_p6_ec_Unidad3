package ec.com.uce.Domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table(name = "acta_electoral")
@Entity
public class ActaElectoral extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "seq_acta_generador", sequenceName = "seq_acta", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_acta_generador")
    @Column(name = "acta_id")
    private Integer id;

    @Column(name = "acta_provincia")
    private String provincia;

    @Column(name = "acta_canton")
    private String canton;

    @Column(name = "acta_parroquia")
    private String parroquia;

    @Column(name = "acta_votos_validos")
    private Integer votosValidos;

    @Column(name = "acta_votos_nulos")
    private Integer votosNulos;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getParroquia() {
        return parroquia;
    }

    public void setParroquia(String parroquia) {
        this.parroquia = parroquia;
    }

    public Integer getVotosValidos() {
        return votosValidos;
    }

    public void setVotosValidos(Integer votosValidos) {
        this.votosValidos = votosValidos;
    }

    public Integer getVotosNulos() {
        return votosNulos;
    }

    public void setVotosNulos(Integer votosNulos) {
        this.votosNulos = votosNulos;
    }
    
    @Override
    public String toString() {
        return "ActaElectoral [id=" + id + ", provincia=" + provincia + ", canton=" + canton 
                + ", parroquia=" + parroquia + ", votosValidos=" + votosValidos 
                + ", votosNulos=" + votosNulos + "]";
    }
}
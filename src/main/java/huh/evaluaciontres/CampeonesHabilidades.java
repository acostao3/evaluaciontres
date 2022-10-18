/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huh.evaluaciontres;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author yorsh
 */
@Entity
@Table(name = "campeones_habilidades")
@NamedQueries({
    @NamedQuery(name = "CampeonesHabilidades.findAll", query = "SELECT c FROM CampeonesHabilidades c"),
    @NamedQuery(name = "CampeonesHabilidades.findByCampeonNombre", query = "SELECT c FROM CampeonesHabilidades c WHERE c.campeonNombre = :campeonNombre"),
    @NamedQuery(name = "CampeonesHabilidades.findByHabilidad1", query = "SELECT c FROM CampeonesHabilidades c WHERE c.habilidad1 = :habilidad1"),
    @NamedQuery(name = "CampeonesHabilidades.findByHabilidad2", query = "SELECT c FROM CampeonesHabilidades c WHERE c.habilidad2 = :habilidad2"),
    @NamedQuery(name = "CampeonesHabilidades.findByHabilidad3", query = "SELECT c FROM CampeonesHabilidades c WHERE c.habilidad3 = :habilidad3"),
    @NamedQuery(name = "CampeonesHabilidades.findByHabilidad4", query = "SELECT c FROM CampeonesHabilidades c WHERE c.habilidad4 = :habilidad4")})
public class CampeonesHabilidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "campeon_nombre")
    private String campeonNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "habilidad_1")
    private String habilidad1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "habilidad_2")
    private String habilidad2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "habilidad_3")
    private String habilidad3;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "habilidad_4")
    private String habilidad4;

    public CampeonesHabilidades() {
    }

    public CampeonesHabilidades(String campeonNombre) {
        this.campeonNombre = campeonNombre;
    }

    public CampeonesHabilidades(String campeonNombre, String habilidad1, String habilidad2, String habilidad3, String habilidad4) {
        this.campeonNombre = campeonNombre;
        this.habilidad1 = habilidad1;
        this.habilidad2 = habilidad2;
        this.habilidad3 = habilidad3;
        this.habilidad4 = habilidad4;
    }

    public String getCampeonNombre() {
        return campeonNombre;
    }

    public void setCampeonNombre(String campeonNombre) {
        this.campeonNombre = campeonNombre;
    }

    public String getHabilidad1() {
        return habilidad1;
    }

    public void setHabilidad1(String habilidad1) {
        this.habilidad1 = habilidad1;
    }

    public String getHabilidad2() {
        return habilidad2;
    }

    public void setHabilidad2(String habilidad2) {
        this.habilidad2 = habilidad2;
    }

    public String getHabilidad3() {
        return habilidad3;
    }

    public void setHabilidad3(String habilidad3) {
        this.habilidad3 = habilidad3;
    }

    public String getHabilidad4() {
        return habilidad4;
    }

    public void setHabilidad4(String habilidad4) {
        this.habilidad4 = habilidad4;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (campeonNombre != null ? campeonNombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CampeonesHabilidades)) {
            return false;
        }
        CampeonesHabilidades other = (CampeonesHabilidades) object;
        if ((this.campeonNombre == null && other.campeonNombre != null) || (this.campeonNombre != null && !this.campeonNombre.equals(other.campeonNombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "huh.evaluaciontres.CampeonesHabilidades[ campeonNombre=" + campeonNombre + " ]";
    }
    
}

package com.jdreyes.webapi.prestamos.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "devices")
@Data
public class Device implements Serializable {
    private static final long SERIAL_VERSION_UID = 1L;

    @Id
    @Column(name = "mac")
    private String mac;

    @JoinColumn(name = "Id_Usuario", referencedColumnName = "Id_Usuario", updatable = false, insertable = false)
    @ManyToOne
    private AppUser user;

    @Column(name = "activo")
    private Integer act;

    @Transient
    private boolean activo;

    @PostLoad
    private void initTrasient(){
        this.activo = act == 1;
    }

    @Column(name = "Id_Usuario")
    private Integer idUsuario;

    public boolean isActivo() {
        return act == 1;
    }
}

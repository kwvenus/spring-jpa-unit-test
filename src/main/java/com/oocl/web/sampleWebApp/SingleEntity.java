package com.oocl.web.sampleWebApp;

import javax.persistence.*;

@Entity
public class SingleEntity {

    @Id
    public Long id;
    @Column(length = 10)
    public String name;

    public SingleEntity() {
    }

    public SingleEntity(String name) {
        this.name = name;
    }
//
//    @Override
//    public boolean equals(Object obj){
//        if (obj == null) return false;
//        if (this == obj) return true;
//        if (!(obj instanceof SingleEntity)) return false;
//
//    }

}

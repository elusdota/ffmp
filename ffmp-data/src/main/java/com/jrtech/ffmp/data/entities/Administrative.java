package com.jrtech.ffmp.data.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by suelmer on 2016/7/2.
 */

@Entity
@Table(name="administratives")
public class Administrative {
    @Id
    private String code;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "parent_code")
    List<Administrative> children;

    public Administrative() {

    }
    public Administrative(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Administrative> getChildren() {
        return children;
    }

    public void setChildren(List<Administrative> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return String.format("Administrative[code='%s', name='%s']", code, name);
    }
}

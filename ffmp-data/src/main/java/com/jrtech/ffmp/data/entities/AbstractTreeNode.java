package com.jrtech.ffmp.data.entities;

import java.util.*;
import javax.persistence.*;

/***
 * 所有树状数据结构的基类
 * @author Shawn
 *
 * @param <T>
 */
@MappedSuperclass
public class AbstractTreeNode<T> extends AbstractNamedObject {
	
	@ManyToOne
	private T parent = null;
	@OneToMany(mappedBy = "parent", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, orphanRemoval = true)
	protected Collection<T> children = new TreeSet<T>();

	protected AbstractTreeNode() {
		super();
	}

	protected AbstractTreeNode(String name) {
		super(name);
	}
	
	public T getParent() {
        return parent;
    }

    public void setParent(T parent) {
        this.parent = parent;
    }

	public Collection<T> getChildren() {
		return children;
	}
}
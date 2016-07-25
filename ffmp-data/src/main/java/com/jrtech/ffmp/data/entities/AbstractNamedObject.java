package com.jrtech.ffmp.data.entities;

import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 命名对象。在域中，所有需要名称的对象可从该对象继承。该对象实现了Comparable接口。
 */
@MappedSuperclass
public abstract class AbstractNamedObject extends AbstractDomainObject {
	
	@NotEmpty
    private String name = "";

    protected AbstractNamedObject() {
        super();
    }

    protected AbstractNamedObject(String name) {
        this();
        this.name = name;
    }

    /***
     * 取得对象的名称
     * @return 返回对象的名称
     */
    public String getName() {
        return name;
    }

    /***
     * 设置对象的名称
     * @param name 新的对象名称，不能为空。
     */
    public void setName(String name) {
		this.name = name;
	}

    /***
     * 重载的toString方法。显示为：{%s:[id: %d, name: %s, hash: %d]} 格式。表示对象的ID，名称，及哈希码。
     */
	@Override
    public String toString() {
        return String.format("{%s:[id: %s, name: %s, hash: %d]}", this.getClass().getName(), getId(), name, hashCode());
    }
}

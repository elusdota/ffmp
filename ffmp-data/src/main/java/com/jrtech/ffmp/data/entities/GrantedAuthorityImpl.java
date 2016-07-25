package com.jrtech.ffmp.data.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Shawn on 2015/12/4.
 */
@Entity
@Table(name = "Authorities")
@JsonIgnoreProperties(value = {"parent"})
public class GrantedAuthorityImpl extends AbstractTreeNode<GrantedAuthorityImpl> implements GrantedAuthority, Comparable<GrantedAuthorityImpl> {
	private static final long serialVersionUID = -6628438150688559470L;

	public String src;
	public String icons;
    public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getIcons() {
		return icons;
	}

	public void setIcons(String icons) {
		this.icons = icons;
	}

	protected GrantedAuthorityImpl() {
        super();
    }

    public GrantedAuthorityImpl(String name) {
        super(name);
    }

	@Override
	public String getAuthority() {
		return null == getParent() ? "/" + getName() : getParent().getAuthority() + "/" + getName();
	}
	
	/***
	 * 重载的hashCode方法。该方法仅使用名称生成哈希码。
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1028;
		result = prime * result + ((getAuthority() == null) ? 0 : getAuthority().hashCode());
		return result;
	}

	/***
	 * 重载的equals方法。该方法比较两个命名对象的名称是否相等。英文名称区分大小写。
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		GrantedAuthorityImpl other = (GrantedAuthorityImpl) obj;
		if (getAuthority() == null) {
			if (other.getAuthority() != null)
				return false;
		} else if (!getAuthority().equals(other.getAuthority()))
			return false;
		return true;
	}

	/***
	 * Comparable接口的方法实现。使用名称进行比较。英文名称区分大小写。
	 */
	@Override
	public int compareTo(GrantedAuthorityImpl o) {
		return getAuthority().compareTo(o.getAuthority());
	}

	@Override
	public String toString() {
		return String.format("%s: [%s]", getName(), getAuthority());
	}
}

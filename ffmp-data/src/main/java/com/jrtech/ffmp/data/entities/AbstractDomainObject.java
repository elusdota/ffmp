package com.jrtech.ffmp.data.entities;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.jrtech.ffmp.data.common.AccessType;
import org.hibernate.annotations.GenericGenerator;

/**
 * 所有域对象的基类
 */
@MappedSuperclass
public abstract class AbstractDomainObject {

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator") 
    private String id;

    @Version
    private long version = 0L;

    @Enumerated(EnumType.ORDINAL)
    private AccessType accessType = AccessType.USER;

	protected AbstractDomainObject() {
    }

	/***
	 * 域对象的ID。由Hibernate生成的UUID类型的字符串表示
	 */
    public String getId() {
        return id;
    }
    
    /***
     * 版本号。由Hibernate管理的数据库记录版本号
     */
    public long getVersion() {
		return version;
	}

    /***
     * 获取该条数据访问类型的标示。
     */
	public AccessType getAccessType() {
		return accessType;
	}

    /***
     * 设置该条数据访问类型的标示。
     */
	public void setAccessType(AccessType accessType) {
		this.accessType = accessType;
	}

	/***
	 * 重载的hashCode方法。该方法使用实体ID生成哈希码。
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	/***
	 * 重载的equals方法。该方法使用实体ID进行相等的比较。
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractDomainObject other = (AbstractDomainObject) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

}

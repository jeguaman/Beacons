package com.espe.edu.ec.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AreaBeacon.class)
public abstract class AreaBeacon_ {

	public static volatile SingularAttribute<AreaBeacon, Boolean> estado;
	public static volatile SingularAttribute<AreaBeacon, Integer> areaBeaconId;
	public static volatile SingularAttribute<AreaBeacon, Date> inserted;
	public static volatile SingularAttribute<AreaBeacon, Boolean> deleted;
	public static volatile SingularAttribute<AreaBeacon, Area> areaId;
	public static volatile SingularAttribute<AreaBeacon, Beacon> beaconId;
	public static volatile SingularAttribute<AreaBeacon, Date> updated;

}


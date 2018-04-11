package com.espe.edu.ec.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Beacon.class)
public abstract class Beacon_ {

	public static volatile SingularAttribute<Beacon, String> descripcion;
	public static volatile SingularAttribute<Beacon, Date> inserted;
	public static volatile SingularAttribute<Beacon, Boolean> deleted;
	public static volatile SingularAttribute<Beacon, String> major;
	public static volatile SingularAttribute<Beacon, String> minor;
	public static volatile SingularAttribute<Beacon, byte[]> imagen;
	public static volatile ListAttribute<Beacon, AreaBeacon> areaBeaconList;
	public static volatile SingularAttribute<Beacon, Integer> beaconId;
	public static volatile SingularAttribute<Beacon, String> uuid;
	public static volatile SingularAttribute<Beacon, String> nombre;
	public static volatile SingularAttribute<Beacon, Date> updated;

}


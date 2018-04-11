package com.espe.edu.ec.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Area.class)
public abstract class Area_ {

	public static volatile SingularAttribute<Area, String> descripcion;
	public static volatile SingularAttribute<Area, Integer> areaId;
	public static volatile SingularAttribute<Area, Date> inserted;
	public static volatile SingularAttribute<Area, Boolean> deleted;
	public static volatile ListAttribute<Area, Registro> registroList;
	public static volatile ListAttribute<Area, Lugar> lugarList;
	public static volatile SingularAttribute<Area, String> titulo;
	public static volatile SingularAttribute<Area, byte[]> imagen;
	public static volatile ListAttribute<Area, AreaBeacon> areaBeaconList;
	public static volatile ListAttribute<Area, Notificacion> notificacionList;
	public static volatile SingularAttribute<Area, Date> updated;

}


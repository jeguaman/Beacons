package com.espe.edu.ec.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Notificacion.class)
public abstract class Notificacion_ {

	public static volatile SingularAttribute<Notificacion, String> descripcion;
	public static volatile SingularAttribute<Notificacion, String> tipo;
	public static volatile SingularAttribute<Notificacion, Date> inserted;
	public static volatile SingularAttribute<Notificacion, Boolean> deleted;
	public static volatile SingularAttribute<Notificacion, Area> areaId;
	public static volatile SingularAttribute<Notificacion, Date> updated;
	public static volatile SingularAttribute<Notificacion, Integer> notificacionId;

}


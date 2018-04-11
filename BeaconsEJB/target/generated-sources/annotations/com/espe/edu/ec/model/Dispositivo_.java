package com.espe.edu.ec.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Dispositivo.class)
public abstract class Dispositivo_ {

	public static volatile SingularAttribute<Dispositivo, String> descripcion;
	public static volatile SingularAttribute<Dispositivo, Date> inserted;
	public static volatile SingularAttribute<Dispositivo, Boolean> deleted;
	public static volatile ListAttribute<Dispositivo, Registro> registroList;
	public static volatile SingularAttribute<Dispositivo, Integer> dispositivoId;
	public static volatile SingularAttribute<Dispositivo, String> imei;
	public static volatile SingularAttribute<Dispositivo, Date> updated;

}


package com.espe.edu.ec.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Registro.class)
public abstract class Registro_ {

	public static volatile SingularAttribute<Registro, String> tipo;
	public static volatile SingularAttribute<Registro, Date> inserted;
	public static volatile SingularAttribute<Registro, Boolean> deleted;
	public static volatile SingularAttribute<Registro, Area> areaId;
	public static volatile SingularAttribute<Registro, Dispositivo> dispositivoId;
	public static volatile SingularAttribute<Registro, Integer> registroId;

}


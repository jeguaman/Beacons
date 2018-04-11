package com.espe.edu.ec.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Perfil.class)
public abstract class Perfil_ {

	public static volatile SingularAttribute<Perfil, String> descripcion;
	public static volatile SingularAttribute<Perfil, String> codigo;
	public static volatile ListAttribute<Perfil, AsignacionPerfil> asignacionPerfilList;
	public static volatile SingularAttribute<Perfil, Date> inserted;
	public static volatile SingularAttribute<Perfil, Boolean> deleted;
	public static volatile SingularAttribute<Perfil, Integer> perfilId;
	public static volatile SingularAttribute<Perfil, String> nombre;
	public static volatile SingularAttribute<Perfil, Date> updated;

}


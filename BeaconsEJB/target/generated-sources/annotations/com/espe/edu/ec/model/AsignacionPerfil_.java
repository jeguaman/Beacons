package com.espe.edu.ec.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AsignacionPerfil.class)
public abstract class AsignacionPerfil_ {

	public static volatile SingularAttribute<AsignacionPerfil, Date> inserted;
	public static volatile SingularAttribute<AsignacionPerfil, Boolean> deleted;
	public static volatile SingularAttribute<AsignacionPerfil, Perfil> perfilId;
	public static volatile SingularAttribute<AsignacionPerfil, Integer> asigPerfilId;
	public static volatile SingularAttribute<AsignacionPerfil, Date> updated;
	public static volatile SingularAttribute<AsignacionPerfil, Usuario> usuarioId;

}


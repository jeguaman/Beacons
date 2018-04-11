package com.espe.edu.ec.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, String> apellidos;
	public static volatile ListAttribute<Usuario, AsignacionPerfil> asignacionPerfilList;
	public static volatile SingularAttribute<Usuario, Date> inserted;
	public static volatile SingularAttribute<Usuario, Boolean> deleted;
	public static volatile SingularAttribute<Usuario, String> contrasenia;
	public static volatile SingularAttribute<Usuario, String> nombreUsuario;
	public static volatile SingularAttribute<Usuario, Integer> usuarioId;
	public static volatile SingularAttribute<Usuario, String> correoElectronico;
	public static volatile SingularAttribute<Usuario, Date> updated;
	public static volatile SingularAttribute<Usuario, String> nombres;

}


package com.bancoexterior.parametros.limitesusuarios.config;


public class Codigos {

	
	public class Ambientes{
		
		private Ambientes() {
			super();
		}
		
		public static final String DESARROLLO = "des";
		public static final String CALIDAD    = "qa";
		public static final String PRODUCCION = "pro";
	}
	
	
	public class CodRespuesta{
		
		private CodRespuesta() {
			super();
		}
		
		//ok
		public static final String C0000 = "0000";
		public static final String C0001 = "0001";
		
		//entrada moneda
		public static final String CDE1000 = "1000";
		public static final String CDE1001 = "1001";
		public static final String CDE1002 = "1002";
		public static final String CDE1003 = "1003";
		public static final String CDE1004 = "1004";
		public static final String CDE1005 = "1005";
		public static final String CDE1006 = "1006";
		public static final String CDE1007 = "1007";
		public static final String CDE1008 = "1008";
		
		
		//entrada tasa
		public static final String CDE1009 = "1009";
		public static final String CDE1010 = "1010";
		public static final String CDE1011 = "1011";
		public static final String CDE1012 = "1012";
		public static final String CDE1013 = "1013";
		
		public static final String CDE2000 = "2000";
		public static final String CDE2001 = "2001";
		public static final String CDE2003 = "2003";
		public static final String CDE2004 = "2004";
		
		
		
		////GENERAL
    	public static final String CME6000 = "6000";
    	public static final String CME6001 = "6001";
		public static final String CME6002 = "6002";
	
		
	}
	
	
	public class Annotation{
		 
		private Annotation() {
			super();
		}
		
		 public static final String OBJECTDEFAULT     = "[Objeto vacio]";
		 public static final String FECHADEFAULT      = "[Fecha invalida]";
	 }
	
	
	public class ParamConfig{
		
		private ParamConfig() {
			super();
		}
		
		public static final String CANAL           = "^[a-zA-Z\\-0-9]{1,4}$";
		public static final String IDSESIONVALID   = "uuuuMMddHHmmss";
		public static final String CODUSUARIO      = "^.{2,10}$";
		public static final String IDUSUARIO       = "^.{1,15}$";
		public static final String CODMONEDA       = "^[a-zA-Z\\-0-9]{1,3}$";
		public static final String DESCRIPCION     = "^.{1,500}$";
		public static final String CODALTERNO      = "^.{1,10}$";
		public static final String MONTO           = "^\\d{1,3}(\\.?\\d{3})*(,\\d{1,2})?$";
		
	}
	
	
	public class Constantes{
		
		private Constantes() {
			super();
		}
		
		public static final String MONEDADEFAULT                      = "000";
		public static final String TRUE                               = "true";
		public static final String CODALTERNODEFAULT                  = "1234";
		public static final String BLANK                              = "";
		public static final String RES                                = "res.";
		public static final String SUBSTRING_COD_OK                   = "0";
		public static final String SUBSTRING_COD_UNPROCESSABLE_ENTITY = "1";
		public static final String INTERNAL_SERVER_ERROR              = "6";
		public static final String XCLIENTIP                          = "X-Client-IP";
		public static final String CONTENT_TYPE                       = "Content-Type";
		public static final String ACCEPT_CHARSET                     = "Accept-Charset";
		public static final String UTF8                               = "UTF-8";
		public static final String TERMINAL                           = "CONVENIO1";
		public static final String N_A                                = "N/A";
		public static final String FECHA_HORA                         = "yyyy-MM-dd HH:mm:ss";
		public static final String PLECA                              = "|";
		public static final String APP_JSON                           = "application/json";
		public static final String EXC                                = "Exc:";
		public static final String ERROR                              = "@@Error";
		public static final String RIF                                = "J000000000";
		public static final String CEDULA                             = "V00000000";
		public static final String TELEFONO                           = "00000000000000";
	}
	
	
	
	public class Servicios{
		
		private Servicios() {
			super();
		}
		
		//limitesGenerales
		
		public static final String LIMITESUSUARIOSURLV1       = "/v1/parametros/limitesclientes";
		public static final String LIMITESUSUARIOSPARAMETERIDURLV1       = "/v1/parametros/limitesusuarios/codMoneda/{codMoneda}/tipoTransaccion/{tipoTransaccion}/codIbs/{codIbs}";
		
		
		//Consultas
		public static final String LIMITESCLIENTE            = "Convenio1-LimitesCliente";
		public static final String LIMITESCLIENTEACTUALIZAR  = "Convenio1-LimitesCliente Actualizacion";
		public static final String LIMITESCLIENTECONTROLLERI = "[==== INICIO Convenio n° 1 LimitesCliente - Controller ====]";
		public static final String LIMITESCLIENTECONTROLLERF = "[==== FIN Convenio n° 1 LimitesCliente - Controller ====]";
		public static final String LIMITESCLIENTESERVICEICREAR    = "==== INICIO Convenio 1 - LimitesCliente - Service - Crear ====";
		public static final String LIMITESCLIENTESERVICEFCREAR    = "==== FIN Convenio 1 - LimitesCliente - Service - Crear ====";
		public static final String LIMITESCLIENTESERVICEIACTUALIZAR    = "==== INICIO Convenio 1 - LimitesCliente - Service - Actualizar ====";
		public static final String LIMITESCLIENTESERVICEFACTUALIZAR    = "==== FIN Convenio 1 - LimitesCliente - Service - Actualizar ====";
		
		public static final String LIMITESCLIENTESCONSULTASERVICEI    = "==== INICIO Convenio 1 - LimitesCliente Consultas ====";
		public static final String LIMITESCLIENTESCONSULTASERVICEF    = "==== FIN Convenio 1 - LimitesCliente Consultas ====";
		
		//Auditoria
		
		public static final String ASERVICEI    = "====== INICIO Registrar Auditoria ======";
		public static final String ASERVICEF    = "====== FIN Registrar Auditoria ======";
		
	}
	
	
	public class ExceptionMessages{
		
		private ExceptionMessages() {
			super();
		}
		
		public static final String UNIRESTHTTPE         = "HttpStatusCodeException: %1$s";
		public static final String UNIRESTBODYE         = "ResponseBody: %1$s";
		public static final String UNIRESTE             = "Exception UNI: %1$s";
		
		public static final String AUPRINTERROR         = "ERROR:{}";
		public static final String AUPRINTERRORMENSA    = "Ocurrio un error en registrar Auditoria:{}";
	}
		
	
	
	public class InfoMessages {
		
		private InfoMessages() {
			super();
		}
		// Auditoria Service
    	public static final String AUREQUEST                = "Request = [{}]";
    	public static final String AUPRINTINFO              = "registrar Auditoria respuesta: ";
	}
		
	public class SQLUtils{
		
		private SQLUtils() {
			super();
		}
		
		public static final String SELECTLIMITESCLIENTES ="SELECT cod_ibs, cod_moneda, tipo_transaccion, monto_min, monto_max, monto_tope, monto_mensual, monto_diario,"
				+ "flag_activo, cod_usuario, fecha_modificacion "
				+ "FROM \"Convenio1\".\"Limites_personalizados\" "
				+ "where cod_ibs= (case when ?1 = '' then cod_ibs else ?1 end) "
				+ "and cod_moneda= (case when ?2 = '' then cod_moneda else ?2 end) "
				+ "and tipo_transaccion= (case when ?3 = '' then tipo_transaccion else ?3 end) "
				+ "and case when  ?4 = 'si' then flag_activo= ?5 else flag_activo = flag_activo end";
			  
				
	}	
		
	
	
}

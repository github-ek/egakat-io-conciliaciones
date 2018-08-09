package com.egakat.io.conciliaciones.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

import com.egakat.integration.files.domain.Registro;
import com.egakat.integration.files.enums.EstadoRegistroType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "saldos_inventario")
@DynamicUpdate
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class SaldoInventario extends Registro {

	public static final String CLIENTE_CODIGO = "CLIENTE_CODIGO";
	public static final String FECHA = "FECHA";
	public static final String FECHA_CORTE = "FECHA_CORTE";
	public static final String PRODUCTO_CODIGO_ALTERNO = "PRODUCTO_CODIGO_ALTERNO";
	public static final String BODEGA_CODIGO_ALTERNO = "BODEGA_CODIGO_ALTERNO";
	public static final String ESTADO_CONCILIACION_CODIGO_ALTERNO = "ESTADO_CONCILIACION_CODIGO_ALTERNO";
	public static final String UNIDAD_MEDIDA_CODIGO_ALTERNO = "UNIDAD_MEDIDA_CODIGO_ALTERNO";
	public static final String UNIDADES_POR_EMPAQUE = "UNIDADES_POR_EMPAQUE";
	public static final String CANTIDAD = "CANTIDAD";
	public static final String VALOR_UNITARIO = "VALOR_UNITARIO";

	@Column(name = "cliente_codigo", length = 20)
	@NotNull
	private String clienteCodigo;

	@Column(name = "fecha")
	@NotNull
	private LocalDate fecha;

	@Column(name = "fecha_corte")
	@NotNull
	private LocalDateTime fechaCorte;

	@Column(name = "producto_codigo_alterno", length = 50)
	@NotNull
	private String productoCodigoAlterno;

	@Column(name = "bodega_codigo_alterno", length = 50)
	@NotNull
	private String bodegaCodigoAlterno;

	@Column(name = "estado_conciliacion_codigo_alterno", length = 50)
	@NotNull
	private String estadoConciliacionCodigoAlterno;

	@Column(name = "unidad_medida_codigo_alterno", length = 50)
	@NotNull
	private String unidadMedidaCodigoAlterno;

	@Column(name = "unidades_por_empaque")
	private Integer unidadesPorEmpaque;

	@Column(name = "cantidad")
	@NotNull
	private BigDecimal cantidad;

	@Column(name = "valor_unitario")
	private Integer valorUnitario;

	@Column(name = "id_cliente")
	private Long idCliente;

	@Column(name = "id_producto")
	private Long idProducto;

	@Column(name = "id_bodega")
	private Long idBodega;

	@Column(name = "id_estado_conciliacion")
	private Long idEstadoConciliacion;

	@Column(name = "id_unidad_medida")
	private Long idUnidadMedida;

	@Override
	public String getIdCorrelacion() {
		return String.valueOf(getNumeroLinea());
	}

	@Override
	public Object getObjectValueFromProperty(String property) {
		switch (property) {
		case CLIENTE_CODIGO:
		case PRODUCTO_CODIGO_ALTERNO:
		case BODEGA_CODIGO_ALTERNO:
		case ESTADO_CONCILIACION_CODIGO_ALTERNO:
		case UNIDAD_MEDIDA_CODIGO_ALTERNO:
			return getStringValueFromHomologableProperty(property);

		case FECHA:
			return getFecha();
		case FECHA_CORTE:
			return getFechaCorte();
		case UNIDADES_POR_EMPAQUE:
			return getUnidadesPorEmpaque();
		case CANTIDAD:
			return getCantidad();
		case VALOR_UNITARIO:
			return getValorUnitario();
		default:
			return null;
		}
	}

	@Override
	public boolean isHomologableProperty(String property) {
		switch (property) {
		case CLIENTE_CODIGO:
		case PRODUCTO_CODIGO_ALTERNO:
		case BODEGA_CODIGO_ALTERNO:
		case ESTADO_CONCILIACION_CODIGO_ALTERNO:
		case UNIDAD_MEDIDA_CODIGO_ALTERNO:
			return true;
		default:
			return false;
		}
	}

	@Override
	protected String getStringValueFromHomologableProperty(String property) {
		switch (property) {
		case CLIENTE_CODIGO:
			return getClienteCodigo();
		case PRODUCTO_CODIGO_ALTERNO:
			return getProductoCodigoAlterno();
		case BODEGA_CODIGO_ALTERNO:
			return getBodegaCodigoAlterno();
		case ESTADO_CONCILIACION_CODIGO_ALTERNO:
			return getEstadoConciliacionCodigoAlterno();
		case UNIDAD_MEDIDA_CODIGO_ALTERNO:
			return getUnidadMedidaCodigoAlterno();
		default:
			return null;
		}
	}

	@Override
	protected Object getObjectValueFromHomologousProperty(String property) {
		switch (property) {
		case CLIENTE_CODIGO:
			return getIdCliente();
		case PRODUCTO_CODIGO_ALTERNO:
			return getIdProducto();
		case BODEGA_CODIGO_ALTERNO:
			return getIdBodega();
		case ESTADO_CONCILIACION_CODIGO_ALTERNO:
			return getIdEstadoConciliacion();
		case UNIDAD_MEDIDA_CODIGO_ALTERNO:
			return getIdUnidadMedida();
		default:
			return null;
		}
	}

	@Builder
	public SaldoInventario(Long id, int version, LocalDateTime fechaCreacion, String creadoPor,
			LocalDateTime fechaModificacion, String modificadoPor, Long idArchivo, @NotNull EstadoRegistroType estado,
			int numeroLinea, @NotNull String clienteCodigo, @NotNull LocalDate fecha, @NotNull LocalDateTime fechaCorte,
			@NotNull String productoCodigoAlterno, @NotNull String bodegaCodigoAlterno,
			@NotNull String estadoConciliacionCodigoAlterno, @NotNull String unidadMedidaCodigoAlterno,
			Integer unidadesPorEmpaque, @NotNull BigDecimal cantidad, Integer valorUnitario, Long idCliente,
			Long idProducto, Long idBodega, Long idEstadoConciliacion, Long idUnidadMedida) {
		super(id, version, fechaCreacion, creadoPor, fechaModificacion, modificadoPor, idArchivo, estado, numeroLinea);
		this.clienteCodigo = clienteCodigo;
		this.fecha = fecha;
		this.fechaCorte = fechaCorte;
		this.productoCodigoAlterno = productoCodigoAlterno;
		this.bodegaCodigoAlterno = bodegaCodigoAlterno;
		this.estadoConciliacionCodigoAlterno = estadoConciliacionCodigoAlterno;
		this.unidadMedidaCodigoAlterno = unidadMedidaCodigoAlterno;
		this.unidadesPorEmpaque = unidadesPorEmpaque;
		this.cantidad = cantidad;
		this.valorUnitario = valorUnitario;
		this.idCliente = idCliente;
		this.idProducto = idProducto;
		this.idBodega = idBodega;
		this.idEstadoConciliacion = idEstadoConciliacion;
		this.idUnidadMedida = idUnidadMedida;
	}
}

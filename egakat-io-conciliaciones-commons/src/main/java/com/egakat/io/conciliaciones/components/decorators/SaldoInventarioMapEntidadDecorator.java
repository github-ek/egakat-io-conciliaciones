package com.egakat.io.conciliaciones.components.decorators;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.egakat.integration.commons.archivos.dto.EtlRequestDto;
import com.egakat.integration.commons.archivos.dto.RegistroDto;
import com.egakat.integration.commons.archivos.enums.EstadoRegistroType;
import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.components.decorators.MapEntidadDecorator;
import com.egakat.io.conciliaciones.domain.SaldoInventario;

import lombok.val;

public class SaldoInventarioMapEntidadDecorator extends MapEntidadDecorator<SaldoInventario, Long> {

	public SaldoInventarioMapEntidadDecorator(Decorator<SaldoInventario, Long> inner) {
		super(inner);
	}

	@Override
	protected SaldoInventario map(EtlRequestDto<SaldoInventario, Long> archivo,
			RegistroDto<SaldoInventario, Long> registro) {
		LocalDate fecha = getLocalDate(archivo, registro, SaldoInventario.FECHA);
		LocalDateTime fechaCorte = getLocalDateTime(archivo, registro, SaldoInventario.FECHA_CORTE);
		Integer unidadesPorEmpaque = getInteger(archivo, registro, SaldoInventario.UNIDADES_POR_EMPAQUE);
		BigDecimal cantidad = getBigDecimal(archivo, registro, SaldoInventario.CANTIDAD);
		BigDecimal valorUnitario = getBigDecimal(archivo, registro, SaldoInventario.VALOR_UNITARIO);

		val result = new SaldoInventario();
		result.setIdArchivo(archivo.getArchivo().getId());
		result.setEstado(EstadoRegistroType.ESTRUCTURA_VALIDA);
		result.setNumeroLinea(registro.getNumeroLinea());
		result.setVersion(0);

		result.setClienteCodigo(getString(archivo, registro, SaldoInventario.CLIENTE_CODIGO));
		result.setFecha(fecha);
		result.setFechaCorte(fechaCorte);
		result.setProductoCodigoAlterno(getString(archivo, registro, SaldoInventario.PRODUCTO_CODIGO_ALTERNO));
		result.setBodegaCodigoAlterno(getString(archivo, registro, SaldoInventario.BODEGA_CODIGO_ALTERNO));
		result.setEstadoConciliacionCodigoAlterno(
				getString(archivo, registro, SaldoInventario.ESTADO_CONCILIACION_CODIGO_ALTERNO));
		result.setUnidadMedidaCodigoAlterno(getString(archivo, registro, SaldoInventario.UNIDAD_MEDIDA_CODIGO_ALTERNO));
		result.setUnidadesPorEmpaque(unidadesPorEmpaque);
		result.setCantidad(cantidad);
		result.setValorUnitario(valorUnitario.intValue());

		return result;
	}
}
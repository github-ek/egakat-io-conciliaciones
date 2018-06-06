package com.egakat.io.conciliaciones.components.decorators;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.components.decorators.MapEntidadDecorator;
import com.egakat.integration.files.dto.EtlRequestDto;
import com.egakat.integration.files.dto.RegistroDto;
import com.egakat.integration.files.enums.EstadoRegistroType;
import com.egakat.io.conciliaciones.domain.SaldoInventario;

import lombok.val;

public class SaldoInventarioMapEntidadDecorator extends MapEntidadDecorator<SaldoInventario,Long> {
	
	public SaldoInventarioMapEntidadDecorator(Decorator<SaldoInventario,Long> inner) {
		super(inner);
	}

	@Override
	protected SaldoInventario map(EtlRequestDto<SaldoInventario,Long> archivo, RegistroDto<SaldoInventario,Long> registro) {
		LocalDate fecha = getLocalDate(archivo, registro, SaldoInventario.FECHA);
		LocalDateTime fechaCorte = getLocalDateTime(archivo, registro, SaldoInventario.FECHA_CORTE);
		Integer unidadesPorEmpaque = getInteger(archivo, registro, SaldoInventario.UNIDADES_POR_EMPAQUE);
		BigDecimal cantidad = getBigDecimal(archivo, registro, SaldoInventario.CANTIDAD);
		BigDecimal valorUnitario = getBigDecimal(archivo, registro, SaldoInventario.VALOR_UNITARIO);

		// @formatter:off
 		val result = SaldoInventario.builder()
				.idArchivo(archivo.getArchivo().getId())
				.estado(EstadoRegistroType.ESTRUCTURA_VALIDA)
				.numeroLinea(registro.getNumeroLinea())
				.version(0)
				
				.clienteCodigo(getString(archivo, registro, SaldoInventario.CLIENTE_CODIGO))
				.fecha(fecha)
				.fechaCorte(fechaCorte)
				.productoCodigoAlterno(getString(archivo, registro, SaldoInventario.PRODUCTO_CODIGO_ALTERNO))
				.bodegaCodigoAlterno(getString(archivo, registro, SaldoInventario.BODEGA_CODIGO_ALTERNO))
				.estadoConciliacionCodigoAlterno(getString(archivo, registro, SaldoInventario.ESTADO_CONCILIACION_CODIGO_ALTERNO))
				.unidadMedidaCodigoAlterno(getString(archivo, registro, SaldoInventario.UNIDAD_MEDIDA_CODIGO_ALTERNO))
				.unidadesPorEmpaque(unidadesPorEmpaque)
				.cantidad(cantidad)
				.valorUnitario(valorUnitario.intValue())
				.build();
		// @formatter:on

		return result;
	}
}
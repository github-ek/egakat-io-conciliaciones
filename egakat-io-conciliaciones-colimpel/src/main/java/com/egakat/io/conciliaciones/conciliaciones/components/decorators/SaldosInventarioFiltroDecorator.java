package com.egakat.io.conciliaciones.conciliaciones.components.decorators;

import static java.util.stream.Collectors.toList;

import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.files.dto.EtlRequestDto;
import com.egakat.io.conciliaciones.domain.SaldoInventario;

import lombok.val;

public class SaldosInventarioFiltroDecorator extends Decorator<SaldoInventario, Long> {

	public SaldosInventarioFiltroDecorator(Decorator<SaldoInventario, Long> inner) {
		super(inner);
	}

	@Override
	public EtlRequestDto<SaldoInventario, Long> transformar(EtlRequestDto<SaldoInventario, Long> archivo) {
		val result = super.transformar(archivo);

		val campo = result.getCampo(SaldoInventario.BODEGA_CODIGO_ALTERNO);
		val bodegas = campo.get().valoresPermitidos();

		// @formatter:off
		val registros = result
				.getRegistros()
				.stream()
				.filter(a -> bodegas.contains(a.getDatos().get(SaldoInventario.BODEGA_CODIGO_ALTERNO)))
				.collect(toList());
		// @formatter:on

		result.getRegistros().clear();
		result.getRegistros().addAll(registros);

		return result;
	}
}

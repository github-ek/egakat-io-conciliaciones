package com.egakat.io.conciliaciones.heinz.components.decorators;

import java.util.stream.Collectors;

import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.files.dto.EtlRequestDto;
import com.egakat.io.conciliaciones.domain.SaldoInventario;

import lombok.val;

public class SaldosInventarioProteusFiltroDecorator extends Decorator<SaldoInventario, Long> {

	public SaldosInventarioProteusFiltroDecorator(Decorator<SaldoInventario, Long> inner) {
		super(inner);
	}

	@Override
	public EtlRequestDto<SaldoInventario, Long> transformar(EtlRequestDto<SaldoInventario, Long> archivo) {
		val result = super.transformar(archivo);

		// @formatter:off
		val registros = result
				.getRegistros()
				.stream()
				.filter(a -> !a.getDatos().get(SaldoInventario.PRODUCTO_CODIGO_ALTERNO).isEmpty())
				.collect(Collectors.toList());

		result.getRegistros().clear();
		result.getRegistros().addAll(registros);
		// @formatter:on

		return result;
	}
}

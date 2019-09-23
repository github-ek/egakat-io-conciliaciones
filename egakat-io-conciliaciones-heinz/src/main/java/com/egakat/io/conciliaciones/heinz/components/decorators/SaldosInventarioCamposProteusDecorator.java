package com.egakat.io.conciliaciones.heinz.components.decorators;

import org.apache.commons.lang3.StringUtils;

import com.egakat.integration.commons.archivos.dto.EtlRequestDto;
import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.io.conciliaciones.domain.SaldoInventario;

import lombok.val;

public class SaldosInventarioCamposProteusDecorator extends Decorator<SaldoInventario, Long> {

	public SaldosInventarioCamposProteusDecorator(Decorator<SaldoInventario, Long> inner) {
		super(inner);
	}

	@Override
	public EtlRequestDto<SaldoInventario, Long> transformar(EtlRequestDto<SaldoInventario, Long> archivo) {
		val result = super.transformar(archivo);

		val registros = result.getRegistros();

		for (val registro : registros) {
			String cantidad = registro.getDatos().get(SaldoInventario.CANTIDAD);

			cantidad = StringUtils.remove(cantidad, ' ');
			registro.getDatos().put(SaldoInventario.CANTIDAD, cantidad);
		}

		return result;
	}
}

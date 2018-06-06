package com.egakat.io.conciliaciones.heinz.components.decorators;

import org.apache.commons.lang3.StringUtils;

import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.files.dto.EtlRequestDto;
import com.egakat.io.conciliaciones.domain.SaldoInventario;

import lombok.val;

public class SaldosInventarioCamposDecorator extends Decorator<SaldoInventario, Long> {

	public SaldosInventarioCamposDecorator(Decorator<SaldoInventario, Long> inner) {
		super(inner);
	}

	@Override
	public EtlRequestDto<SaldoInventario, Long> transformar(EtlRequestDto<SaldoInventario, Long> archivo) {
		val result = super.transformar(archivo);

		val registros = result.getRegistros();

		for (val registro : registros) {
			String bodegaCodigoAlterno = registro.getDatos().get(SaldoInventario.BODEGA_CODIGO_ALTERNO);

			bodegaCodigoAlterno = StringUtils.leftPad(bodegaCodigoAlterno, 4, "0");
			registro.getDatos().put(SaldoInventario.BODEGA_CODIGO_ALTERNO, bodegaCodigoAlterno);
		}

		return result;
	}
}

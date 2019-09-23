package com.egakat.io.conciliaciones.heinz.components.decorators;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.egakat.integration.commons.archivos.dto.EtlRequestDto;
import com.egakat.integration.commons.archivos.dto.RegistroDto;
import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.io.conciliaciones.domain.SaldoInventario;

import lombok.val;

public class SaldosInventarioCorregirLineasDecorator extends Decorator<SaldoInventario, Long> {

	public SaldosInventarioCorregirLineasDecorator(Decorator<SaldoInventario, Long> inner) {
		super(inner);
	}

	@Override
	public EtlRequestDto<SaldoInventario, Long> transformar(EtlRequestDto<SaldoInventario, Long> archivo) {
		val result = super.transformar(archivo);
		val registros = new ArrayList<RegistroDto<SaldoInventario, Long>>();

		int n = result.getRegistros().size();
		if (n > 0) {
			val campo = result.getCampo(SaldoInventario.FECHA_CORTE);
			val separadorCampos = result.getUnescapedSeparadorCampos();
			val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd H:mm:ss");
			val fechaCorte = LocalDateTime.now().format(formatter);

			for (int i = 4; i < n; i++) {
				val registro = result.getRegistros().get(i);
				String s;
				if (i == 4) {
					s = campo.get().getNombre();
				} else {
					s = fechaCorte;
				}
				registro.setLinea(s + separadorCampos + registro.getLinea());

				registros.add(registro);
			}
		}

		result.getRegistros().clear();
		result.getRegistros().addAll(registros);

		return result;
	}
}

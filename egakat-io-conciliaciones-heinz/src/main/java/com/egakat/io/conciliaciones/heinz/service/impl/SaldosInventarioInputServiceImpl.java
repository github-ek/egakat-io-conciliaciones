package com.egakat.io.conciliaciones.heinz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.service.impl.ArchivoExcelInputServiceImpl;
import com.egakat.io.conciliaciones.components.decorators.SaldoInventarioMapEntidadDecorator;
import com.egakat.io.conciliaciones.domain.SaldoInventario;
import com.egakat.io.conciliaciones.heinz.components.decorators.SaldosInventarioCamposDecorator;
import com.egakat.io.conciliaciones.heinz.components.decorators.SaldosInventarioCorregirLineasDecorator;
import com.egakat.io.conciliaciones.heinz.components.decorators.SaldosInventarioFiltroDecorator;
import com.egakat.io.conciliaciones.heinz.service.api.SaldosInventarioInputService;
import com.egakat.io.conciliaciones.repository.SaldoInventarioRepository;

public class SaldosInventarioInputServiceImpl extends ArchivoExcelInputServiceImpl<SaldoInventario> implements SaldosInventarioInputService{

	private static final String TIPO_ARCHIVO_CODIGO = "HEINZ_SALDOS_INVENTARIO";
	
	private static final String WORKSHEET_NAME = "1";

	@Autowired
	private SaldoInventarioRepository repository;

	@Override
	public String getTipoArchivoCodigo() {
		return TIPO_ARCHIVO_CODIGO;
	}

	@Override
	protected String getWorkSheetName() {
		return WORKSHEET_NAME;
	}

	@Override
	protected SaldoInventarioRepository getRepository() {
		return repository;
	}
	
	@Override
	protected Decorator<SaldoInventario, Long> getFiltrarRegistrosDecorator(Decorator<SaldoInventario, Long> inner) {
		return new SaldosInventarioFiltroDecorator(inner);
	}

	@Override
	protected Decorator<SaldoInventario, Long> getEnriquecerCamposDecorator(Decorator<SaldoInventario, Long> inner) {
		return new SaldosInventarioCamposDecorator(inner);
	}

	
	@Override
	protected Decorator<SaldoInventario, Long> getLimpiarLineasDecorator(Decorator<SaldoInventario, Long> inner) {
		return new SaldosInventarioCorregirLineasDecorator(inner);
	}

	@Override
	protected Decorator<SaldoInventario, Long> getMapEntidadDecorator(Decorator<SaldoInventario, Long> inner) {
		return new SaldoInventarioMapEntidadDecorator(inner);
	}
}

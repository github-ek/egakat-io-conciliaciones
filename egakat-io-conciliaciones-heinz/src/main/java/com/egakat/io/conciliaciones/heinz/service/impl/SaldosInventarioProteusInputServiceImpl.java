package com.egakat.io.conciliaciones.heinz.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.components.readers.ExcelWorkSheetReader;
import com.egakat.integration.core.files.components.readers.Reader;
import com.egakat.integration.core.files.service.impl.ArchivoExcelInputServiceImpl;
import com.egakat.io.conciliaciones.components.decorators.SaldoInventarioMapEntidadDecorator;
import com.egakat.io.conciliaciones.domain.SaldoInventario;
import com.egakat.io.conciliaciones.heinz.components.decorators.SaldosInventarioCamposProteusDecorator;
import com.egakat.io.conciliaciones.heinz.components.decorators.SaldosInventarioProteusFiltroDecorator;
import com.egakat.io.conciliaciones.heinz.service.api.SaldosInventarioInputService;
import com.egakat.io.conciliaciones.repository.SaldoInventarioRepository;

import lombok.val;

@Service
@Transactional(readOnly = true)
public class SaldosInventarioProteusInputServiceImpl extends ArchivoExcelInputServiceImpl<SaldoInventario> implements SaldosInventarioInputService{

	private static final String TIPO_ARCHIVO_CODIGO = "HEINZ_SALDOS_INVENTARIO_PROTEUS";
	
	private static final String WORKSHEET_NAME = "0";

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
	protected Reader getReader() {
		val reader = (ExcelWorkSheetReader) super.getReader();
		reader.setRowOffset(1);
		return reader;
	}

	@Override
	protected SaldoInventarioRepository getRepository() {
		return repository;
	}
	
	@Override
	protected Decorator<SaldoInventario, Long> getFiltrarRegistrosDecorator(Decorator<SaldoInventario, Long> inner) {
		return new SaldosInventarioProteusFiltroDecorator(inner);
	}

	@Override
	protected Decorator<SaldoInventario, Long> getEnriquecerCamposDecorator(Decorator<SaldoInventario, Long> inner) {
		return new SaldosInventarioCamposProteusDecorator(inner);
	}

	@Override
	protected Decorator<SaldoInventario, Long> getMapEntidadDecorator(Decorator<SaldoInventario, Long> inner) {
		return new SaldoInventarioMapEntidadDecorator(inner);
	}
}

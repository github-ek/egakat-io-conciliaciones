package com.egakat.io.conciliaciones.conciliaciones.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.service.impl.ArchivoPlanoInputServiceImpl;
import com.egakat.io.conciliaciones.components.decorators.SaldoInventarioMapEntidadDecorator;
import com.egakat.io.conciliaciones.conciliaciones.service.api.SaldosInventarioInputService;
import com.egakat.io.conciliaciones.domain.SaldoInventario;
import com.egakat.io.conciliaciones.repository.SaldoInventarioRepository;

@Service
public class SaldosInventarioInputServiceImpl extends ArchivoPlanoInputServiceImpl<SaldoInventario> implements SaldosInventarioInputService{

	private static final String TIPO_ARCHIVO_CODIGO = "COLIMPEL_SALDOS_INVENTARIO";
	
	@Autowired
	private SaldoInventarioRepository repository;

	@Override
	public String getTipoArchivoCodigo() {
		return TIPO_ARCHIVO_CODIGO;
	}

	@Override
	protected SaldoInventarioRepository getRepository() {
		return repository;
	}

	@Override
	protected Decorator<SaldoInventario, Long> getMapEntidadDecorator(Decorator<SaldoInventario, Long> inner) {
		return new SaldoInventarioMapEntidadDecorator(inner);
	}
}

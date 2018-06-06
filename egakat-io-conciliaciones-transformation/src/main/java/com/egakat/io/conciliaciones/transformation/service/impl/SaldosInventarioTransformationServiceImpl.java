package com.egakat.io.conciliaciones.transformation.service.impl;

import static com.egakat.io.conciliaciones.domain.SaldoInventario.BODEGA_CODIGO_ALTERNO;
import static com.egakat.io.conciliaciones.domain.SaldoInventario.CLIENTE_CODIGO;
import static com.egakat.io.conciliaciones.domain.SaldoInventario.ESTADO_CONCILIACION_CODIGO_ALTERNO;
import static com.egakat.io.conciliaciones.domain.SaldoInventario.PRODUCTO_CODIGO_ALTERNO;
import static com.egakat.io.conciliaciones.domain.SaldoInventario.UNIDAD_MEDIDA_CODIGO_ALTERNO;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egakat.econnect.conciliaciones.client.service.api.lookup.ConciliacionesLookUpService;
import com.egakat.econnect.maestros.client.service.api.lookup.LookUpService;
import com.egakat.integration.core.transformation.service.impl.TransformationServiceImpl;
import com.egakat.integration.files.dto.CampoDto;
import com.egakat.io.conciliaciones.domain.SaldoInventario;
import com.egakat.io.conciliaciones.repository.SaldoInventarioRepository;
import com.egakat.io.conciliaciones.transformation.service.api.SaldosInventarioTransformationService;

import lombok.val;

@Service
public class SaldosInventarioTransformationServiceImpl extends TransformationServiceImpl<SaldoInventario> implements SaldosInventarioTransformationService{

	@Autowired
	private SaldoInventarioRepository repository;

	@Autowired
	private LookUpService lookUpService;

	@Autowired
	private ConciliacionesLookUpService conciliacioneslookUpService;

	@Override
	protected SaldoInventarioRepository getRepository() {
		return repository;
	}

	protected LookUpService getLookUpService() {
		return lookUpService;
	}
	
	public ConciliacionesLookUpService getConciliacioneslookUpService() {
		return conciliacioneslookUpService;
	}

	public SaldosInventarioTransformationServiceImpl() {
		super();
	}

	@Override
	protected void translateField(SaldoInventario registro, CampoDto campo, String value) {
		switch (campo.getCodigo()) {
		case CLIENTE_CODIGO:
			translateCliente(registro, value);
			break;
		case PRODUCTO_CODIGO_ALTERNO:
			translateProducto(registro, value);
			break;
		case UNIDAD_MEDIDA_CODIGO_ALTERNO:
			if (StringUtils.isEmpty(value)) {
				if (!campo.isIncluir()) {
					break;
				}
			}			
			translateUnidadMedida(registro, value);
			break;
		case BODEGA_CODIGO_ALTERNO:
			translateBodega(registro, value);
			break;
		case ESTADO_CONCILIACION_CODIGO_ALTERNO:
			translateEstadoConciliacion(registro, value);
			break;
		default:
		}
	}

	private void translateCliente(SaldoInventario registro, String value) {
		registro.setIdCliente(null);
		val id = getLookUpService().findClienteIdByCodigo(value);
		registro.setIdCliente(id);
	}

	private void translateProducto(SaldoInventario registro, String value) {
		registro.setIdProducto(null);
		val cliente = registro.getIdCliente();
		if (cliente != null) {
			val id = getLookUpService().findProductoIdByClienteIdAndCodigo(cliente.longValue(), value);
			registro.setIdProducto(id);
		}
	}

	private void translateUnidadMedida(SaldoInventario registro, String value) {
		registro.setIdUnidadMedida(null);
		Long id = null;

		if (!"".equals(value)) {
			id = getLookUpService().findUnidadMedidaIdByCodigo(value);
		} else {
			val producto = registro.getIdProducto();
			val bodega = registro.getIdBodega();
			if ((producto != null) && (bodega != null)) {
				id = getLookUpService().findUnidadMedidaDeReciboIdByProductoIdAndBodegaId(producto, bodega);
			}
		}

		registro.setIdUnidadMedida(id);
	}

	private void translateBodega(SaldoInventario registro, String value) {
		registro.setIdBodega(null);
		val id = getLookUpService().findBodegaIdByCodigo(value);
		registro.setIdBodega(id);
	}

	private void translateEstadoConciliacion(SaldoInventario registro, String value) {
		registro.setIdEstadoConciliacion(null);
		val id = getConciliacioneslookUpService().findEstadoConciliacionIdByEstadoConciliacionCodigo(value);
		registro.setIdEstadoConciliacion(id);
	}
}
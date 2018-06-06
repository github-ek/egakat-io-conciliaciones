package com.egakat.io.conciliaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.egakat.integration.files.enums.EstadoRegistroType;
import com.egakat.integration.files.repository.RegistroRepository;
import com.egakat.io.conciliaciones.domain.SaldoInventario;

public interface SaldoInventarioRepository extends RegistroRepository<SaldoInventario> {

	@Override
	@Query("SELECT DISTINCT a.idArchivo FROM SaldoInventario a WHERE a.estado IN (:estados) ORDER BY a.idArchivo")
	List<Long> findAllArchivoIdByEstadoIn(@Param("estados") List<EstadoRegistroType> estado);
}

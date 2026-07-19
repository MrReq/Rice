package io.agrimind.backend.repository;

import io.agrimind.backend.entity.Field;
import io.agrimind.backend.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {

    List<Field> findByFarm(Farm farm);

    Optional<Field> findByIdAndFarm(Long id, Farm farm);
}
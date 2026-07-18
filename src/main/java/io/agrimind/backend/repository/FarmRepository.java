package io.agrimind.backend.repository;

import io.agrimind.backend.entity.Farm;
import io.agrimind.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

    List<Farm> findByOwner(User owner);

}
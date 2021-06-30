package ru.vtb.productprofile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vtb.productprofile.domain.term.LogOperations;

@Repository
public interface LogOperationsRepository extends JpaRepository<LogOperations, Long> {
}

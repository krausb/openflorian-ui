package de.openflorian.data.dao;

import de.openflorian.data.model.Operation;
import org.springframework.data.repository.CrudRepository;

/**
 * {@link OperationRepository}
 *
 * @author Bastian Kraus <bofh@k-hive.de>
 */
public interface OperationRepository extends CrudRepository<Operation, Integer> {
    // empty
}

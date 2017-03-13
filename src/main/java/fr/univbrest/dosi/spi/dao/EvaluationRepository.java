package fr.univbrest.dosi.spi.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.univbrest.dosi.spi.bean.Evaluation;


/**
 * @author DOSI
 *
 */
@RepositoryRestResource(collectionResourceRel = "evaluation", path = "evaluation")
public interface EvaluationRepository extends PagingAndSortingRepository<Evaluation, Long> {

}

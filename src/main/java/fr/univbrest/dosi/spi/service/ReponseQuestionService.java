package fr.univbrest.dosi.spi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.spi.bean.ReponseQuestion;
import fr.univbrest.dosi.spi.bean.ReponseQuestionPK;
import fr.univbrest.dosi.spi.dao.QuestionEvaluationRepository;
import fr.univbrest.dosi.spi.dao.ReponseEvaluationRepository;
import fr.univbrest.dosi.spi.dao.ReponseQuestionRepository;

@Service
public class ReponseQuestionService {
	
	@Autowired
	private ReponseQuestionRepository reponseQuestionRepository;
	
	@Autowired private QuestionEvaluationRepository questionEvaluationRepository;
	@Autowired private ReponseEvaluationRepository reponseEvaluationRepository;
	
	public final Iterable<ReponseQuestion> getReponsesQuestion(){
		return reponseQuestionRepository.findAll();
	}
	
	public void delete(ReponseQuestionPK pk){
		reponseQuestionRepository.delete(pk);
	}
	
	public ReponseQuestion ajouterReponseQuestion(ReponseQuestion reponse){
		return reponseQuestionRepository.save(reponse);
	}
	
	public ReponseQuestion getReponseEvaluation(ReponseQuestionPK id){
		return reponseQuestionRepository.findOne(id);
	}
	
	public final Iterable<ReponseQuestion> getReponseQuestionByEvaluation(Long id){
		return reponseQuestionRepository.findByIdReponseEvaluation(id);
	}
	
	public final Iterable<ReponseQuestion> getReponseQuestionByRepEvalAndQuestionEval(Long repEval , Long questionEval){
		return reponseQuestionRepository.findByIdReponseEvalAndIdQuestionEval(repEval, questionEval);
	}
	
	/*public final ReponseQuestion getReponseByPK(ReponseQuestionPK pk){
		return reponseQuestionRepository.findOne(pk);
	}*/
	
	
	

}

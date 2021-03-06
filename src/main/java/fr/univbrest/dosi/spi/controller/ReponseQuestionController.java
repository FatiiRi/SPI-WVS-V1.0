package fr.univbrest.dosi.spi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.RepQstRepEvalQstEval;
import fr.univbrest.dosi.spi.bean.ReponseEvaluation;
import fr.univbrest.dosi.spi.bean.ReponseQuestion;
import fr.univbrest.dosi.spi.bean.ReponseQuestionPK;
import fr.univbrest.dosi.spi.service.QuestionEvaluationService;
import fr.univbrest.dosi.spi.service.ReponseEvaluationService;
import fr.univbrest.dosi.spi.service.ReponseQuestionService;

@RestController
@RequestMapping("reponseQuestion")
public class ReponseQuestionController {
	
	@Autowired
	private ReponseQuestionService reponseQuestionService;
	
	@Autowired
	private QuestionEvaluationService questionEvalService;
	
	@Autowired
	private ReponseEvaluationService reponseEvaluationService;
	
	@RequestMapping(value = "/", produces="application/json", method = RequestMethod.GET)
	public final Iterable<ReponseQuestion> reponsesQuestion(){
		return reponseQuestionService.getReponsesQuestion();
	}
	
	@RequestMapping(value = "/",method = RequestMethod.DELETE)
	public final void supprimer(
			@PathVariable(value="reponseQuestionPK") final ReponseQuestionPK reponseQuestionPK
			){
		reponseQuestionService.delete(reponseQuestionPK);
		
	}
	
	@RequestMapping(value="/",method = RequestMethod.POST, consumes ="application/json")
	public final ReponseQuestion ajouter(
			@RequestBody final RepQstRepEvalQstEval repQstRepEvalQstEval
			){
		
		ReponseQuestion rq = new ReponseQuestion();
		rq = repQstRepEvalQstEval.getReponseQuestion();
		rq.setId(new ReponseQuestionPK(repQstRepEvalQstEval.getIdReponseEvaluation(), repQstRepEvalQstEval.getIdQuestionEvaluation()));
//		rq.setReponseEvaluation(repQstRepEvalQstEval.getReponseEvaluation());
		return reponseQuestionService.ajouterReponseQuestion(rq);
		
	}
	
	
	@RequestMapping(value="/",method = RequestMethod.PUT, consumes ="application/json")
	public final ReponseQuestion modifier(
			@RequestBody final ReponseQuestion reponseQuestion
			){
		reponseQuestionService.ajouterReponseQuestion(reponseQuestion);
		return reponseQuestion;
	}
	
	@RequestMapping(value = "/{idEvaluation}" ,produces="application/json",method = RequestMethod.GET)
	public final Iterable<ReponseQuestion> reponsesByIdEvaluation(
			@PathVariable(value="idEvaluation") final Long idEvaluation
			){
		return reponseQuestionService.getReponseQuestionByEvaluation(idEvaluation);
	}
	
	@RequestMapping(value = "/reponses/{idRepEval}/{idQuestEval}" ,produces="application/json",method = RequestMethod.GET )
	public final Iterable<ReponseQuestion> reponsesByIdEvalAndQuestionEval(
			@PathVariable(value="idRepEval") final Long idRepEval,
			@PathVariable(value="idQuestEval") final Long idQuestEval
			){
			
		/*ReponseQuestionPK pk = new ReponseQuestionPK();
		pk.setIdQuestionEvaluation(idQuestEval);
		pk.setIdReponseEvaluation(idRepEval);
				return reponseQuestionService.getReponseByPK(pk);	*/
		return reponseQuestionService.getReponseQuestionByRepEvalAndQuestionEval(idRepEval, idQuestEval);
	}

}

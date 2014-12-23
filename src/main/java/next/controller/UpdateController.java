package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class UpdateController extends AbstractController {
	private static final Logger logger = LoggerFactory
			.getLogger(ShowController.class);
	private QuestionDao questionDao = new QuestionDao(); 
	private AnswerDao answerDao = new AnswerDao();
	private Answer answer;

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String questionIdString = ServletRequestUtils.getRequiredStringParameter(request, "questionId");
		String writer = ServletRequestUtils.getRequiredStringParameter(request,"writer");
		String contents = ServletRequestUtils.getRequiredStringParameter(request, "contents");
		long questionId = Long.parseLong(questionIdString);
		answer = new Answer(writer, contents, questionId);

		answerDao.insert(answer);
		questionDao.plusCountOfComment(questionId);
		
		ModelAndView mav = jsonView();
		return mav;
	}
}

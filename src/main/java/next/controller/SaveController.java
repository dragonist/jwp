package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class SaveController extends AbstractController {
	private QuestionDao questionDao = new QuestionDao();
	private List<Question> questions;

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String writer = ServletRequestUtils.getRequiredStringParameter(request,"writer");
		String title = ServletRequestUtils.getRequiredStringParameter(request,"title");
		String contents = ServletRequestUtils.getRequiredStringParameter(request, "contents");

		Question expected = new Question(writer, title, contents);
		questionDao.insert(expected);

		questions = questionDao.findAll();

		ModelAndView mav = jstlView("list.jsp");
		mav.addObject("questions", questions);
		return mav;
	}
}

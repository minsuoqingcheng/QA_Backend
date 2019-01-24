package cn.nju.edu.se.controller;

import cn.nju.edu.se.entity.Answer;
import cn.nju.edu.se.entity.Question;
import cn.nju.edu.se.entity.User;
import cn.nju.edu.se.service.impl.AnswerServiceImpl;
import cn.nju.edu.se.service.impl.QuestionServiceImpl;
import cn.nju.edu.se.service.impl.UserMapServiceImpl;
import cn.nju.edu.se.service.impl.UserServiceImpl;
import cn.nju.edu.se.vo.AnswerVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by PandaLin on 2019/1/24.
 */
@RestController
@RequestMapping(value = "/answer")
public class AnswerController {
    @Autowired
    private AnswerServiceImpl answerService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private QuestionServiceImpl questionService;

    @Autowired
    private UserMapServiceImpl userMapService;

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ApiOperation(value = "提交回答", response = Answer.class)
    public Answer submitAnswer(@RequestBody Map<String,String> requestBody){
        int userId = Integer.parseInt(requestBody.get("userId"));
        User user = userService.getUserById(userId);
        int questionId = Integer.parseInt(requestBody.get("questionId"));
        Question question = questionService.getQuestionById(questionId);
        String content = requestBody.get("content");
        String time = requestBody.get("time");
        int state = Integer.parseInt(requestBody.get("state"));
        Answer answer = new Answer();
        answer.setUser(user);
        answer.setQuestion(question);
        answer.setContent(content);
        answer.setHide(0);
        answer.setState(state);
        answer.setTime(time);
        return answerService.submitAnswer(answer);
    }

    @RequestMapping(value = "/question", method = RequestMethod.POST)
    @ApiOperation(value = "获得问题的所有回答", response = Answer.class, responseContainer = "List")
    public List<Answer> listAnswersForQuestion(@RequestBody Map<String,String> requestBody){
        int questionId = Integer.parseInt(requestBody.get("questionId"));
        return answerService.listAllAnswersForQuestion(questionId);
    }

    @RequestMapping(value = "/user/submitted", method = RequestMethod.POST)
    @ApiOperation(value = "获得用户的所有回答", response = Answer.class, responseContainer = "List")
    public List<Answer> listUserAnswers(@RequestBody Map<String,String> requestBody){
        int userId = Integer.parseInt(requestBody.get("userId"));
        return answerService.listAllAnswersForUser(userId);
    }



    @RequestMapping(value = "/user/focused", method = RequestMethod.POST)
    @ApiOperation(value = "获得关注的用户的最新回答", response = Answer.class, responseContainer = "List")
    public List<Answer> getFocusedUserAnswers(@RequestBody Map<String,String> requestBody){
        int userId = Integer.parseInt(requestBody.get("userId"));
        return answerService.listFocusedUserAnswers(userId);
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ApiOperation(value = "根据回答ID删除回答", response = Answer.class)
    public Answer deleteAnswer(@RequestBody Map<String,String> requestBody){

        int answerId = Integer.parseInt(requestBody.get("answerId"));
        return answerService.delAnswer(answerId);
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ApiOperation(value = "查询回答详情及是否关注", response = AnswerVO.class)
    public AnswerVO getAnswer(@RequestBody Map<String,String> requestBody){
        int userId = Integer.parseInt(requestBody.get("userId"));
        int answerId = Integer.parseInt(requestBody.get("answerId"));
        Answer answer = answerService.getAnswerById(answerId);
        Boolean answererFocused = userMapService.checkUserFocused(userId, answer.getUser().getId());

        AnswerVO answerVO = new AnswerVO();
        answerVO.setAnswer(answer);
        answerVO.setAnswererFocused(answererFocused);

        return answerVO;

    }


}

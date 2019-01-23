package cn.nju.edu.se.controller;

import cn.nju.edu.se.entity.Question;
import cn.nju.edu.se.entity.UserQuestionMap;
import cn.nju.edu.se.form.QuestionID;
import cn.nju.edu.se.form.UserID;
import cn.nju.edu.se.service.QuestionService;
import cn.nju.edu.se.service.UserQuestionMapService;
import cn.nju.edu.se.vo.AnswerForQuestion;
import cn.nju.edu.se.vo.QuestionVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * Created by PandaLin on 2019/1/21.
 */

@RestController
@RequestMapping(value = "/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserQuestionMapService userQuestionMapService;


    /**
     * 获得一个问题
     * @param requestBody 问题ID
     * @return Question
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ApiOperation(value = "根据问题ID获得问题内容", response = Question.class)
    public Question getOneQuestion(@RequestBody QuestionID requestBody){
        int questionId = Integer.parseInt(requestBody.getQuestionId());
        return questionService.getOneQuestion(questionId);
    }

    /**
     * 删除一个问题
     * @param requestBody 问题ID
     * @return Question
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ApiOperation(value = "根据问题ID隐藏/删除问题", response = Question.class)
    public Question hideOneQuestion(@RequestBody QuestionID requestBody){
        int questionId = Integer.parseInt(requestBody.getQuestionId());
        return questionService.hideQuestion(questionId);
    }

    /**
     * 关闭一个问题
     * @param requestBody 问题ID
     * @return Question
     */
    @RequestMapping(value = "/close", method = RequestMethod.POST)
    @ApiOperation(value = "根据问题ID关闭问题", response = Question.class)
    public Question closeQuestion(@RequestBody QuestionID requestBody){
        int questionId = Integer.parseInt(requestBody.getQuestionId());
        return questionService.controlQuestion(questionId, 0);
    }

    /**
     * 打开一个问题
     * @param requestBody 问题ID
     * @return Question
     */
    @RequestMapping(value = "/open", method = RequestMethod.POST)
    @ApiOperation(value = "根据问题ID打开", response = Question.class)
    public Question openQuestion(@RequestBody QuestionID requestBody){
        int questionId = Integer.parseInt(requestBody.getQuestionId());
        return questionService.controlQuestion(questionId, 1);
    }

    /**
     * 查询问题列表方法
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiOperation(value = "查询问题列表方法", response = Question.class, responseContainer = "List")
    public List<Question> getAllQuestions(){
        return questionService.listAllQuestions();
    }

    /**
     * 查询用户所有提问的问题
     * @param requestBody 用户ID
     * @return List<Question>
     */
    @RequestMapping(value = "/user/ask", method = RequestMethod.POST)
    @ApiOperation(value = "根据用户ID查询用户所有提问的问题", response = Question.class, responseContainer = "List")
    public List<Question> listQuestionsForUser(@RequestBody UserID requestBody){
        int userId = Integer.parseInt(requestBody.getUserId());
        return questionService.listUserSubmittedQuestions(userId);
    }

    /**
     * 查询用户回答过的问题
     * @param requestBody 用户ID
     * @return List<AnswerForQuestion>
     */
    @RequestMapping(value = "/user/answer", method = RequestMethod.POST)
    @ApiOperation(value = "根据用户ID查询用户所有提问的问题", response = AnswerForQuestion.class, responseContainer = "List")
    public List<AnswerForQuestion> listAnswerForUser(@RequestBody UserID requestBody){
        int userId = Integer.parseInt(requestBody.getUserId());
        return questionService.listUserAnsweredQuestions(userId);
    }

    @RequestMapping(value = "/focus", method = RequestMethod.POST)
    public UserQuestionMap focusQuestion(@RequestBody Map<String,String> requestBody){
        int userId = Integer.parseInt(requestBody.get("userId"));
        int questionId = Integer.parseInt(requestBody.get("questionId"));
        return userQuestionMapService.focusQuestion(userId,questionId);
    }

    @RequestMapping(value = "/ignore", method = RequestMethod.POST)
    public void notFocusQuestion(@RequestBody Map<String,String> requestBody){
        int userId = Integer.parseInt(requestBody.get("userId"));
        int questionId = Integer.parseInt(requestBody.get("questionId"));
        userQuestionMapService.notFocusQuestion(userId,questionId);
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public QuestionVO checkFocusQuestion(@RequestBody Map<String,String> requestBody){
        int userId = Integer.parseInt(requestBody.get("userId"));
        int questionId = Integer.parseInt(requestBody.get("questionId"));

        Question question =questionService.getOneQuestion(questionId);
        Boolean isMyQuestion = false;
        if(question.getUser().getId()==userId){
            isMyQuestion = true;
        }
        Boolean focused =  userQuestionMapService.checkFocus(userId,questionId);

        QuestionVO questionVO = new QuestionVO();
        questionVO.setFocused(focused);
        questionVO.setMyQuestion(isMyQuestion);
        questionVO.setQuestion(question);

        return questionVO;
    }

    @RequestMapping(value = "/focused", method = RequestMethod.POST)
    public List<Question> userFocusedQuestions(@RequestBody Map<String,String> requestBody){
        int userId = Integer.parseInt(requestBody.get("userId"));
        return userQuestionMapService.userFocusedQuestions(userId);
    }



}

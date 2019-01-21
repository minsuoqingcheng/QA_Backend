package cn.nju.edu.se.controller;

import cn.nju.edu.se.entity.Question;
import cn.nju.edu.se.form.QuestionID;
import cn.nju.edu.se.form.UserID;
import cn.nju.edu.se.service.impl.QuestionServiceImpl;
import cn.nju.edu.se.vo.AnswerForQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by PandaLin on 2019/1/21.
 */

@RestController
@RequestMapping(value = "/question")
public class QuestionController {
    @Autowired
    private QuestionServiceImpl questionService;

    /**
     * 获得一个问题
     * @param requestBody 问题ID
     * @return Question
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST)
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
    public Question openQuestion(@RequestBody QuestionID requestBody){
        int questionId = Integer.parseInt(requestBody.getQuestionId());
        return questionService.controlQuestion(questionId, 1);
    }

    /**
     * 查询问题列表方法
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Question> getAllQuestions(){
        return questionService.listAllQuestions();
    }

    /**
     * 查询用户所有提问的问题
     * @param requestBody 用户ID
     * @return List<Question>
     */
    @RequestMapping(value = "/user/ask", method = RequestMethod.POST)
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
    public List<AnswerForQuestion> listAnswerForUser(@RequestBody UserID requestBody){
        int userId = Integer.parseInt(requestBody.getUserId());
        return questionService.listUserAnsweredQuestions(userId);
    }



}

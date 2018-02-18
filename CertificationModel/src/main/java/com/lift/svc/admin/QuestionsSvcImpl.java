package com.lift.svc.admin;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lift.model.Answers;
import com.lift.model.Questions;
import com.lift.model.UserQuestions;
import com.lift.so.enums.ExamTypes;
import com.lift.so.enums.QuestionStatus;
import com.lift.so.questions.QuestionsSO;
import com.lift.svc.CertificationSvc;
import com.lift.util.HibernateUtil;
import com.lift.util.ResHelper;

@Service
public class QuestionsSvcImpl implements QuestionsSvc, HibernateUtil {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private CertificationSvc certificationSvc;

	@Override
	@Transactional
	public void addQuestions(QuestionsSO questionsSO) {
		Session session = getSession();
		Questions questions = new Questions();
		questions.getModelFromSo(questionsSO);

		session.save(questions);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public LinkedHashSet<Integer> prepareQuestion(Integer userId) {

		Session session = getSession();

		ExamTypes examTypes = certificationSvc.getNextCertificate(userId);
		LinkedHashSet<Integer> randomQuestions = null;
		
		if (examTypes != null) {

			Integer maxQuestionsToForm = Integer.parseInt(ResHelper.get("MAX_QUESTIONS", "0"));

			if (maxQuestionsToForm != null && maxQuestionsToForm > 0) {
				List<Integer> questionIds = session.createCriteria(Questions.class)
						.add(Restrictions.eq("certificationLevel", examTypes))
						.add(Restrictions.eq("status", QuestionStatus.ACTIVE)).setProjection(Projections.id())
						.addOrder(Order.desc("id")).list();
				session.close();
				System.out.println("questionIds: "+questionIds);
				if (questionIds != null && !questionIds.isEmpty()) {
					LinkedList<Integer> questions = new LinkedList<>(questionIds);

					/*for (Questions question : questionIds) {
						questions.add(question.getqId());
					}*/
					randomQuestions = generateRandomNumberFromGivenRange(maxQuestionsToForm,
							questions, true);
					System.out.println("randomQuestions: "+randomQuestions);
					if (randomQuestions.size() > 0) {
						session = getSession();
						for (Integer questionId : randomQuestions) {
							UserQuestions.UserQuestionsPK userQuestionsPK = new UserQuestions.UserQuestionsPK(userId,
									questionId);
							UserQuestions userQuestions = new UserQuestions();
							userQuestions.setId(userQuestionsPK);
							userQuestions.setAttempted(false);
							userQuestions.getAudit().setCreatedDate(null);
							userQuestions.getAudit().setModifiedDate(null);

							session.persist(userQuestions);
						}
					}
				}

			} else {
				// throw exception
			}
		}
		
		return randomQuestions;
	}

	/**
	 * Generate Random number and create questions based on input.
	 * 
	 * @param maxQuestions
	 *            max set we need for user.
	 * @param questionIds
	 *            List of question Ids which will have all the question ids.
	 * @param inPlace
	 *            Let the program know that you want same input or not.
	 */
	private LinkedHashSet<Integer> generateRandomNumberFromGivenRange(int maxQuestions, LinkedList<Integer> questionIds,
			boolean inPlace) {

		if (!inPlace)
			questionIds = new LinkedList<>(questionIds);

		int origin = 0;
		int bound = questionIds.size();
		int count = maxQuestions;
		LinkedHashSet<Integer> randomQuestions = new LinkedHashSet<>(maxQuestions);
		while (count > 0 || randomQuestions.size() < maxQuestions) {
			int ofInt = ThreadLocalRandom.current().nextInt(origin, bound);

			Integer temp = questionIds.remove(ofInt);
			randomQuestions.add(temp);
			bound = questionIds.size();
			count--;
		}
		System.out.println(questionIds.size() + " : " + randomQuestions.size());

		return randomQuestions;
	}

	@Override
	@Transactional
	public QuestionsSO getNextQuestion(Integer userId, Integer questionId) {

		Session session = getSession();
		UserQuestions userQuestions = (UserQuestions) session.createCriteria(UserQuestions.class)
				.add(Restrictions.eq("id.userId", userId)).add(Restrictions.eq("id.questionId", questionId))
				.uniqueResult();
		QuestionsSO questionsSO = null;

		if (userQuestions == null) {

			session.clear();
			Questions questions = (Questions) session.createCriteria(Questions.class)
					.add(Restrictions.eq("questionId", questionId)).uniqueResult();

			questionsSO = questions.getSoFromModel();

		} else {
			// user don't have any such question.
		}
		return questionsSO;
	}

	@Override
	public Session getSession() {
		return sessionFactory.openSession();
	}

	@Override
	@Transactional
	public void saveUsersSelectedAnswers(Integer userId, Integer questionId, Set<Integer> actualAnsSet) {

		if (actualAnsSet != null) {

			Session session = getSession();

			Questions questions = (Questions) session.createCriteria(Questions.class)
					.add(Restrictions.eq("questionId", questionId)).uniqueResult();

			Set<Answers> answers = questions.getAnswers();
			Boolean isAnsCorrect = Boolean.FALSE;
			Set<Integer> ecapectedAnsSet = Collections.emptySet();
			Set<Integer> actualAnsSetCopy = null;

			for (Answers answer : answers) {
				ecapectedAnsSet.add(answer.getaId());
			}

			// If both are equal, means answer is correct
			if (ecapectedAnsSet.size() == actualAnsSet.size()) {
				actualAnsSetCopy = new HashSet<>(actualAnsSet);
				// Double check to make sure, answer selected by user and
				// expected answers are correct.
				actualAnsSetCopy.retainAll(ecapectedAnsSet);

				if (actualAnsSetCopy.size() == 0)
					isAnsCorrect = Boolean.TRUE;
			}

			UserQuestions userQuestions = (UserQuestions) session.createCriteria(UserQuestions.class)
					.add(Restrictions.eq("id.userId", userId)).add(Restrictions.eq("id.questionId", questionId))
					.uniqueResult();
			String ans = actualAnsSetCopy.toString().replace("[", "").replace("]", "").trim();
			userQuestions.setSelectedAnsIds(ans);
			userQuestions.setAttempted(true);
			userQuestions.setCorrect(isAnsCorrect);
			userQuestions.getAudit().setModifiedDate(null);

			session.update(userQuestions);

		} else {
			// throw exception
		}

	}

	@Override
	@Transactional
	public void updateQuestion(Integer questionId, QuestionsSO questionsSO) {

		questionsSO.setQuestionId(questionId);

		Session session = getSession();
		Questions questions = (Questions) session.get(Questions.class, questionId);
		questions.getModelFromSo(questionsSO);
		session.update(questions);

	}

	@Override
	@Transactional
	public void submitTest(Integer userId) {

		String sql = "SELECT SUM(Q.Q_MARKS) FROM ";

	}

}

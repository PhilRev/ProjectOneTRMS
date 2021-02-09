package dev.crane.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import dev.crane.controllers.TrainingController;

public class RequestProcessor {

	public static void process(HttpServletRequest request, HttpServletResponse response)
			throws JsonSyntaxException, JsonIOException, IOException {
		String uri = request.getRequestURI();

		//System.out.println(uri);

		switch (uri) {
		case "/projectOne/changePassword.do": {
			TrainingController.changePassword(request, response);
			break;
		}
		case "/projectOne/forgotPassword.do": {
			TrainingController.forgotPassword(request, response);
			break;
		}
		case "/projectOne/loginScreen.do": {
			TrainingController.signIn(request, response);
			break;
		}
		case "/projectOne/newEmployee.do": {
			TrainingController.newEmployee(request, response);
			break;
		}
		case "/projectOne/newJob.do": {
			TrainingController.newJob(request,response);
			break;
		}
		case "/projectOne/requestTraining.do": {
			TrainingController.requestTraining(request, response);
			break;
		}
		case "/projectOne/viewCompletedTraining.do": {
			TrainingController.viewCompletedTraining(request, response);
			break;
		}
		case "/projectOne/viewCourses.do": {
			TrainingController.viewCourses(request, response);
			break;
		}
		case "/projectOne/viewStatus.do": {
			TrainingController.viewStatus(request, response);
			break;
		}
		case "/projectOne/newCourse.do": {
			TrainingController.newCourse(request, response);
			break;
		}
		default: {
			try {
				response.sendError(418, "Defalt case hit.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		}
	}
}

package dev.crane.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import dev.crane.entities.Employee;
import dev.crane.entities.Grading;
import dev.crane.entities.Jobs;
import dev.crane.entities.Request;
import dev.crane.entities.RequestOut;
import dev.crane.entities.Status;
import dev.crane.entities.TRF;
import dev.crane.entities.Training;
import dev.crane.entities.Ytd;
import dev.crane.service.TrainingService;
import dev.crane.service.TrainingServiceImpl;

public class TrainingController {
	public static TrainingService ts = new TrainingServiceImpl();
	public static Gson gson = new Gson();
//	public static Employee logedInUser = new Employee();
//	public static boolean logedIn = false;

	public static void signIn(HttpServletRequest request, HttpServletResponse response)
			throws JsonSyntaxException, JsonIOException, IOException {
		Employee a = gson.fromJson(request.getReader(), Employee.class);
		String username = a.getUsername();
		String userpass = a.getUserpass();
		List<Employee> allEmployees = ts.allEmployees();
		Iterator<Employee> it = allEmployees.iterator();
		while (it.hasNext()) {
			Employee employee = it.next();
//			    System.out.println(employee.getUsername() + " " + username);
			if (employee.getUsername().equals(username)) {
				if (employee.getUserpass().equals(userpass)) {
//					logedInUser = employee;
//					logedIn = true;
					HttpSession session = request.getSession();
					session.setAttribute("logedInUser", employee);
					session.setAttribute("logedIn", true);
					// session.setAttribute("AllEmployees", allEmployees);
					try {
						response.getWriter().append("LogedIn");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					try {
						response.getWriter().append("Username and pass don't match.");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
//		   System.out.println(logedInUser + " " + logedIn);
	}

	public static void newEmployee(HttpServletRequest request, HttpServletResponse response) {
//		   System.out.println("in NewEmployee!!");

		HttpSession session = request.getSession();
//		Employee logedInUser = (Employee) session.getAttribute("logedInUser");
		boolean logedIn = (boolean) session.getAttribute("logedIn");

		if (logedIn) {
			String input = request.getParameter("id");
//			   System.out.println(input);
			if (input != null) {
				List<Employee> employees = ts.allEmployees();

				if (input.equals("LogOut")) {

//					logedInUser = null;
//					logedIn = false;

					session.invalidate();

					try {
						response.getWriter().append("LogOut");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (input.equals("job")) {

					List<Jobs> jobs = ts.allJobs();
//					   System.out.println(jobs);
					try {
						response.getWriter().append(gson.toJson(jobs));
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (input.equals("sup")) {
//					   System.out.println("in sup");
					try {
						response.getWriter().append(gson.toJson(employees));
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					Iterator<Employee> it = employees.iterator();
//					   System.out.println(input);
					boolean inUse = false;
					Employee test = new Employee();
					while (it.hasNext()) {
						test = it.next();
						if (test.getUsername().equals(input)) {
							inUse = true;
						}
					}
					if (inUse) {
						try {
							response.getWriter().append("is already being used. Please choose something differnt.");
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else if (input.length() < 6) {
						try {
							response.getWriter()
									.append("must be at least 6 letters long. \nPlease choose something different.");
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else if (input.contains(" ")) {
						try {
							response.getWriter()
									.append("must not contain any spaces. Please choose something different.");
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						try {
							response.getWriter().append("Username good");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			} else {
				Employee employee = new Employee();
				try {
					employee = gson.fromJson(request.getReader(), Employee.class);
				} catch (JsonSyntaxException e1) {

					e1.printStackTrace();
				} catch (JsonIOException e1) {

					e1.printStackTrace();
				} catch (IOException e1) {

					e1.printStackTrace();
				}

				boolean success = ts.newHire(employee);
				if (success) {
					try {
//						session.removeAttribute("AllEmployees");
//						List<Employee> allEmployees = ts.allEmployees();
//						session.setAttribute("AllEmployees", allEmployees);
						response.getWriter().append("Employee Added");
					} catch (IOException e) {
						System.out.println("line 176 Training contorller");
						e.printStackTrace();
					}
				} else {
					try {
						response.getWriter()
								.append("Error adding Employee. Reenter infromation or quit and see supervisor");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void newJob(HttpServletRequest request, HttpServletResponse response)
			throws JsonSyntaxException, JsonIOException, IOException {

		HttpSession session = request.getSession();
//		Employee logedInUser = (Employee) session.getAttribute("logedInUser");
		boolean logedIn = (boolean) session.getAttribute("logedIn");

		if (logedIn) {
			String input = request.getParameter("id");
//			   System.out.println(input);
			if (input != null) {
				if (input.equals("LogOut")) {
//					logedInUser = null;
//					logedIn = false;
					session.invalidate();
					try {
						response.getWriter().append("LogOut");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (input.equals("dep")) {
					List<Jobs> jobs = ts.allJobs();
					List<Jobs> jOut = new ArrayList<Jobs>();
					Iterator<Jobs> it = jobs.iterator();
//					   System.out.println(jobs);
					while (it.hasNext()) {
						Jobs job = it.next();
//						   System.out.println(job);
						switch (job.getJob_des()) {
						case "CEO":
						case "Office Manager":
						case "Sales Manager":
						case "Shop Manager":
						case "Parts Manager":
						case "Body Shop Manager": {
							jOut.add(job);
							break;
						}
						}
					}
					response.getWriter().append(gson.toJson(jobs));
				}
			} else {
				Jobs job = gson.fromJson(request.getReader(), Jobs.class);
				boolean success = ts.newJob(job);
				if (success) {
					response.getWriter().append("New Job Added");
				} else {
					response.getWriter().append(
							"There was an error adding the new job. \nPlease reenter the job or cancle and check with supervisor.");
				}
			}
		}
	}

	public static void changePassword(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		Employee logedInUser = (Employee) session.getAttribute("logedInUser");
//		boolean logedIn = (boolean) session.getAttribute("logedIn");

		Employee a = new Employee();
		try {
			a = gson.fromJson(request.getReader(), Employee.class);
		} catch (JsonSyntaxException e1) {
			e1.printStackTrace();
		} catch (JsonIOException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String oldPass = a.getUsername();
		String userpass = a.getUserpass();

//		 System.out.println(logedInUser + " " + oldPass + "\n " + a + " " + userpass);

		if (logedInUser.getUserpass().equals(oldPass)) {
			logedInUser.setUserpass(userpass);
			ts.updateEmployee(logedInUser);
			try {
				response.getWriter().append("Password updated");
				session.invalidate();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().append("An Error Occured. Password "
						+ "was not updated. \nRequest another password " + "reset or cancel and see your supervisor.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void forgotPassword(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
//		Employee logedInUser = (Employee) session.getAttribute("logedInUser");
//		boolean logedIn = (boolean) session.getAttribute("logedIn");

		String input = request.getParameter("id");
		Employee employee = new Employee();
		List<Employee> allEmployees = ts.allEmployees();

		boolean exsists = false;
		Iterator<Employee> it = allEmployees.iterator();
		while (it.hasNext()) {
			Employee test = it.next();
//			   System.out.println(test);
			if (test.getEmail().equals(input)) {
				exsists = true;
				employee = test;
//				   System.out.println(test + " " + employee);
			}
		}
//	   System.out.println(logedIn);
		if (exsists) {
			Email email = new SimpleEmail();
//			   System.out.println(email + " " + input + " " + employee);
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("revaturecrane@gmail.com", "revPhilip"));
			email.setSSLOnConnect(true);
			try {
				email.setFrom("revaturecrane@gmail.com");
			} catch (EmailException e) {
				e.printStackTrace();
			}
			email.setSubject("Reset Pass");
			double ran = Math.random() * 1000;
			int rdn = (int) (ran);
			String run = rdn + "rel";
//			   System.out.println(ran + " " + run);
			employee.setUserpass(run);
			exsists = ts.updateEmployee(employee);
			if (exsists) {
				try {
					email.setMsg("Your reset pass is " + run);
					session.setAttribute("logedInUser", employee);
					try {
						response.getWriter()
								.append("Email has been sent to " + input + ". Check email for new password");
					} catch (IOException e) {
						e.printStackTrace();
					}

				} catch (EmailException e) {
					e.printStackTrace();
				}
				try {
					email.addTo(input);
				} catch (EmailException e) {
					e.printStackTrace();
				}
				try {
					email.send();
				} catch (EmailException e) {
					e.printStackTrace();
				}
			} else {
				try {
					response.getWriter().append("There was a problem updating your password. Please see supervisor.");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				response.getWriter().append("Email does not match any employees. \nPlease see your supervisor.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void viewStatus(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		Employee logedInUser = (Employee) session.getAttribute("logedInUser");
		boolean logedIn = (boolean) session.getAttribute("logedIn");

		if (logedIn) {
			String input = request.getParameter("id");
			System.out.println(input);
			if (input.equals("LogOut")) {
				session.invalidate();
				try {
					response.getWriter().append("LogOut");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (input.equals("getPend")) {

				List<Request> requests = ts.allRequests();
//				System.out.println(requests);
				// List<Request> userreq = new ArrayList<Request>();
				Iterator<Request> rit = requests.iterator();
				List<RequestOut> statusList = new ArrayList<RequestOut>();
				String subName = "";
				List<Ytd> ytds = new ArrayList<Ytd>();
				Iterator<Ytd> yit = ytds.iterator();
				Ytd ytd = new Ytd();
				List<Employee> employees = ts.allEmployees();
				Iterator<Employee> eit = employees.iterator();
				Employee employee = new Employee();
				while (eit.hasNext()) {
					employee = eit.next();
					ytd.setEmployee_id(employee.getEmployee_id());
					ytd.setPayout(0);
					ytds.add(ytd);
				}
				int cost = 0;
				int projected = 0;
				int reqReim = 0;
				int left = 1000;
				HashMap<Integer, Integer> allEmpLeft = new HashMap<Integer, Integer>();

				while (rit.hasNext()) {
					Request req = rit.next();
					boolean isOne = false;
					int who = 0;
					String urg = "n";
					Calendar today = Calendar.getInstance();
					Calendar startDate = Calendar.getInstance();
					Calendar reqDate = Calendar.getInstance();
					Employee sub = ts.getEmployeeById(req.getEmployee_id());
					Jobs subJob = ts.getJobById(sub.getJob_id());
					Status status = ts.getStatusById(req.getStatus_id());
					Date tDay = new Date();
					String ovPay = status.getPayout_override();
					if (ovPay == null) {
						ovPay = "n";
					}
					String ovYtd = status.getYear_limit_override();
					if (ovYtd == null) {
						ovYtd = "n";
					}
					reqReim = req.getRequested_reimbursement();

//					System.out.println(status + "Hello!  " + sub.getSupervisor_id() + logedInUser.getEmployee_id()
//							+ +subJob.getDep_head_id() + logedInUser.getEmployee_id());

					if (req.getEmployee_id() == logedInUser.getEmployee_id()) {
						subName = "a";
						isOne = true;
						ytd.setEmployee_id(logedInUser.getEmployee_id());
						left = 1000 - ytd.getPayout();
						if (!ovPay.equals("y")) {
							if (left - reqReim < 0) {
								reqReim = left;
								left = 0;
							} else {
								left -= reqReim;
							}
						}
//						System.out.println(ytd + " here " + left);
						ytd.setPayout(ytd.getPayout() + reqReim);
						who = 1;
					} else if (sub.getSupervisor_id() == logedInUser.getEmployee_id()
							|| subJob.getDep_head_id() == logedInUser.getEmployee_id()) {

						who = 2;
						subName = sub.getFname() + " " + sub.getLname();

//						System.out.println(status.getDate_sup_approve() + " " + sub.getSupervisor_id()
//								+ logedInUser.getEmployee_id() + +subJob.getDep_head_id()
//								+ logedInUser.getEmployee_id());
						String pattern = "MM/dd/yyyy";
						SimpleDateFormat simDate = new SimpleDateFormat(pattern);
						Date sdate = new Date();
						today.setTime(sdate);
						try {
							sdate = simDate.parse(req.getRequested_start_date());
						} catch (ParseException e) {
							e.printStackTrace();
						}
						startDate.setTime(sdate);
						if (today.compareTo(startDate) > 14) {
							urg = "n";
						}

						
//						boolean test = today.compareTo(reqDate) >= 1;
//						System.out.println("test test test " + test + "  " +today.compareTo(startDate));// + " "+today +" \n"+startDate);
//						System.out.println(today);
//						System.out.println(startDate);
//						System.out.println(startDate.compareTo(today));
						if (urg.equals("y")) {
							if (sub.getSupervisor_id() == logedInUser.getEmployee_id()) {
								if (today.compareTo(reqDate) > 2) {
									status.setDate_sup_approve(simDate.format(tDay));
									ts.updateStatus(status);
								}
							}
							if (subJob.getDep_head_id() == logedInUser.getEmployee_id()) {
								if (today.compareTo(reqDate) > 4) {

									status.setDate_dep_head_approve(simDate.format(tDay));
									ts.updateStatus(status);
								}
							}
						}
//						System.out.println(status.getDate_sup_approve() + " sup " + isOne + " " + sub.getSupervisor_id()
//								+ " " + logedInUser.getEmployee_id());

						if (sub.getSupervisor_id() == logedInUser.getEmployee_id()) {
							if (status.getDate_sup_approve() == null) {
								isOne = true;
//								System.out.println(status.getDate_sup_approve() + " sup " + isOne);
							}
						}
						if (subJob.getDep_head_id() == logedInUser.getEmployee_id()) {
							if (status.getDate_dep_head_approve() == null) {
								isOne = true;
//								System.out.println(status.getDate_sup_approve() + " sup " + isOne);
							}
						}

//						System.out.println(isOne + "hear hear hear line 499");

//					} else if (subJob.getDep_head_id() == logedInUser.getEmployee_id()) {
//
//						who = 2;
//						subName = sub.getFname() + " " + sub.getLname();
//
//						System.out.println(sub.getSupervisor_id() + logedInUser.getEmployee_id()
//								+ +subJob.getDep_head_id() + logedInUser.getEmployee_id());
//						String pattern = "MM/dd/yyyy";
//						SimpleDateFormat simDate = new SimpleDateFormat(pattern);
//						Date sdate = new Date();
//						today.setTime(sdate);
//						try {
//							sdate = simDate.parse(req.getRequested_start_date());
//						} catch (ParseException e) {
//							e.printStackTrace();
//						}
//						startDate.setTime(sdate);
//						if (today.compareTo(startDate) < 14) {
//							urg = "y";
//						}
//						if (urg.equals("y")) {
//
//							if (today.compareTo(reqDate) < 4) {
//								status.setDate_dep_head_approve(simDate.format(tDay));
//								ts.updateStatus(status);
//							}
//						}
//
//						if (status.getDate_dep_head_approve() == null) {
//							isOne = true;
//							System.out.println(status.getDate_dep_head_approve() + " dep " + isOne);
//
//						}
//
//						System.out.println(isOne + "hear hear hear line 540");
					} else if (logedInUser.getEmployee_id() == req.getBenco_id()) {
						who = 3;
						subName = sub.getFname() + " " + sub.getLname();
						String pattern = "MM/dd/yyyy";
						SimpleDateFormat simDate = new SimpleDateFormat(pattern);
						Date sdate = new Date();
						today.setTime(sdate);
						try {
							sdate = simDate.parse(req.getRequested_start_date());
						} catch (ParseException e) {
							e.printStackTrace();
						}
						startDate.setTime(sdate);
						if (today.compareTo(startDate) < 14) {
							urg = "n";
						}
						if (urg.equals("y")) {
							if (today.compareTo(reqDate) < 2) {
								status.setDate_sup_approve(simDate.format(tDay));
							} else if (today.compareTo(reqDate) < 4) {
								status.setDate_dep_head_approve(simDate.format(tDay));
							}
						} else {
							if (today.compareTo(reqDate) < 7) {
								status.setDate_sup_approve(simDate.format(tDay));
							} else if (today.compareTo(reqDate) < 14) {
								status.setDate_dep_head_approve(simDate.format(tDay));
							}
						}
						if (status.getDate_benco_approve() == null) {
							isOne = true;
						}
						Training cou = ts.getCourseById(req.getTraining_id());
						int percent = 30;
						String courType = cou.getCourse_type();
						if (courType.equals("University Courses")) {
							percent = 80;
						} else if (courType.equals("Seminars")) {
							percent = 60;
						} else if (courType.equals("Certification Preparation Classes")) {
							percent = 75;
						} else if (courType.equals("Certification")) {
							percent = 100;
						} else if (courType.equals("Technical Training")) {
							percent = 90;
						}
						cost = Integer.parseInt(cou.getCourse_cost());
						projected = cost * percent / 100;
						if (reqReim != projected) {
							if (ovPay.equals("y")) {
								projected = reqReim;
							} else {								
								ovPay = "Requested reimbursement is $" + (reqReim - projected) + " over the" +
										" standard amount of $" + projected + ".\nWould you like to approve this amount?";
							}
						}
						yit = ytds.iterator();
						boolean found = false;
						while (!found && yit.hasNext()) {
							ytd = yit.next();
							if (ytd.getEmployee_id() == req.getEmployee_id()) {
								found = true;
							}
						}
						if (allEmpLeft.containsKey(req.getEmployee_id())) {
							left = 1000 - allEmpLeft.get(req.getEmployee_id());
						} else {
							allEmpLeft.put(req.getEmployee_id(), 0);
							left = 1000;
						}

						if (!ovYtd.equals("y")) {
							if (left - reqReim < 0) {
								ovYtd = "The payout exceeds the yearly limit by $" + (left - reqReim)
										+ ". \nDo you want to approve a limit extension?";
								reqReim = left;
								left = 0;
							} else {
								left -= reqReim;
							}
						}
						allEmpLeft.put(req.getEmployee_id(), 1000 - left);
						ytd.setPayout(allEmpLeft.get(req.getEmployee_id()));
						who = 3;
//						System.out.println(req.getEmployee_id() + "  " + ytd);
					}
					if (isOne) {

						// userreq.add(req);

						String stat = "";
						if (status.getAdd_info_req() != null) {
							if (!status.getAdd_info_req().equals("Course Canceled.")) {
								stat = status.getAdd_info_req();
								isOne = false;
							}
						} else if (status.getDate_sup_approve() == null) {
							stat = "Waiting for Supervisor Approval";
						} else if (status.getDate_dep_head_approve() == null) {
							stat = "Waiting for Department Head Approval";
						} else if (status.getDate_benco_approve() == null) {
							stat = "Waiting on Benefits Coordinator Approval";
						} else if (status.getGrade() == null) {
							stat = "Waiting for proof of completions with " + req.getRequested_pass_criteria() + ".";
							
						}
						if (isOne) {
							if (status.getPayout_override() != null && status.getPayout_override().equals("y")) {
								projected = req.getRequested_reimbursement();
							}

							Training course = ts.getCourseById(req.getTraining_id());
							Grading grade = ts.getGradingById(course.getGrading_id());
							
							
							RequestOut pendingOut = new RequestOut(req.getRequest_id(), course.getTraining_id(),
									course.getCourse_name(), course.getStart_date(), course.getEnd_date(),
									course.getLocation(), stat, grade.getPass_criteria(), who, subName, urg, cost,
									projected, req.getJustification(), ovPay, ovYtd, ytd.getPayout(), left);
							statusList.add(pendingOut);
						}

					}
				}
//				session.setAttribute("Requests", requests);
//				session.setAttribute("Statuses", statusList);
//				session.setAttribute("UserReq", userreq);
				if (!statusList.isEmpty()) {
					try {

						response.getWriter().append(gson.toJson(statusList));
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					try {
						response.getWriter().append("No Courses Pending");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
//			
			} else if (input.equals("apovPay")) {
				String request_id = request.getParameter("aid");
				System.out.println(request_id + "Made it...");
				Request req = ts.getRequestById(Integer.parseInt(request_id));
				Status stat = ts.getStatusById(req.getStatus_id());

				stat.setYear_limit_override("y");
				boolean isgood = ts.updateStatus(stat);

				if (stat.getYear_limit_override().equals("y")) {
					isgood = true;
				}
				try {
					response.getWriter().append((isgood) ? "y" : "An error occured.");
				} catch (IOException e) {

					e.printStackTrace();
				}

			} else if (input.equals("appRe")) {
				String request_id = request.getParameter("aid");
				System.out.println(request_id + "in appRe line 630");
				Request req = ts.getRequestById(Integer.parseInt(request_id));
				Status stat = ts.getStatusById(req.getStatus_id());
				stat.setPayout_override("y");
				boolean isgood = ts.updateStatus(stat);
				if (stat.getPayout_override().equals("y")) {
					isgood = true;
				}
				try {
					response.getWriter().append((isgood) ? "y" : "An error occured.");
				} catch (IOException e) {

					e.printStackTrace();
				}
			} else if (input.equals("approve")) {
				String input2 = request.getParameter("aid");
//				String input3 = request.getParameter("bid");
//				System.out.println(input2 + " line 661 approve " + input3);
				Request req = ts.getRequestById(Integer.parseInt(input2));
				Status stat = ts.getStatusById(req.getStatus_id());
				String pattern = "MM/dd/yyyy";
				SimpleDateFormat simDate = new SimpleDateFormat(pattern);
				Date sdate = new Date();
				String today = simDate.format(sdate);

				req = ts.getRequestById(Integer.parseInt(input2));
				Employee employee = ts.getEmployeeById(req.getEmployee_id());
				Jobs job = ts.getJobById(employee.getJob_id());

				System.out.println(employee + "       job" + job);
				if (logedInUser.getEmployee_id() == employee.getSupervisor_id()) {
					stat.setDate_sup_approve(today);
				}
				System.out.println(logedInUser.getEmployee_id() + "  " + job.getDep_head_id());

				if (logedInUser.getEmployee_id() == job.getDep_head_id()) {
					stat.setDate_dep_head_approve(today);
				}
				if (logedInUser.getEmployee_id() == req.getBenco_id()) {
					stat.setDate_benco_approve(today);
				}

				boolean isgood = ts.updateStatus(stat);
				System.out.println(isgood + " is good" + stat);

				try {
					response.getWriter().append((isgood) ? "y" : "An error occured.");
				} catch (IOException e) {

					e.printStackTrace();
				}
			} else if (input.equals("deny")) {
				String input2 = request.getParameter("aid");
//				String input3 = request.getParameter("bid");
//				System.out.println(input2 + " line 646 approve " + input3);
				Request req = ts.getRequestById(Integer.parseInt(input2));
				Status stat = ts.getStatusById(req.getStatus_id());
				String pattern = "MM/dd/yyyy";
				SimpleDateFormat simDate = new SimpleDateFormat(pattern);
				Date sdate = new Date();
				String today = simDate.format(sdate);

				req = ts.getRequestById(Integer.parseInt(input2));
				Employee employee = ts.getEmployeeById(req.getEmployee_id());
				Jobs job = ts.getJobById(employee.getJob_id());
				if (logedInUser.getEmployee_id() == employee.getSupervisor_id()) {
					stat.setGrade("Course Canceled.");
					stat.setAdd_info_req("Denied by supervisor.");
				} else if (logedInUser.getEmployee_id() == job.getDep_head_id()) {
					stat.setGrade("Course Canceled.");
					stat.setAdd_info_req("Denied by Department Head.");
				} else if (logedInUser.getEmployee_id() == req.getBenco_id()) {
					stat.setGrade("Course Canceled.");
					stat.setAdd_info_req("Denied by Benefits Coordinator.");
				}

				boolean isgood = ts.updateStatus(stat);
				if (stat.getDate_benco_approve() != null) {
					isgood = true;
				}
				stat.setDate_benco_approve(today);
				try {
					response.getWriter().append((isgood) ? "y" : "An error occured.");
				} catch (IOException e) {

					e.printStackTrace();
				}
			} else if (input.equals("reqCou")) {
				session.setAttribute("req", request.getParameter("aid"));

			}
		}
	}

	public static void viewCourses(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		Employee logedInUser = (Employee) session.getAttribute("logedInUser");
		boolean logedIn = (boolean) session.getAttribute("logedIn");

		if (logedIn) {
			String input = request.getParameter("id");
//				System.out.println(input);
			if (input != null) {
				if (input.equals("LogOut")) {
					logedInUser = null;
					logedIn = false;
					try {
						response.getWriter().append("LogOut");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (input.equals("getAva")) {

					List<Training> courses = ts.allCourses();
//					System.out.println(courses);
					Iterator<Training> it = courses.iterator();
					List<RequestOut> statusList = new ArrayList<RequestOut>();
					List<Request> requests = ts.allRequests();

					if (it.hasNext()) {
						while (it.hasNext()) {
							Iterator<Request> rit = requests.iterator();
							boolean allreadyReg = false;
							String stat = "";
							Training course = it.next();
							System.out.println(course + "testing");
							while (rit.hasNext()) {
								Request req = rit.next();
								if (req.getEmployee_id() == logedInUser.getEmployee_id()
										&& req.getTraining_id() == course.getTraining_id()) {
									allreadyReg = true;
								}
							}

							if (!allreadyReg) {

								Grading grade = ts.getGradingById(course.getGrading_id());

								RequestOut statusOut = new RequestOut(course.getTraining_id(), course.getCourse_name(),
										course.getStart_date(), course.getEnd_date(), course.getLocation(), stat,
										grade.getPass_criteria());
								statusList.add(statusOut);
							}
						}
//						System.out.println(statusList);
						try {
							response.getWriter().append(gson.toJson(statusList));
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						try {
							response.getWriter().append("No upcomming courses");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} else if (input.equals("reqCou")) {
					String course_id = request.getParameter("aid");

					Training course = ts.getCourseById(Integer.parseInt(course_id));
//				 	System.out.println("sssssssssssssssss" + course + " " + course_id);
					List<Jobs> jobs = ts.allJobs();
					Iterator<Jobs> jit = jobs.iterator();
					List<Jobs> jobs2 = new ArrayList<Jobs>();
					Jobs job = new Jobs();
					boolean found = false;
					while (!found && jit.hasNext()) {
						job = jit.next();
						if (job.getJob_des().equals("Benefits Coordinator")) {
							jobs2.add(job);
						}
					}
					jit = jobs2.iterator();
					System.out.println(job);
					int bencoId = 21;
					if (logedInUser.getEmployee_id() == 21) {
						bencoId = 1;
					}
					String pattern = "MM/dd/yyyy";
					SimpleDateFormat simDate = new SimpleDateFormat(pattern);
					String date = simDate.format(new Date());
					String supApp = null;
					String depHed = null;
					String status = "Pending justification";
					if (logedInUser.getEmployee_id() == logedInUser.getSupervisor_id()) {
						supApp = date;
					}
					if (logedInUser.getEmployee_id() == ts.getJobById(logedInUser.getJob_id()).getDep_head_id()) {
						depHed = date;
					}
					Status status1 = new Status(status, date, "newStat", supApp, depHed, null, null, null, null);
					boolean success = ts.newStatus(status1);
					List<Status> statuses = ts.allStatuses();
					Iterator<Status> sit = statuses.iterator();
					while (success && sit.hasNext()) {
						Status s = sit.next();
						if (s.getAdd_info_req() != null) {
							if (s.getAdd_info_req().equals("newStat")) {
								status1 = s;
								status1.setAdd_info_req(null);
								success = ts.updateStatus(status1);
								System.out.println("Status Check" + status1);
								success = false;
							}
						}
					}
					Request newRequest = new Request(0, logedInUser.getEmployee_id(), course.getTraining_id(), bencoId,
							status1.getStatus_id());
					success = ts.newRequest(newRequest);
					List<Request> requests = ts.allRequests();
					Iterator<Request> rit = requests.iterator();
					while (success && rit.hasNext()) {
						Request r = rit.next();
						if (r.getStatus_id() == status1.getStatus_id()) {
							newRequest = r;
						}
					}
					session = request.getSession();
					session.setAttribute("Request", newRequest);
					session.setAttribute("Status", status1);
					int r = newRequest.getRequest_id();
					String req = String.valueOf(r);
					session.setAttribute("req", req);
					if (session.getAttribute("Request") == newRequest) {
						success = true;
					}
					try {
						response.getWriter().append((success) ? "Request Created"
								: "An error occured trying to process your request. \nPlease try again or see your supervisor.");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (input.equals("getComplete")) {

				}
			}
		}
	}

	public static void requestTraining(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		Employee logedInUser = (Employee) session.getAttribute("logedInUser");
		boolean logedIn = (boolean) session.getAttribute("logedIn");

		if (logedIn) {
			String input = request.getParameter("id");
			System.out.println(input);

			Request req = new Request();

			if (input != null) {
				if (input.equals("LogOut")) {
					logedInUser = null;
					logedIn = false;
					try {
						response.getWriter().append("LogOut");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (input.equals("open1")) {
//					session = request.getSession();
					// System.out.println("hehe");
					String reqIdS = (String) session.getAttribute("req");
					int reqId = Integer.parseInt(reqIdS);
					// System.out.println(req);
					List<Request> reqs = ts.allRequests();
					Iterator<Request> rit = reqs.iterator();

					boolean isit = false;
					while (!isit && rit.hasNext()) {
						Request r = rit.next();
						if (r.getRequest_id() == reqId) {
							isit = true;
							req = r;
						}
					}
					// System.out.println(req);

//					Status stat = (Status) session.getAttribute("Status");
//					System.out.println(req + " " + stat);
					Status status = ts.getStatusById(req.getStatus_id());
					Employee employee = ts.getEmployeeById(req.getEmployee_id());
					Employee sup = ts.getEmployeeById(employee.getSupervisor_id());
					Training tra = ts.getCourseById(req.getTraining_id());
					Grading gra = ts.getGradingById(tra.getGrading_id());
					Jobs job = ts.getJobById(employee.getJob_id());

					String pattern = "MM/dd/yyyy";
					SimpleDateFormat simDate = new SimpleDateFormat(pattern);
					Date sdate = new Date();
					try {
						sdate = simDate.parse(tra.getStart_date());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					Date edate = new Date();
					try {
						edate = simDate.parse(tra.getEnd_date());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
//					isit = true;
					pattern = "yyyy-MM-dd";
					simDate = new SimpleDateFormat(pattern);
					String ssdate = simDate.format(sdate);
					String sedate = simDate.format(edate);
					Status stat = ts.getStatusById(req.getStatus_id());
					TRF trf = new TRF();
					String approve = "";
					if (logedInUser.getEmployee_id() == employee.getSupervisor_id()
							|| logedInUser.getEmployee_id() == job.getDep_head_id()
							|| logedInUser.getEmployee_id() == req.getBenco_id()) {
						approve = "y";
					} else if (logedInUser.getEmployee_id() == req.getEmployee_id()) {
						approve = "e";
					} else if (logedInUser.getEmployee_id() == req.getEmployee_id()
							&& status.getDate_benco_approve() != null) {
						approve = "g";
					}

					if (stat != null) {

						trf = new TRF(req.getRequest_id(), logedInUser.getFname() + " " + logedInUser.getLname(),
								logedInUser.getEmail(), job.getJob_des(), sup.getFname() + " " + sup.getLname(),
								tra.getCourse_name(), tra.getCourse_type(), tra.getLocation(), tra.getStart_date(),
								tra.getEnd_date(), tra.getCourse_cost(), gra.getPass_criteria(),
								req.getRequested_reimbursement(), req.getRequested_pass_criteria(), ssdate, sedate,
								req.getJustification(), stat.getAdd_info_req(), approve, stat.getGrade());

					} else {
						trf = new TRF(req.getRequest_id(), logedInUser.getFname() + " " + logedInUser.getLname(),
								logedInUser.getEmail(), job.getJob_des(), sup.getFname() + " " + sup.getLname(),
								tra.getCourse_name(), tra.getCourse_type(), tra.getLocation(), tra.getStart_date(),
								tra.getEnd_date(), tra.getCourse_cost(), gra.getPass_criteria(),
								req.getRequested_reimbursement(), req.getRequested_pass_criteria(), ssdate, sedate,
								req.getJustification());
					}
//					System.out.println(trf);
					try {
						response.getWriter().append(gson.toJson(trf));
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (input.equals("Delete")) {
					String input2 = request.getParameter("aid");

					System.out.println(input2 + "INDELETE");
					req = ts.getRequestById(Integer.parseInt(input2));
					Status stat = ts.getStatusById(req.getStatus_id());
					stat.setAdd_info_req("Course Canceled.");

					boolean success = ts.deleteRequestById(req.getRequest_id());

					try {
						response.getWriter()
								.append((success) ? "Deleted" : "A promblem occured deleteing the request.");
					} catch (IOException e) {

						e.printStackTrace();
					}

				} else if (input.equals("approve")) {
					String input2 = request.getParameter("aid");
					// String input3 = request.getParameter("bid");
					System.out.println(input2 + " line 1050 approve ");
					req = ts.getRequestById(Integer.parseInt(input2));
					Status stat = ts.getStatusById(req.getStatus_id());
					String pattern = "MM/dd/yyyy";
					SimpleDateFormat simDate = new SimpleDateFormat(pattern);
					Date sdate = new Date();
					String today = simDate.format(sdate);

					req = ts.getRequestById(Integer.parseInt(input2));
					Employee employee = ts.getEmployeeById(req.getEmployee_id());
					Jobs job = ts.getJobById(employee.getJob_id());

					System.out.println(employee + "       job" + job);
					if (logedInUser.getEmployee_id() == employee.getSupervisor_id()) {
						stat.setDate_sup_approve(today);
					}
					System.out.println(logedInUser.getEmployee_id() + "  " + job.getDep_head_id());

					if (logedInUser.getEmployee_id() == job.getDep_head_id()) {
						stat.setDate_dep_head_approve(today);
					}
					if (logedInUser.getEmployee_id() == req.getBenco_id()) {
						stat.setDate_benco_approve(today);
					}

					boolean isgood = ts.updateStatus(stat);
					System.out.println(isgood + " is good" + stat);

					try {
						response.getWriter().append((isgood) ? "y" : "An error occured.");
					} catch (IOException e) {

						e.printStackTrace();
					}
				}

			} else {
				///////// // String strf = "";
				TRF trf = new TRF();
				try {
					trf = gson.fromJson(request.getReader(), TRF.class);
				} catch (JsonSyntaxException e) {
					e.printStackTrace();
				} catch (JsonIOException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				// trf = gson.fromJson(strf, TRF.class);
//				if (trf.getReqId() == 0) {
//					System.out.println("hearhhh" + trf);
//					session = request.getSession();
//					req = (Request) session.getAttribute("Request");
//					Status stat = (Status) session.getAttribute("Status");
//					System.out.println(req + " " + stat);
//					if (stat.getDate_sup_approve() != null) {
//						if (stat.getDate_dep_head_approve() != null) {
//							stat.setStatus("Awaiting Benefits Coordinator Approval.");
//						} else {
//							stat.setStatus("Awaiting Department Head Approval.");
//						}
//
//					} else {
//						stat.setStatus("Awaiting Supervisor Review.");
//					}
//					System.out.println("hehehe");
//					boolean success = ts.updateStatus(stat);
//					System.out.println(success + " " + stat + " " + success);
//					if (success) {
//						List<Status> statuses = ts.allStatuses();
//						Iterator<Status> sit = statuses.iterator();
//						System.out.println(statuses);
//						while (sit.hasNext()) {
//							Status s = sit.next();
//							System.out.println(s);
//							if (s.getAdd_info_req() != null) {
//								if (s.getAdd_info_req().equals("newStat")) {
//									s.setAdd_info_req(null);
//
//									stat = s;
//
//									success = ts.updateStatus(stat);
//
//									System.out.println(success + " " + stat);
//								}
//							}
//						}
//					}
//// DATE conversion					
//					System.out.println(trf + " " + stat + " " + req);
//					String pattern = "yyyy-MM-dd";
//					SimpleDateFormat simDate = new SimpleDateFormat(pattern);
//					Date sdate = new Date();
//					try {
//						sdate = simDate.parse(trf.getResdate());
//					} catch (ParseException e1) {
//						e1.printStackTrace();
//					}
//					Date edate = new Date();
//					try {
//						edate = simDate.parse(trf.getReedate());
//					} catch (ParseException e1) {
//						e1.printStackTrace();
//					}
//					pattern = "MM/dd/yyyy";
//					simDate = new SimpleDateFormat(pattern);
//					String ssdate = simDate.format(sdate);
//					String sedate = simDate.format(edate);
//					req.setRequested_reimbursement(trf.getReimb());
//					req.setRequested_pass_criteria(trf.getRepassCrit());
//					req.setRequested_start_date(ssdate);
//					req.setRequested_end_date(sedate);
//					req.setJustification("nNnew");
//					req.setStatus_id(stat.getStatus_id());
//					success = ts.updateRequest(req);
//					System.out.println(success + " " + req + " " + stat);
//					if (success) {
//						List<Request> requests = ts.allRequests();
//						Iterator<Request> rit = requests.iterator();
//						while (rit.hasNext()) {
//							Request r = rit.next();
//							System.out.println(r);
//							if (r.getJustification() != null) {
//								if (r.getJustification().equals("nNnew")) {
//									r.setJustification(trf.getJust());
//
//									req = r;
//									success = ts.updateRequest(req);
//									System.out.println(r);
//								}
//							}
//						}
//					}
//					System.out.println(req + " " + stat);
//
//					session = request.getSession();
//					session.setAttribute("Request", req);
//					session.setAttribute("Status", stat);
//					Request r2 = (Request) session.getAttribute("Request");
//
//					System.out.println(success);
//					System.out.println(r2);
//					System.out.println(req);
//
//					if (r2 == ts.getRequestById(req.getRequest_id())) {
//						success = true;
//					}
//					try {
//						response.getWriter().append((success) ? "Request Complete"
//								: "An error occured trying to process your request. \nPlease try again or see your supervisor.");
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				} else {
					req = ts.getRequestById(trf.getReqId());
					Status stat = ts.getStatusById(req.getStatus_id());
					System.out.println("hearhhh" + trf);
					String pattern = "yyyy-MM-dd";
					SimpleDateFormat simDate = new SimpleDateFormat(pattern);
					Date sdate = new Date();
					try {
						sdate = simDate.parse(trf.getResdate());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					Date edate = new Date();
					try {
						edate = simDate.parse(trf.getReedate());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					pattern = "MM/dd/yyyy";
					simDate = new SimpleDateFormat(pattern);
					String ssdate = simDate.format(sdate);
					String sedate = simDate.format(edate);

					req.setRequested_reimbursement(trf.getReimb());
					req.setRequested_end_date(sedate);
					req.setRequested_pass_criteria(trf.getRepassCrit());
					req.setRequested_start_date(ssdate);
					req.setJustification(trf.getJust());
					stat.setGrade(trf.getGrade());
					stat.setAdd_info_req(trf.getNotes());
					if (trf.getAprove() != "0") {
						stat.setPayout_override(trf.getAprove());
					}
// Futher logic to calculate money and time off here.
					System.out.println(stat +"\n" + req);
					boolean success = ts.updateStatus(stat);
					success = ts.updateRequest(req);
					try {
						response.getWriter().append((success) ? "Request Complete"
								: "An error occured trying to process your request. \nPlease try again or see your supervisor.");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	

	public static void viewCompletedTraining(HttpServletRequest request, HttpServletResponse response) {

	}

	public static void newCourse(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
//		Employee logedInUser = (Employee) session.getAttribute("logedInUser");
		boolean logedIn = (boolean) session.getAttribute("logedIn");

		if (logedIn) {
			String input = request.getParameter("id");
//			System.out.println(input);
			if (input != null) {
				if (input.equals("LogOut")) {
//					logedInUser = null;
//					logedIn = false;
					session.invalidate();
					try {
						response.getWriter().append("LogOut");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {

				Training t = new Training();
				try {
					t = gson.fromJson(request.getReader(), Training.class);
				} catch (JsonSyntaxException e1) {
					e1.printStackTrace();
				} catch (JsonIOException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.out.println(t);
				String pattern = "yyyy-MM-dd";
				SimpleDateFormat simDate = new SimpleDateFormat(pattern);
				Date sdate = new Date();
				try {
					sdate = simDate.parse(t.getStart_date());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				Date edate = new Date();
				try {
					edate = simDate.parse(t.getEnd_date());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				pattern = "MM/dd/yyyy";
				simDate = new SimpleDateFormat(pattern);
				t.setStart_date(simDate.format(sdate));
				t.setEnd_date(simDate.format(edate));

				boolean success = ts.newCourse(t);
				System.out.println(success + " " + t);
				try {
					response.getWriter().append((success) ? "Training Added"
							: "Error adding the course or training. \nReenter infromation or quit and see supervisor");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

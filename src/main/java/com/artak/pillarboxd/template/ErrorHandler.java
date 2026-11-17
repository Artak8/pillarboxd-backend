package com.artak.pillarboxd.template;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorHandler implements ErrorController {
    /*
        First we retrieve the status amd the accept header
        Then we broadcast the error in two ways

        1. Send JSON containing the status and a simple error message
        2. Show the error page and pass the status code
    */
    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request, HttpServletResponse response, Model model) {
        int status = response.getStatus();
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            return new ModelAndView("jsonView", "error", new APIError(status, "Something went wrong"));
        } else {
            model.addAttribute("status", status);
            return new ModelAndView("error");
        }
    }

    //A simple error record, we can use this to bundle information
    public static record APIError (
        int status,
        String message
    ) {}
}

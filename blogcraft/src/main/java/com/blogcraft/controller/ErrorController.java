package com.blogcraft.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            
            switch (statusCode) {
                case 404:
                    model.addAttribute("errorCode", "404");
                    model.addAttribute("errorMessage", "Page not found");
                    model.addAttribute("errorDescription", "The page you're looking for doesn't exist.");
                    break;
                case 403:
                    model.addAttribute("errorCode", "403");
                    model.addAttribute("errorMessage", "Access denied");
                    model.addAttribute("errorDescription", "You don't have permission to access this resource.");
                    break;
                case 500:
                    model.addAttribute("errorCode", "500");
                    model.addAttribute("errorMessage", "Internal server error");
                    model.addAttribute("errorDescription", "Something went wrong on our end. Please try again later.");
                    break;
                default:
                    model.addAttribute("errorCode", statusCode);
                    model.addAttribute("errorMessage", "An error occurred");
                    model.addAttribute("errorDescription", "Something went wrong. Please try again.");
            }
        } else {
            model.addAttribute("errorCode", "Unknown");
            model.addAttribute("errorMessage", "An error occurred");
            model.addAttribute("errorDescription", "Something went wrong. Please try again.");
        }
        
        return "error";
    }
} 
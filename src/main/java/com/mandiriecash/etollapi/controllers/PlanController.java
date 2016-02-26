package com.mandiriecash.etollapi.controllers;

import com.github.yafithekid.mandiri_ecash_api.exceptions.MEATokenExpiredException;
import com.mandiriecash.etollapi.exceptions.PaymentErrorException;
import com.mandiriecash.etollapi.exceptions.UserNotFoundException;
import com.mandiriecash.etollapi.models.Plan;
import com.mandiriecash.etollapi.models.User;
import com.mandiriecash.etollapi.services.PlanService;
import com.mandiriecash.etollapi.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/plans")
public class PlanController {
    private final String ok = "ok";
    private final String error = "error";
    public final static String TOKEN_EXPIRED = "TOKEN_EXPIRED";

    @Autowired
    PlanService planService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    PlanResponse getPlans(
            @RequestParam(name = "msisdn") String msisdn){
        List<Plan> plans = planService.getPlansByMsisdnAndNotExecuted(msisdn);
        return new PlanResponse(ok,"", plans);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public @ResponseBody
    CreatePlanResponse createPlan(
            @RequestParam(name = "source_id") int source_id,
            @RequestParam(name = "source_name") String source_name,
            @RequestParam(name = "dest_id") int dest_id,
            @RequestParam(name = "dest_name") String dest_name,
            @RequestParam(name = "msisdn") String msisdn,
            @RequestParam(name = "price") int price){
        Plan plan = new Plan();
        plan.setMsisdn(msisdn);
        plan.setDest_id(dest_id);
        plan.setDest_name(dest_name);
        plan.setSource_id(source_id);
        plan.setSource_name(source_name);
        plan.setPrice(price);
        plan.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return new CreatePlanResponse(ok,"",planService.createPlan(plan));
    }

    @RequestMapping(value = "/execute", method = RequestMethod.GET)
    public @ResponseBody
    ExecutePlanResponse executePlan(
            @RequestParam(name = "id") int id){
        try {
            Plan plan = planService.getPlanById(id);
            User user = userService.getUserByMsisdn(plan.getMsisdn());
            planService.execute(plan,user.getCredentials(),user.getToken());
            return new ExecutePlanResponse(ok, "");
        } catch (UserNotFoundException e) {
            return new ExecutePlanResponse(error,e.getMessage());
        } catch (PaymentErrorException e){
            if (e.getCause() instanceof MEATokenExpiredException){
                return new ExecutePlanResponse(error,TOKEN_EXPIRED);
            } else {
                return new ExecutePlanResponse(error,e.getMessage());
            }
        }
    }
}

class PlanResponse {
    public final String status;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public List<Plan> getPlans() {
        return plans;
    }

    public final String message;
    public final List<Plan> plans;

    public PlanResponse(String status, String message, List<Plan> plans){
        this.status = status;
        this.message = message;
        this.plans = plans;
    }

}

class CreatePlanResponse {
    public final String status;
    public final String message;
    public final int id;

    public CreatePlanResponse(String status, String message, int id){
        this.status = status;
        this.message = message;
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getId() {
        return id;
    }
}

class ExecutePlanResponse {
    private String status;
    private String message;

    public ExecutePlanResponse(String status, String message) {
        this.message = message;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
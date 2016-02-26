package com.mandiriecash.etollapi.controllers;

import com.mandiriecash.etollapi.models.Plan;
import com.mandiriecash.etollapi.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    PlanService planService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    PlanResponse getPlans(
            @RequestParam(name = "msisdn") String msisdn){
        List<Plan> plans = planService.getPlansByMsisdnAndNotExecuted(msisdn);
        return new PlanResponse("ok","", plans);
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
        return new CreatePlanResponse("ok","",planService.createPlan(plan));
    }

    @RequestMapping(value = "/execute", method = RequestMethod.GET)
    public @ResponseBody
    PlanResponse executePlan(
            @RequestParam(name = "id") int id){
        Plan plan = planService.getPlanById(id);
        plan.setExecuted(true);
        planService.updatePlan(plan);
        return new PlanResponse("ok", "", new ArrayList<Plan>());
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
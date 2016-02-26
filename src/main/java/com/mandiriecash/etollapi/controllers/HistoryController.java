package com.mandiriecash.etollapi.controllers;

import com.mandiriecash.etollapi.models.Plan;
import com.mandiriecash.etollapi.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/histories")
public class HistoryController {
    @Autowired
    PlanService planService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    HistoryResponse getPlans(
            @RequestParam(name = "msisdn") String msisdn){
        List<Plan> histories = planService.getPlansByMsisdnAndExecuted(msisdn);
        return new HistoryResponse("ok","", histories);
    }
}

class HistoryResponse {
    public final String status;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public List<Plan> getHistories() {
        return histories;
    }

    public final String message;
    public final List<Plan> histories;

    public HistoryResponse(String status, String message, List<Plan> histories){
        this.status = status;
        this.message = message;
        this.histories = histories;
    }

}

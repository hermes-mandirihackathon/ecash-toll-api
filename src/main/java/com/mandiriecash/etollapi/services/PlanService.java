package com.mandiriecash.etollapi.services;

import com.mandiriecash.etollapi.models.Plan;

import java.util.List;

public interface PlanService {
    public List<Plan> getPlansByMsisdn(String msisdn);
    public List<Plan> getPlansByMsisdnAndExecuted(String msisdn);
    public List<Plan> getPlansByMsisdnAndNotExecuted(String msisdn);
    public void updatePlan(Plan plan);
    public int createPlan(Plan plan);
    public void deletePlan(Plan plan);
    public Plan getPlanById(int id);

}

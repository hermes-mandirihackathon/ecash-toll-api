package com.mandiriecash.etollapi.dao;

import com.mandiriecash.etollapi.models.Plan;

import java.util.List;

public interface PlanDAO {
    int createPlan(Plan plan);

    List<Plan> getPlansByMsisdn(String msisdn);

    void deletePlan(Plan plan);

    void updatePlan(Plan plan);

    Plan getPlanById(int id);

    List<Plan> getPlansByMsisdnAndExecuted(String msisdn);

    List<Plan> getPlansByMsisdnAndNotExecuted(String msisdn);
}

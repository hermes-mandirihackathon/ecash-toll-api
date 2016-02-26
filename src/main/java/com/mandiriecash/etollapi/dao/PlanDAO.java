package com.mandiriecash.etollapi.dao;

import com.mandiriecash.etollapi.models.Plan;

import java.util.List;

public interface PlanDAO {
    int createPlan(Plan plan);

    List<Plan> getPlansByMsisdn(String msisdn);

    void deletePlan(Plan plan);
}

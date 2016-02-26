package com.mandiriecash.etollapi.services;

import com.mandiriecash.etollapi.dao.PlanDAO;
import com.mandiriecash.etollapi.exceptions.PaymentErrorException;
import com.mandiriecash.etollapi.models.Plan;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PlanServiceImpl implements PlanService {
    @Autowired
    PlanDAO planDAO;

    @Autowired
    PaymentService paymentService;

    @Override
    public List<Plan> getPlansByMsisdn(String msisdn) {
        return planDAO.getPlansByMsisdn(msisdn);
    }

    @Override
    public List<Plan> getPlansByMsisdnAndExecuted(String msisdn) {
        return planDAO.getPlansByMsisdnAndExecuted(msisdn);
    }

    @Override
    public List<Plan> getPlansByMsisdnAndNotExecuted(String msisdn) {
        return planDAO.getPlansByMsisdnAndNotExecuted(msisdn);
    }

    @Override
    public void updatePlan(Plan plan) {
        planDAO.updatePlan(plan);
    }

    @Override
    public int createPlan(Plan plan) {
        return planDAO.createPlan(plan);
    }

    @Override
    public void deletePlan(Plan plan) {
        planDAO.deletePlan(plan);
    }

    @Override
    public Plan getPlanById(int id) {
        return planDAO.getPlanById(id);
    }

    @Override
    public void execute(Plan plan, String credentials, String token) throws PaymentErrorException {
        plan.setExecuted(true);
        updatePlan(plan);
        paymentService.payToll(plan.getMsisdn(),credentials,token,plan.getSource_id(),plan.getDest_id(),plan.getPrice());
    }
}

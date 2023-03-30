package com.ruoyi.project.agent.service;

import com.ruoyi.project.agent.domain.Agent;

import java.util.List;

public interface AgentService {
    Integer addAgent(Agent agent);

    Agent seleAgentById(String id);

    List<Agent> seleListAgent(String agtype);
}

package com.ruoyi.project.agent.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.project.agent.domain.Agent;
import com.ruoyi.project.agent.mapper.AgentMapper;
import com.ruoyi.project.agent.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by hujun
 * @date 2023-02-15
 */
@Service
public class AgentServixeImpl implements AgentService {

    @Autowired
    private AgentMapper agentMapper;

    @Override
    public Integer addAgent(Agent agent) {
        agent.setAgentid(IdUtil.getSnowflakeNextIdStr());
        return agentMapper.addAgent(agent);
    }

    @Override
    public Agent seleAgentById(String id) {
        return agentMapper.seleAgentById(id);
    }

    @Override
    public List<Agent> seleListAgent(String agtype) {
        return agentMapper.seleListAgent(agtype);
    }
}

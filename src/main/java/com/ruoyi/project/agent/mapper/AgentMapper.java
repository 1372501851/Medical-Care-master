package com.ruoyi.project.agent.mapper;

/**
 * @author by hujun
 * @date 2023-02-15
 */

import com.ruoyi.project.agent.domain.Agent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Mapper
public interface AgentMapper {

    @Insert("INSERT INTO xt_agent (agentid,agname,agcity,agage,agsex,agaddress,agidcard,agidpic,agphone,appip,uesrname,information,agtype) values (#{agentid},#{agname},#{agcity},#{agage},#{agsex},#{agaddress},#{agidcard},#{agidpic},#{agphone},#{appip},#{uesrname},#{information},#{agtype})")
    Integer addAgent(Agent agent);

    @Select("SELECT * FROM xt_agent WHERE agentid = #{id}")
    Agent seleAgentById(String id);

    @Select("SELECT * FROM xt_agent WHERE agtype = #{agtype}")
    List<Agent> seleListAgent(String agtype);

    @Select("SELECT * FROM xt_agent WHERE agtype = #{agtype} And  agname LIKE #{name}")
    List<Agent> seleLikeAgent(String name , String agtype);
}

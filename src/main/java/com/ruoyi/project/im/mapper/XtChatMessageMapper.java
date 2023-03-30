package com.ruoyi.project.im.mapper;


import com.ruoyi.project.im.domain.Qun;
import com.ruoyi.project.im.domain.XtChatMessage;
import com.ruoyi.project.im.domain.XtMember;
import com.ruoyi.project.im.domain.XtQunConnetMember;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

/**
 * @Entity com.yunsw.zouwangyun.pojo.XtChatMessage
 */

@Repository
public interface XtChatMessageMapper  {

    void saveGroupMessageToMySQL(@Param("list") List<XtChatMessage> groupMessage);

    void savePrivateMessageToMySQL(@Param("list") List<XtChatMessage> privateMessage);

    @Insert("INSERT INTO xt_chat_message ( message_id,from_id,to_id,content,message_type,create_time,task_id,name,avatar,type,second,weizhi) VALUES  (#{messageId},#{fromId},#{toId},#{content},#{messageType},#{createTime},#{taskId},#{name},#{avatar},#{type},#{second},#{weizhi})")
    Integer savePrivateMessageToMySQL2(XtChatMessage privateMessage);

    List<XtChatMessage> queryPrivateHistoryMessageList(@Param("fromId") String fromId,@Param("toId") String toId,@Param("messageType")String messageType , @Param("weizhi")String weizhi) ;//,@Param("timeStamp") long timeStamp

    @Select("SELECT * FROM xt_chat_message WHERE to_id = #{qunId}")
    List<XtChatMessage> queryHistoryMesssageOfQun(String qunId);

    List<XtChatMessage> queryGroupHistoryMessageList(@Param("toId")String toId,@Param("messageType") String type,@Param("timeStamp") long timeStamp);

    List<XtChatMessage> selectMessageListAll();

    void delMessageListByIds(String[] ids);

    @Insert("INSERT INTO qun (id,qunzhuid,quntouxiang,qunmingzi,compid,weizhi) VALUES  (#{id},#{qunzhuid},#{quntouxiang},#{qunmingzi},#{compid},#{weizhi})")
    Integer creatQun(Qun qun);

    @Insert("INSERT INTO xt_qun_connect_member (uqun_connect_member_id,uqun_id , umember_id) VALUES (#{uqunConnectMemberId},#{uqunId},#{umemberId})")
    Integer creatXtQunConnetMember(XtQunConnetMember xtQunConnetMember);

    @Insert("INSERT INTO xt_member (umember_id , user_id) VALUES (#{umemberId},#{userId})")
    Integer creatXtMember(XtMember xtMember);


    @Select("SELECT umember_id FROM xt_member WHERE user_id = #{userId}")
    String selectMemberIdByUserId(String userId);

    @Select("SELECT user_id FROM xt_qun_connect_member , xt_member WHERE uqun_id = #{qunid} And xt_qun_connect_member.umember_id = xt_member.umember_id")
    List<String> seleChengYuanId(String qunid);

    @Select("SELECT umember_id FROM xt_member WHERE user_id = #{userId}")
    String selectMember(String user);

    @Select("SELECT * FROM qun WHERE qunid = #{qunid}")
    Qun seleQunByQunid(String qunid);


    @Delete("delete from qun where qunid = #{qunid} And chengyuanid = #{chengyuangid}")
    Integer delChengYaungById(String qunid , String chengyuangid);

    @Select("SELECT * FROM qun WHERE compid = #{compId} AND qunmingzi = #{qunMingZi}")
    List<Qun> selectQunByCompIdAndQunmingzi(String compId, String qunMingZi);

    @Select("SELECT * FROM qun WHERE compid = #{compId} AND qunmingzi = #{qunMingZi} AND weizhi = #{weizhi}")
    Qun selectQunByCompIdAndQunmingziAndweizhi(String compId, String qunMingZi , String weizhi);

    @Select("SELECT qunmingzi FROM qun WHERE compid = #{compId}")
    HashSet<String> selectQunNameByCompId(String compId);

    @Select("SELECT * FROM qun WHERE compid = #{compId}  AND weizhi = #{weizhi}")
    List<Qun> selectQunByCompIdAndQunmingziAndWeizhi(String compId,  String weizhi);

    @Select("SELECT * FROM qun WHERE compid = #{compId}  AND weizhi = #{weizhi} AND qunmingzi = #{name}")
    List<Qun> selectQunByCompIdAndQunmingziAndWeizhi2(String compId,  String weizhi , String name);
}





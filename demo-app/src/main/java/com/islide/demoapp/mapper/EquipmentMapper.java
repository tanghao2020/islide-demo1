package com.islide.demoapp.mapper;

import com.islide.demoapp.domain.Equipment;
import com.islide.demoapp.domain.EquipmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface EquipmentMapper {
    @SelectProvider(type=EquipmentSqlProvider.class, method="countByExample")
    int countByExample(EquipmentExample example);

    @DeleteProvider(type=EquipmentSqlProvider.class, method="deleteByExample")
    int deleteByExample(EquipmentExample example);

    @Delete({
        "delete from equipment",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into equipment (name, login_time)",
        "values (#{name,jdbcType=VARCHAR}, #{loginTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(Equipment record);

    @InsertProvider(type=EquipmentSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(Equipment record);

    @SelectProvider(type=EquipmentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="login_time", property="loginTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Equipment> selectByExample(EquipmentExample example);

    @Select({
        "select",
        "id, name, login_time",
        "from equipment",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="login_time", property="loginTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Equipment selectByPrimaryKey(Long id);

    @UpdateProvider(type=EquipmentSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Equipment record, @Param("example") EquipmentExample example);

    @UpdateProvider(type=EquipmentSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Equipment record, @Param("example") EquipmentExample example);

    @UpdateProvider(type=EquipmentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Equipment record);

    @Update({
        "update equipment",
        "set name = #{name,jdbcType=VARCHAR},",
          "login_time = #{loginTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Equipment record);

    @Select({
            "select",
            "id, name, login_time",
            "from equipment",
            "where TO_DAYS(login_time) = #{key}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="login_time", property="loginTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Equipment> selectByLoginDate(String key);
}